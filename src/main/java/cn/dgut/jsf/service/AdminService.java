package cn.dgut.jsf.service;

import cn.dgut.jsf.bean.*;
import cn.dgut.jsf.mapper.FacilityEquipmentDao;

import java.util.List;

public interface AdminService {
    public List<ClassHour> findAllClassHour();
    public int deleteClassHour(int ch_id);
    public int addClassHour(ClassHour classHour);
    public int updateClassHour(ClassHour classHour);
    public ClassHour findChById(int ch_id);

    public List<MemberCard> findAllMemberCard();
    public int deleteMemberCard(int mc_id);
    public int addMemberCard(MemberCard memberCard);
    public int updateMemberCard(MemberCard memberCard);
    public MemberCard findMcById(int mc_id);

    public List<FacilityEquipment> findAllEqu();
    public int deleteEqu(int equ_id);
    public int addEqu(FacilityEquipment equ);
    public int updateEqu(FacilityEquipment equ);
    public FacilityEquipment findEquById(int equ_id);

    public List<Coach> findAllCoach();
    public int deleteCoach(int coach_id);
    public int addCoach(Coach coach);
    public int updateCoach(Coach coach);
    public Coach findCoachById(int coach_id);
    public List<ClassHour> findcAllCh();
    public boolean findCa(int coach_id,String da);
    public int updateCoachState(Coach coach);

    public List<Member> findAllMember();
    public int deleteMember(int member_id);
    public Member findMemberById(int member_id);
    public MemberClassHour findMchById(MemberClassHour mch);
    public int updateMember(Member member);

    public int updatePassword(User user);

    public List<SetMeal> findAllSm();
    public int deleteSm(int sm_id);
    public int addSm(SetMeal sm);
    public int updateSm(SetMeal sm);
    public SetMeal findSmById(int sm_id);
    public List<SetMeal> findAllChSm();
}
