package com.accp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.accp.pojo.Bill;
import com.accp.pojo.Provider;
import com.accp.pojo.User;

public interface BizDao {
	//登录
	public List<User> login(HashMap map);
	//所有订单页面显示订单信息，订单名称，订单支付状态
	public List<Bill> getAllBill();
	public List<Provider> getAllProvider();
	public List<Integer> getIsPayment();
	//订单模糊查询
	public List<Bill> getBill(HashMap map);
	//添加订单
	public int addBill(HashMap<String,String> map);
	//根据用户名获取用户信息
	public Map<String, Object> validateUser2(@Param("principal") String principal);
}
