package cn.dgut.jsf.service.impl;


import cn.dgut.jsf.bean.Notice;
import cn.dgut.jsf.mapper.AdminDao;
import cn.dgut.jsf.mapper.NoticeDao;
import cn.dgut.jsf.service.AdminService;
import cn.dgut.jsf.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDao noticeDao;
	@Override
	public List<Notice> findAllNotice() {
		return noticeDao.findAllNotice();
	}

	@Override
	public Notice findNoticeById(int notice_id) {
		return noticeDao.findNoticeById( notice_id);
	}

	@Override
	public int deleteNotice(String notice_id) {
		return this.noticeDao.deleteNotice(notice_id);
	}

	@Override
	public int addNotice(Notice notice) {
		return this.noticeDao.addNotice(notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return this.noticeDao.updateNotice(notice);
	}

	@Override
	public int updateNoticeState(Notice notice) {
		return this.noticeDao.updateNoticeState(notice);
	}

}
