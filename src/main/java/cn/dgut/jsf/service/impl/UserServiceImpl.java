package cn.dgut.jsf.service.impl;


import cn.dgut.jsf.bean.*;
import cn.dgut.jsf.mapper.AdminDao;
import cn.dgut.jsf.mapper.UserDao;
import cn.dgut.jsf.service.AdminService;
import cn.dgut.jsf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User loginCheck(User user) {
		return userDao.loginCheck(user);
	}

	@Override
	public Member findMNameByUserId(String user_id) {
		return userDao.findMNameByUserId(user_id);
	}

	@Override
	public Admin findANameByUserId(String user_id) {
		return userDao.findANameByUserId(user_id);
	}

	@Override
	public Member findClassHourUpByUserId(String user_id) {
		return userDao.findClassHourUpByUserId(user_id);
	}

	@Override
	public int updatePassword(User user) {
		return userDao.updatePassword(user);
	}

	@Override
	public int findChByMemberId(Integer member_id) {
		return userDao.findChByMemberId(member_id);
	}

	@Override
	public List<User> findAllUserMember() {
		return userDao.findAllUserMember();
	}

	@Override
	public List<User> findAllUserAdmin() {
		return userDao.findAllUserAdmin();
	}

	@Override
	public User findUserById(String user_id) {
		return userDao.findUserById(user_id);
	}

	@Override
	public int updateState(User user) {
		return userDao.updateState(user);
	}

	@Override
	public List<Fee> findFeeById(String row) {
		return userDao.findFeeById(row);
	}

	@Override
	public SetMeal findSmById(int sm_id) {
		return userDao.findSmById(sm_id);
	}

	@Override
	public MemberCard findMcById(int mc_id) {
		return userDao.findMcById(mc_id);
	}

	@Override
	public ClassHour findChById(int ch_id) {
		return userDao.findChById(ch_id);
	}

	@Override
	public List<MemberClassHour> findMchById(int member_id) {
		return userDao.findMchById(member_id);
	}
}
