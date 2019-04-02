package com.abin.park_system.mapper;

import com.abin.park_system.entity.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
	
	//添加信息
	@Insert("insert into Message(content,createDate,user_id,user_name) values(#{content},SYSDATE(),#{user_id},#{user_name})")
    public void insertMessage(Message message);
	
	//删除信息
	@Delete("delete from Message where id=#{id}")
	public void deleteMessage(int id);
	
	//查询信息
	@Select("select * from Message where content like #{content}")
	List<Message> findByContent(@Param("content") String content);
	
	@Select("select * from Message")
	List<Message> findAllMessage();
	@Select("select * from Message where user_id=#{id}")
	List<Message> findMyMessage(@Param("id") String id);
	//修改信息
	@Update("update Message set answer=#{answer},answerDate=SYSDATE(),admin_id=#{admin_id},admin_name=#{admin_name} where id=#{id}")
	public void updateMessage(Message message);
}
