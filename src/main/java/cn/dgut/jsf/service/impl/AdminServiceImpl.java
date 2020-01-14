package cn.dgut.jsf.service.impl;


import cn.dgut.jsf.bean.*;
import cn.dgut.jsf.mapper.AdminDao;
import cn.dgut.jsf.mapper.FacilityEquipmentDao;
import cn.dgut.jsf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;

	@Override
	public List<ClassHour> findAllClassHour() {
		return adminDao.findAllClassHour();
	}

	@Override
	public int deleteClassHour(int ch_id) {
		return adminDao.deleteClassHour(ch_id);
	}

	@Override
	public int addClassHour(ClassHour classHour) {
		return adminDao.addClassHour(classHour);
	}

	@Override
	public int updateClassHour(ClassHour classHour) {
		return adminDao.updateClassHour(classHour);
	}

	@Override
	public ClassHour findChById(int ch_id) {
		return adminDao.findChById(ch_id);
	}

	@Override
	public List<MemberCard> findAllMemberCard() {
		return adminDao.findAllMemberCard();
	}

	@Override
	public int deleteMemberCard(int mc_id) {
		return adminDao.deleteMemberCard(mc_id);
	}

	@Override
	public int addMemberCard(MemberCard memberCard) {
		return adminDao.addMemberCard(memberCard);
	}

	@Override
	public int updateMemberCard(MemberCard memberCard) {
		return adminDao.updateMemberCard(memberCard);
	}

	@Override
	public MemberCard findMcById(int mc_id) {
		return adminDao.findMcById(mc_id);
	}

	@Override
	public List<FacilityEquipment> findAllEqu() {
		return adminDao.findAllEqu();
	}

	@Override
	public int deleteEqu(int equ_id) {
		return adminDao.deleteEqu(equ_id);
	}

	@Override
	public int addEqu(FacilityEquipment equ) {
		return adminDao.addEqu(equ);
	}

	@Override
	public int updateEqu(FacilityEquipment equ) {
		return adminDao.updateEqu(equ);
	}

	@Override
	public FacilityEquipment findEquById(int equ_id) {
		return adminDao.findEquById(equ_id);
	}

	@Override
	public List<Coach> findAllCoach() {
		return adminDao.findAllCoach();
	}

	@Override
	public int deleteCoach(int coach_id) {
		return adminDao.deleteCoach(coach_id);
	}

	@Override
	public int addCoach(Coach coach) {
		return adminDao.addCoach(coach);
	}

	@Override
	public int updateCoach(Coach coach) {
		return adminDao.updateCoach(coach);
	}

	@Override
	public Coach findCoachById(int coach_id) {
		return adminDao.findCoachById(coach_id);
	}

	@Override
	public  List<ClassHour> findcAllCh() {
		return adminDao.findcAllCh();
	}

	@Override
	public boolean findCa( int coach_id,String da) {
		return adminDao.findCa(coach_id,da);
	}

    @Override
    public int updateCoachState(Coach coach) {
        return adminDao.updateCoachState(coach);
    }

    @Override
    public List<Member> findAllMember() {
        return adminDao.findAllMember();
    }

    @Override
    public int deleteMember(int member_id) {
        return adminDao.deleteMember(member_id);
    }

    @Override
    public Member findMemberById(int member_id) {
        return adminDao.findMemberById(member_id);
    }

    @Override
    public MemberClassHour findMchById(MemberClassHour mch) {
        return adminDao.findMchById(mch);
    }

    @Override
    public int updateMember(Member member) {
        return adminDao.updateMember(member);
    }

    @Override
    public int updatePassword(User user) {
        return adminDao.updatePassword(user);
    }

	@Override
	public List<SetMeal> findAllSm() {
		return adminDao.findAllSm();
	}

	@Override
	public int deleteSm(int sm_id) {
		return adminDao.deleteSm(sm_id);
	}

	@Override
	public int addSm(SetMeal sm) {
		return adminDao.addSm(sm);
	}

	@Override
	public int updateSm(SetMeal sm) {
		return adminDao.updateSm(sm);
	}

	@Override
	public SetMeal findSmById(int sm_id) {
		return adminDao.findSmById(sm_id);
	}

	@Override
	public List<SetMeal> findAllChSm() {
		return adminDao.findAllChSm();
	}
}
