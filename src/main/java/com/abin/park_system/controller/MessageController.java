package com.abin.park_system.controller;

import com.abin.park_system.entity.Message;
import com.abin.park_system.entity.Users;
import com.abin.park_system.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	//添加留言
	@RequestMapping("/message-save")
	public String saveMessage(HttpSession session, @ModelAttribute("message") Message message, Model model){
		Users user = (Users)session.getAttribute("LogUser");
		message.setUser_id(user.getUser_id());
		message.setUser_name(user.getUser_name());
		messageService.addMessage(message);
		List<Message> messageList = messageService.getMyMessage(user.getUser_id());
		model.addAttribute("messageList", messageList);
		return "myMessage";
	}
	
	//用户删除
	@RequestMapping("/delMsg")
	public String delMsg(HttpSession session, @RequestParam("id") int id, Model model){
		messageService.delMessage(id);
		Users user = (Users)session.getAttribute("LogUser");
		List<Message> messageList = messageService.getMyMessage(user.getUser_id());
		model.addAttribute("messageList", messageList);
		return "myMessage";
	}
	
	
	@RequestMapping("/myMessage")
	public String myMessage(@RequestParam("id") String id, Model model){
		List<Message> messageList = messageService.getMyMessage(id);
		model.addAttribute("messageList", messageList);
		return "myMessage";
	}
}
