package com.accp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.dao.BizDao;
import com.accp.pojo.Bill;
import com.accp.pojo.Provider;
import com.accp.pojo.User;
import com.accp.service.BizService;
@Service("bs")
public class BizServiceimpl implements BizService{
	@Autowired
	private BizDao bizDao;
	
	public BizDao getBizDao() {
		return bizDao;
	}

	public void setBizDao(BizDao bizDao) {
		this.bizDao = bizDao;
	}

	@Override
	public List<User> login(HashMap map) {
		// TODO Auto-generated method stub
		return bizDao.login(map);
	}

	@Override
	public List<Bill> getAllBill() {
		// TODO Auto-generated method stub
		return bizDao.getAllBill();
	}

	@Override
	public List<Provider> getAllProvider() {
		// TODO Auto-generated method stub
		return bizDao.getAllProvider();
	}

	@Override
	public List<Integer> getIsPayment() {
		// TODO Auto-generated method stub
		return bizDao.getIsPayment();
	}

	@Override
	public List<Bill> getBill(HashMap map) {
		// TODO Auto-generated method stub
		return bizDao.getBill(map);
	}

	@Override
	public int addBill(HashMap<String,String> map) {
		// TODO Auto-generated method stub
		return bizDao.addBill(map);
	}

	@Override
	public Map<String, Object> validateUser2(String principal) {
		// TODO Auto-generated method stub
		return bizDao.validateUser2(principal);
	}

}
