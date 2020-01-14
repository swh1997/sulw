package cn.dgut.jsf.bean;

import lombok.Data;

import java.util.List;

@Data
public class Member {
    private int member_id; // 会员id
    private User user; //用户
    private String member_name; // 姓名
    private String member_sex; // 性别
    private String member_birthDate;//会员出生日期
    private String member_mobile;//会员手机号码
    private String member_wechat; // 会员微信
    private String member_qq; // 会员qq
    private String member_address; // 会员地址
    private String member_resume; // 会员自我简介
    private String member_idcard; //身份证号
    private MemberCard memberCard;//会员卡
    private String member_mcStart;//会员卡开始日期
    private String member_mcEnd;//会员卡结束日期
    private List<ClassHour> classHourList;//有什么课时类型


}
