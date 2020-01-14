package cn.dgut.jsf.service;

import cn.dgut.jsf.bean.Inform;
import cn.dgut.jsf.bean.Member;
import cn.dgut.jsf.bean.MemberCard;

import java.util.List;

public interface InformService {
    public List<Inform> findAllInform();//管理员查询所有通知
    public Inform findInformById(int inform_id);//发现单个通知
    public  List<MemberCard> findAllMcName();//发现所有会员卡的名字
    public List<Member> findMenberByMcId(int mc_id);//发现该会员卡的会员
    public int addInform(Inform inform);//添加通知
    public List<Inform> findMemberReceiveInform(int member_id);
    public List<Inform> findMemberReceiveInformNotRead(int member_id);
    public List<Inform> findMemberReceiveInformRead(int member_id);
    public int deleteInform(int inform_id);//删除通知
    public int changeRead(int inform_id);
}
