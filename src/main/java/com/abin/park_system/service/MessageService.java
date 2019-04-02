package com.abin.park_system.service;

import com.abin.park_system.entity.Message;
import com.abin.park_system.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageService {
	
	@Resource
	private MessageMapper messageMapper;
	
	//通过id修改状态
	public boolean updateMessage(Message message) {
		try {
			messageMapper.updateMessage(message);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//模糊查询
	public List<Message> getByContent(String tmpContent) {
		String content = "%"+tmpContent+"%";
		return messageMapper.findByContent(content);
	}
	//获取全部
	public List<Message> getAll() {
		// TODO Auto-generated method stub
		return messageMapper.findAllMessage();
	}
	public List<Message> getMyMessage(String id) {
		// TODO Auto-generated method stub
		return messageMapper.findMyMessage(id);
	}
	//删除
	public boolean delMessage(int id) {
		try {
			messageMapper.deleteMessage(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//添加
	public boolean addMessage(Message message) {
		try {
			messageMapper.insertMessage(message);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
