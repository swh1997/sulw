package cn.dgut.jsf.service;

import java.util.List;

import cn.dgut.jsf.bean.Message;

public interface MessageService {
	public List<Message> findMessageNotRead();// 查找所有未读留言

	public List<Message> findMessageRead();// 查找所有已读留言

	public Message findMessageById(int mes_id);// 查找id的留言

	public List<Message> findMessageByMember(int member_id);// 查找会员id的留言

	public int addMessage(Message message);// 会员添加留言

	public int ChangeMessageState(int mes_id);// 把未读改为已读

	public int deleteMessage(int mes_id);//删除留言
}
