package cn.dgut.jsf.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.dgut.jsf.bean.Member;
import cn.dgut.jsf.bean.Message;
import cn.dgut.jsf.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
    private String sb;
	// 查询出会员本人的留言(前端)
	@RequestMapping("/findMessageByMemberWeb")
	public String findMessageByMember(Model model, HttpSession session) {
		try {
			Member self = (Member) session.getAttribute("self");
			List<Message> messages = messageService.findMessageByMember(self.getMember_id());
			model.addAttribute("message", messages );
			sb="message";
			model.addAttribute("sb", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "information";
	}
	// 删除留言
	@ResponseBody
	@RequestMapping("/deleteMessage")
	public int deleteMessage(int mes_id) {
		int i;
		try {
			i = messageService.deleteMessage(mes_id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return i;
	}
	// 添加留言
	@ResponseBody
	@RequestMapping("/addMessage")
	public int addMessage( HttpSession session,Message message) {
		int i;
		try {
			Member member=(Member)session.getAttribute("self");
			message.setMember(member);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			System.out.println("留言添加的时间："+df.format(new Date()));// new Date()为获取当前系统时间
			message.setMes_time(df.format(new Date()));
			i = messageService.addMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
			i = 0;
		}
		return i;
	}

	// 查询未读留言信息（管理员）
	@RequestMapping("/findMessageNotRead")
	public String findMessageNotRead(Model model) {
		List<Message> messages = messageService.findMessageNotRead();
		model.addAttribute("message", messages);
		sb="message-nr";
		model.addAttribute("sb", sb);
		return "show";
	}

	// 查询已读留言信息（管理员）
	@RequestMapping("/findMessageRead")
	public String findMessageRead(Model model) {
		List<Message> messages = messageService.findMessageRead();
		model.addAttribute("message", messages);
		sb="message-r";
		model.addAttribute("sb", sb);
		return "show";
	}

	// 根据id展示留言内容
	@RequestMapping("/toShowMessage")
	public String toShowMessage(Model model,int mes_id) {
		try {
			Message messages = messageService.findMessageById(mes_id);
			messageService.ChangeMessageState(mes_id);
			model.addAttribute("message", messages);
			sb="message";
			model.addAttribute("sb", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show-byid";
	}
}
