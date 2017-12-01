package com.accp.web;

import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.pojo.Bill;
import com.accp.pojo.Provider;
import com.accp.pojo.User;
import com.accp.service.BizService;
import com.alibaba.fastjson.JSONArray;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
@Controller
public class BizAction {
	@Autowired
	private BizService bs;

	public BizService getBs() {
		return bs;
	}
	
	public void setBs(BizService bs) {
		this.bs = bs;
	}
	//登录
	@RequestMapping("/login.action")
	public String login(@Param(value="userCode") String userCode,@Param(value="userPassword") String userPassword){
		System.out.println(userCode+"\t"+userPassword);
		HashMap map=new HashMap();
		map.put("userCode", userCode);
		map.put("userPassword", userPassword);
		List<User> list=bs.login(map);
		if(list.size()>0){
			return "/jsp/frame.jsp";
			}
		else{
			return "error.jsp";
		}
	}
	//shiro控制登录
	@RequestMapping("/login2.action")
	public String validate2(HttpServletRequest request,Model model){
		Subject sub=SecurityUtils.getSubject();
		String userCode= request.getParameter("userCode");
		String userPassword= request.getParameter("userPassword");
		UsernamePasswordToken token=new UsernamePasswordToken(userCode, userPassword);
		try {
			sub.login(token);
			return "/show.action";//密匙配对成功
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "/error.jsp";
		}
	}
	//显示所有订单
	@RequestMapping("/show.action")
	public String show(HttpServletRequest resquset,HttpServletResponse response,Model model){
		List<Bill> listbill=bs.getAllBill();
		List<Provider> listprovider=bs.getAllProvider();
		List<Integer> listpay=bs.getIsPayment();
		model.addAttribute("listbill", listbill);
		model.addAttribute("listprovider", listprovider);
		model.addAttribute("listpay", listpay);
		return "/jsp/billlist.jsp";
		
	}
	//模糊查询
	@RequestMapping("/searchbill.action")
	public String searchBill(@RequestParam(value="queryProductName",required=false) String queryProductName,
				@RequestParam(value="",required=false) String queryProviderId,
				@RequestParam(value="queryIsPayment",required=false) String queryIsPayment,Model model){
		HashMap map=new HashMap(); 
		map.put("queryProductName", queryProductName);
		map.put("queryProviderId", queryProviderId);
		map.put("queryIsPayment", queryIsPayment);
		List<Bill> listbill=bs.getBill(map);
		List<Provider> listprovider=bs.getAllProvider();
		List<Integer> listpay=bs.getIsPayment();
		model.addAttribute("listbill", listbill);
		model.addAttribute("listprovider", listprovider);
		model.addAttribute("listpay", listpay);
		return "/jsp/billlist.jsp";
	}
	//进入订单添加页面
	@RequestMapping("/gotoaddbill.action")
	public String gotoaddbill(Model model){
		List<Provider> listprovider=bs.getAllProvider();
		model.addAttribute("listprovider", listprovider);
		return "/jsp/billadd.jsp";
	}
	//添加订单请求
	@RequestMapping("/addbill.action")
	public String addbill(@RequestParam(value="billCode",required=true) String billCode,
			@RequestParam(value="productName",required=true) String productName,
			@RequestParam(value="productUnit",required=true) String productUnit,
			@RequestParam(value="productCount",required=true) String productCount,
			@RequestParam(value="totalPrice",required=true) String totalPrice,
			@RequestParam(value="providerId",required=true) String providerId,
			@RequestParam(value="isPayment",required=true) String isPayment){
			HashMap<String,String> map=new HashMap<String,String>();
			map.put("billCode", billCode);
			map.put("productName", productName);
			map.put("productUnit", productUnit);
			map.put("productCount", productCount);
			map.put("totalPrice", totalPrice);
			map.put("providerId",providerId);
			map.put("isPayment",isPayment);
			System.out.println(map.values());
			int flag=bs.addBill(map);
			System.out.println(flag);
		return "show.action";
	}
	//异步
	@RequestMapping(value="/search.action",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String searchbill(@RequestBody Bill bill_json,HttpServletResponse response){
		  System.out.println(bill_json);
		  String queryProductName=bill_json.getProductName();
		  String queryProviderId=bill_json.getProName();
		  int queryIsPayment=bill_json.getIsPayment();
		System.out.println(queryProductName+"\t"+queryProviderId+"\t"+queryIsPayment);
		HashMap map=new HashMap(); 
		map.put("queryProductName", queryProductName);
		map.put("queryProviderId", queryProviderId);
		map.put("queryIsPayment", queryIsPayment);
		List<Bill> jlist=bs.getBill(map);
		System.out.println(jlist);
		return JSONArray.toJSONString(jlist);
	}
}
