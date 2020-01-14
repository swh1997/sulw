package cn.dgut.jsf.service;

import cn.dgut.jsf.bean.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SetMealService {
    public Member findMNameByUserId(String user_id);//根据userid找到会员名字
    public Member findClassHourUpByUserId(String user_id);//根据会员id查找该会员拥有的上线的课时类型
    public int findChByMemberId(Integer member_id);//查找会员是否有课时
    public List<SetMeal> findSetMealUp();//发现所有套餐
    public List<SetMealClassHour> findClassHourQuantityById(int sm_id);//发现套餐中的课时数量
    public SetMeal findSetMealById(int sm_id);//通过id发现套餐
    public int buySetMealById(@Param("ch") SetMealClassHour ch, @Param("member_id") int member_id);//插入套餐
    public   MemberClassHour findMchById(@Param("ch") SetMealClassHour ch, @Param("member_id") int member_id);//发现套餐
    public int buySetMealByIdTwo(@Param("ch") SetMealClassHour ch,@Param("member_id") int member_id);//修改套餐
    public int buySetMealTwoById(Member m);//无卡会员修改
    public int buySetMealTwoByIdTwo(Member m);//有卡会员修改
    public Fee getPayInfoByCID(@Param("user_id")String user_id,@Param("fee_datetimet")String fee_datetimet);//查询出账户
    public boolean insertFee(@Param("user_id") String user_id, @Param("fee_used") int fee_used, @Param("fee_datetimet")String fee_datetimet, @Param("sm_id")int sm_id);//修改Fee
}
