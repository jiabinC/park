package com.abin.park_system.mapper;

import com.abin.park_system.entity.Park;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ParkMapper {
	

	@Select("select * from Park")
	List<Park> findAllPark();
	
	@Select("select * from Park where  check_status = #{check_status}")
	List<Park> findAllParkByKey(@Param("check_status") String check_status);
	
	@Select("select * from Park where park_name like #{park_name}")
	List<Park> findParkByKey(@Param("park_name") String park_name);
	
	@Select("select * from Park where park_id=#{park_id}")
	Park findCarById(@Param("park_id") String park_id);

	@Select("select * from Park where check_status=#{check_status}")
	List<Park> findCarByCheckStatus(@Param("check_status") String check_status);

	@Select("select * from Park where user_id=#{user_id}")
	List<Park> findCarByUserId(@Param("user_id") String user_id);
	
	//添加信息
	@Insert("insert into park(park_id,user_id,park_name,park_location,check_status,publish_status,park_price,park_province,park_city,remark,create_time,park_latitude,park_longitude,park_num) values(#{park_id},#{user_id},#{park_name},#{park_location},#{check_status},#{publish_status},#{park_price},#{park_province},#{park_city},#{remark},SYSDATE(),#{park_latitude},#{park_longitude},#{park_num})")
    public void insertCar(Park park);
	
	//删除信息
	@Delete("delete from Car where park_id=#{park_id}")
	public void deleteCar(String id);

	@Update("update Park set check_status=#{check_status} where park_id=#{park_id}")
	public void updateCheckStatus(Park car);

	@Update("update Park set admin_remark=#{admin_remark} where park_id=#{park_id}")
	public void updateAdminRemark(Park car);

	@Update("update Park set publish_status=#{publish_status} where park_id=#{park_id}")
	public void updatePublishStatus(Park car);
	
	//修改信息
	@Update("update Park set park_name=#{park_name},price=#{park_price} where park_id=#{park_id}")
	public void updateCar(Park car);
}
