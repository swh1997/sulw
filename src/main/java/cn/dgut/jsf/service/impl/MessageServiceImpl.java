package cn.dgut.jsf.service.impl;

import java.util.List;

import cn.dgut.jsf.bean.Message;
import cn.dgut.jsf.mapper.MessageDao;
import cn.dgut.jsf.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;

	@Override
	public List<Message> findMessageNotRead() {
		return this.messageDao.findMessageNotRead();
	}

	@Override
	public List<Message> findMessageRead() {
		return this.messageDao.findMessageRead();
	}

	@Override
	public int addMessage(Message message) {
		return this.messageDao.addMessage(message);
	}

	@Override
	public int ChangeMessageState(int mes_id) {
		return this.messageDao.ChangeMessageState(mes_id);
	}

	@Override
	public Message findMessageById(int mes_id) {
		return this.messageDao.findMessageById(mes_id);
	}

	@Override
	public int deleteMessage(int mes_id) {
		return this.messageDao.deleteMessage(mes_id);
	}

	@Override
	public List<Message> findMessageByMember(int member_id) {
		return this.messageDao.findMessageByMember(member_id);
	}
}
