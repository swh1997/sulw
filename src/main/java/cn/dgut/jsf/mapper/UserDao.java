package cn.dgut.jsf.mapper;

import cn.dgut.jsf.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Customer接口文件
 */
@Repository
public interface UserDao {
    public User loginCheck(User user);//登录查询验证
    public Member findMNameByUserId(String user_id);//根据userid找到会员名字
    public Admin findANameByUserId(String user_id);//根据userid找到管理员名字
    public Member findClassHourUpByUserId(String user_id);//根据会员id查找该会员拥有的上线的课时类型
    public int updatePassword(User user);
    public int findChByMemberId(Integer member_id);//查找会员是否有课时
    public List<User> findAllUserMember();
    public List<User> findAllUserAdmin();
    public User findUserById(String user_id);
    public int updateState(User user);
    public List<Fee> findFeeById(String row);
    public SetMeal findSmById(int sm_id);
    public MemberCard findMcById(int mc_id);
    public ClassHour findChById(int ch_id);
    public List<MemberClassHour> findMchById(int member_id);
	
}
