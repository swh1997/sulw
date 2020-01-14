package cn.dgut.jsf.service.impl;


import cn.dgut.jsf.bean.Activity;
import cn.dgut.jsf.bean.Coach;
import cn.dgut.jsf.mapper.ActivityDao;
import cn.dgut.jsf.mapper.AdminDao;
import cn.dgut.jsf.service.ActivityService;
import cn.dgut.jsf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityDao activityDao;

	@Override
	public List<Activity> findActivityUp() {
		return  activityDao.findActivityUp();
	}

	@Override
	public List<Activity> findActivityUpByAct_name(String condition, String input) {
		return activityDao.findActivityUpByAct_name(condition,input);
	}

	@Override
	public List<Activity> findActivityUpByCoach_name(String condition, String input) {
		return  activityDao.findActivityUpByCoach_name(condition,input);
	}

	@Override
	public Activity findActivityById(int act_id) {
		return activityDao.findActivityById(act_id);
	}

	@Override
	public int updateActivityState(Activity act) {
		return activityDao.updateActivityState(act);
	}

	@Override
	public List<Coach> findAllCoaName() {
		return activityDao.findAllCoaName();
	}

	@Override
	public List<Coach> findCoachByCoaExpert(String coach_expert) {
		return activityDao.findCoachByCoaExpert(coach_expert);
	}

	@Override
	public int addActivity(Activity activity) {
		return activityDao.addActivity(activity);
	}
}

