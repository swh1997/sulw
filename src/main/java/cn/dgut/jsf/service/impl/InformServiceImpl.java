package cn.dgut.jsf.service.impl;


import cn.dgut.jsf.bean.Inform;
import cn.dgut.jsf.bean.Member;
import cn.dgut.jsf.bean.MemberCard;
import cn.dgut.jsf.mapper.AdminDao;
import cn.dgut.jsf.mapper.InformDao;
import cn.dgut.jsf.service.AdminService;
import cn.dgut.jsf.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class InformServiceImpl implements InformService {
	@Autowired
	private InformDao informDao;

	@Override
	public List<Inform> findAllInform() {
		return informDao.findAllInform();
	}

	@Override
	public Inform findInformById(int inform_id) {
		return informDao.findInformById(inform_id);
	}

	@Override
	public List<MemberCard> findAllMcName() {
		return informDao.findAllMcName();
	}

	@Override
	public List<Member> findMenberByMcId(int mc_id) {
		return informDao.findMenberByMcId(mc_id);
	}

	@Override
	public int addInform(Inform inform) {
		return informDao.addInform(inform);
	}

	@Override
	public List<Inform> findMemberReceiveInform(int member_id) {
		return informDao.findMemberReceiveInform(member_id);
	}

	@Override
	public List<Inform> findMemberReceiveInformNotRead(int member_id) {
		return informDao.findMemberReceiveInformNotRead(member_id);
	}

	@Override
	public List<Inform> findMemberReceiveInformRead(int member_id) {
		return informDao.findMemberReceiveInformRead(member_id);
	}

	@Override
	public int deleteInform(int inform_id) {
		return informDao.deleteInform(inform_id);
	}

	@Override
	public int changeRead(int inform_id) {
		return informDao.changeRead(inform_id);
	}
}
