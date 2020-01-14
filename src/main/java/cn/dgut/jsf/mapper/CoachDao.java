package cn.dgut.jsf.mapper;

import cn.dgut.jsf.bean.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Customer接口文件
 */

@Repository
public interface CoachDao {
public List<Coach> findAllCoachUp();//发现所有教练
public List<Coach> findCoachUpByCoach_name(@Param("condition")String condition, @Param("input") String input); //根据教练信息查询所有在线的活动
 public int  CoachAppointWeb(CoachAppoint coachClassHour);//预约教练
    public int   reduceMchRemainingQuantity(@Param("member_id")int member_id,@Param("ch_id")int ch_id);//减少课时剩余
    public MemberClassHour findMchRemainingQuantity(@Param("member_id")int member_id,@Param("ch_id")int ch_id);//发现课时剩余
    public  int delMemberClassHour(@Param("member_id")int member_id,@Param("ch_id")int ch_id);//删除课时类型
    public  List<MemberClassHour> findAllMchByMemberId(int member_id);//发现会员的所有课时类型
    public Fee getPayInfoByCID(String user_id);//查询出账户
    public User findUserByMmeberId(int member_id);//查出用户
   public CoachAppoint findCoachAppointByTime(@Param("ca_startTime") String ca_startTime,@Param("member_id") int member_id);//查询会员选择的时间段是否有预约
    public CoachAppoint findMinTimeByCoachIdAndChId(@Param("coach_id") int coach_id,@Param("ch_id")int ch_id,@Param("time")String time,@Param("num")int num );//发现该教练课时类型下的未来可预约的时间段
    public CoachAppoint findMaxTimeByCoachIdAndChId(@Param("coach_id") int coach_id,@Param("ch_id")int ch_id,@Param("time")String time ,@Param("num")int num  );//发现该教练课时类型下的未来可预约的时间段
    public  CoachAppoint findendTime(@Param("ca_startTime") String ca_startTime);//查看中间时段是否被预约
}
