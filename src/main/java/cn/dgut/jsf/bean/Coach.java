package cn.dgut.jsf.bean;

import lombok.Data;

import java.util.List;
@Data
public class Coach {
    private List<Activity> activityList;//教练的活动
    private int coach_id; // 教练id
    private String coach_name; // 姓名
    private String coach_sex; // 性别
    private String coach_mobile;//教练手机号码
    private String coach_wechat; // 教练微信
    private String coach_qq; // 教练qq
    private String coach_expert;//教练特长
    private String coach_resume; // 自我简介
   // private List<ClassHour> classHourList;//这个教练要上什么课时类型
    private ClassHour classHour;
    private String coach_state;


}
