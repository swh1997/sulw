package cn.dgut.jsf.service.impl;


import cn.dgut.jsf.bean.Member;
import cn.dgut.jsf.bean.User;
import cn.dgut.jsf.mapper.AdminDao;
import cn.dgut.jsf.mapper.MemberDao;
import cn.dgut.jsf.service.AdminService;
import cn.dgut.jsf.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;

	@Override
	public int addUser(User user) {
		return memberDao.addUser(user);
	}

	@Override
	public int addMember(Member member) {
		return memberDao.addMember(member);
	}

	@Override
	public int deleteMember(String user_id) {
		return memberDao.deleteMember(user_id);
	}

	@Override
	public int updateMember(Member member) {
		return memberDao.updateMember(member);
	}
}
