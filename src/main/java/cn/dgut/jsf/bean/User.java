package cn.dgut.jsf.bean;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String user_id;//用户id
    private Member member;//会员名
    private Admin admin;//管理员名
    private String user_pwd;//用户密码
    private String user_type;//用户类型
    private String user_state;//用户状态
    private List<Fee> feeList;

}

