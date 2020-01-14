package cn.dgut.jsf.mapper;

import cn.dgut.jsf.bean.Member;
import cn.dgut.jsf.bean.User;
import org.springframework.stereotype.Repository;

/**
 * Customer接口文件
 */

@Repository
public interface MemberDao {
    public int addUser(User user);//为会员注册账户

    public int addMember(Member member);// 为新增的会员账户填写会员信息
    public int deleteMember(String user_id);//删除会员信息
	public int updateMember(Member member);
}
