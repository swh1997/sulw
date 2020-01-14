package cn.dgut.jsf.service.impl;

import cn.dgut.jsf.bean.Activity;
import cn.dgut.jsf.bean.FacilityEquipment;
import cn.dgut.jsf.bean.Notice;
import cn.dgut.jsf.mapper.WelcomeDao;
import cn.dgut.jsf.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class WelcomeServiceImpl implements WelcomeService {
	@Autowired
	private WelcomeDao welcomeDao;

	@Override
	public List<Activity> findActivityUp() {
		return welcomeDao.findActivityUp();
	}

	@Override
	public List<FacilityEquipment> findEquipmentUp() { return welcomeDao.findEquipmentUp(); }

	@Override
	public List<Notice> findNoticeUp() {
		return welcomeDao.findNoticeUp();
	}

	@Override
	public int findCoachs() {
		return welcomeDao.findCoachs();
	}

	@Override
	public int findUsers() {
		return welcomeDao.findUsers();
	}

	@Override
	public int findMembers() {
		return welcomeDao.findMembers();
	}

	@Override
	public int findAdmins() {
		return welcomeDao.findAdmins();
	}

	@Override
	public int findActivities() {
		return welcomeDao.findActivities();
	}

	@Override
	public int findFes() {
		return welcomeDao.findFes();
	}

	@Override
	public int findNotices() {
		return welcomeDao.findNotices();
	}
}
