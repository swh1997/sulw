package cn.dgut.jsf.service.impl;


import cn.dgut.jsf.bean.*;
import cn.dgut.jsf.mapper.AdminDao;
import cn.dgut.jsf.mapper.CoachDao;
import cn.dgut.jsf.service.AdminService;
import cn.dgut.jsf.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CoachServiceImpl implements CoachService {
	@Autowired
	private CoachDao coachDao;

	@Override
	public List<Coach> findAllCoachUp() {
		return coachDao.findAllCoachUp();
	}

	@Override
	public List<Coach> findCoachUpByCoach_name(String condition, String input) {
		return coachDao.findCoachUpByCoach_name(condition,input);
	}

	@Override
	public int CoachAppointWeb(CoachAppoint coachClassHour) {
		return coachDao.CoachAppointWeb(coachClassHour);
	}

	@Override
	public int reduceMchRemainingQuantity(int member_id, int ch_id) {
		return coachDao.reduceMchRemainingQuantity(member_id, ch_id);
	}

	@Override
	public MemberClassHour findMchRemainingQuantity(int member_id, int ch_id) {
		return coachDao.findMchRemainingQuantity(member_id,  ch_id);
	}

	@Override
	public int delMemberClassHour(int member_id, int ch_id) {
		return coachDao.delMemberClassHour(member_id, ch_id);
	}

	@Override
	public List<MemberClassHour> findAllMchByMemberId(int member_id) {
		return coachDao.findAllMchByMemberId(member_id);
	}

	@Override
	public Fee getPayInfoByCID(String user_id) {
		return coachDao.getPayInfoByCID(user_id);
	}

	@Override
	public User findUserByMmeberId(int member_id) {
		return coachDao.findUserByMmeberId(member_id);
	}

	@Override
	public CoachAppoint findCoachAppointByTime(String ca_startTime,int member_id) {
		return coachDao.findCoachAppointByTime(ca_startTime, member_id);
	}

	@Override
	public CoachAppoint findMinTimeByCoachIdAndChId(int coach_id, int ch_Id, String time,int num ) {
		return coachDao.findMinTimeByCoachIdAndChId( coach_id,  ch_Id,  time,num);
	}

	@Override
	public CoachAppoint findMaxTimeByCoachIdAndChId(int coach_id, int ch_id, String time,int num) {
		return coachDao.findMaxTimeByCoachIdAndChId( coach_id,  ch_id,  time, num);
	}

	@Override
	public CoachAppoint findendTime(String ca_startTime) {
		return  coachDao.findendTime(ca_startTime);
	}
}
