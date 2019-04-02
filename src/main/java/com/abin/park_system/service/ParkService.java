package com.abin.park_system.service;

import com.abin.park_system.entity.Park;
import com.abin.park_system.mapper.ParkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ParkService {
	
	@Resource
	private ParkMapper parkMapper;
	
	//获取全部
	public List<Park> getAll() {
		// TODO Auto-generated method stub
		return parkMapper.findAllPark();
	}

	public Boolean check(Park park) {
		try{
			parkMapper.updateCheckStatus(park);
			parkMapper.updateAdminRemark(park);
			return true;
		}
		catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}
	
	public List<Park> getAllByKey(String status) {
		// TODO Auto-generated method stub
		return parkMapper.findAllParkByKey(status);
	}
	
	public List<Park> getByKey(String key) {
		// TODO Auto-generated method stub
		return parkMapper.findParkByKey("%"+key+"%");
	}
	//查询
	public Park getById(String id) {
		return parkMapper.findCarById(id);
	}
	//通过id修改
	public boolean updateCar(Park car) {
		try {
			parkMapper.updateCar(car);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean updateCheckStatus(Park car) {
		try {
			parkMapper.updateCheckStatus(car);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public boolean updatePublishStatus(Park car) {
		try {
			parkMapper.updatePublishStatus(car);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public List<Park> findParkByUserId(String user_id){

		return parkMapper.findCarByUserId(user_id);

	}
	public List<Park> findParkByCheckStatus(String check_status){

		return parkMapper.findCarByCheckStatus(check_status);

	}
	//删除
	public boolean delCar(String id) {
		try {
			parkMapper.deleteCar(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//添加
	public boolean addCar(Park car) {
		try {
			parkMapper.insertCar(car);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
