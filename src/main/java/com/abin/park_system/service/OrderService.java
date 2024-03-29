package com.abin.park_system.service;

import com.abin.park_system.entity.Order;
import com.abin.park_system.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	//获取全部
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return orderMapper.findAllOrder();
	}
	
	public Order getById(int id) {
		return orderMapper.findById(id);
	}
	
	//模糊查询
	public List<Order> getByUserId(String id) {
		return orderMapper.findByUserId(id);
	}
		
	//模糊查询
	public List<Order> getByKey(String tmpKey) {
		String key = "%"+tmpKey+"%";
		return orderMapper.findByKey(key);
	}
	
	
	//删除
	public boolean delOrder(int id) {
		try {
			orderMapper.deleteOrder(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//添加
	public boolean addOrder(Order order) {
		try {
			orderMapper.insertOrder(order);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public boolean updateOrderStatus(Order order) {
		try {
			orderMapper.updateStatus(order);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
