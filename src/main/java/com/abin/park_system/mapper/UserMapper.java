package com.abin.park_system.mapper;

import com.abin.park_system.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
	
	//添加信息
	@Insert("insert into user(user_id,user_name,user_phone,plate_num,user_password,createDate,stauts) values(#{user_id},#{user_name},#{user_phone},#{plate_num},#{user_password},SYSDATE(),0)")
    public void insertUsers(Users users);
	
	//删除信息
	@Delete("delete from User where id=#{user_id}")
	public void deleteUserById(int id);
	
	//修改信息
	@Update("update user set stauts=#{stauts} where id=#{user_id}")
	public void updateUserStauts(@Param("user_id") int id, @Param("stauts") int stauts);

	@Update("update user set point=#{point} where id=#{user_id}")
	public void updateUserPoint(Users user);

	@Update("update user set user_phone=#{user_phone},plate_num=#{plate_num} where id=#{user_id}")
	public void updateUser(Users user);
	@Update("update user set user_password=#{user_password} where id=#{user_id}")
	public void updateUserPwd(Users user);
	//查询信息
	@Select("select * from user where user_name like #{user_name}")
	List<Users> findByName(@Param("user_name") String name);

	@Select("select * from user")
	List<Users> findAllUser();

	@Select("select * from user where user_id=#{user_id}")
	Users findUserById(@Param("user_id") String id);

	@Select("select * from user order by point desc")
	List<Users> findAllPoint();

	@Select("select * from user where user_name like #{user_name} order by point desc")
	List<Users> findPointByName(@Param("user_name") String name);

	//登陆使用
	@Select("select * from user where user_name=#{user_name} and user_password = #{user_password}")
	List<Users> findUserByNameAndPwd(@Param("user_name") String adminName, @Param("user_password") String password);
}
