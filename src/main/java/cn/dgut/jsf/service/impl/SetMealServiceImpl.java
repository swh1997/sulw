package cn.dgut.jsf.service.impl;


import cn.dgut.jsf.bean.*;
import cn.dgut.jsf.mapper.AdminDao;
import cn.dgut.jsf.mapper.SetMealDao;
import cn.dgut.jsf.service.AdminService;
import cn.dgut.jsf.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@Transactional
public class SetMealServiceImpl implements SetMealService {
	@Autowired
	private SetMealDao setMealDao;

	@Override
	public Member findMNameByUserId(String user_id) {
		return setMealDao.findMNameByUserId(user_id);
	}

	@Override
	public Member findClassHourUpByUserId(String user_id) {
		return setMealDao.findClassHourUpByUserId(user_id);
	}

	@Override
	public int findChByMemberId(Integer member_id) {
		return setMealDao.findChByMemberId(member_id);
	}

	@Override
	public List<SetMeal> findSetMealUp() {
		return setMealDao.findSetMealUp();
	}

	@Override
	public List<SetMealClassHour> findClassHourQuantityById(int sm_id) {
		return setMealDao.findClassHourQuantityById(sm_id);
	}

	@Override
	public SetMeal findSetMealById(int sm_id) {
		return setMealDao.findSetMealById(sm_id);
	}

	@Override
	public int buySetMealById(SetMealClassHour ch, int member_id) {
		return setMealDao.buySetMealById(ch,member_id);
	}

	@Override
	public   MemberClassHour findMchById(SetMealClassHour ch, int member_id) {
		return setMealDao.findMchById(ch,member_id);
	}

	@Override
	public int buySetMealByIdTwo(SetMealClassHour ch, int member_id) {
		return setMealDao.buySetMealByIdTwo(ch,member_id);
	}

	@Override
	public int buySetMealTwoById(Member m) {
		return setMealDao.buySetMealTwoById(m);
	}

	@Override
	public int buySetMealTwoByIdTwo(Member m) {
		return setMealDao.buySetMealTwoByIdTwo(m);
	}

	@Override
	public Fee getPayInfoByCID(String user_id, String fee_datetimet) {
		return setMealDao.getPayInfoByCID(user_id,fee_datetimet);
	}

	@Override
	public boolean insertFee(String user_id, int fee_used, String fee_datetimet, int sm_id) {
		return setMealDao.insertFee(user_id,fee_used,fee_datetimet,sm_id);
	}
}
