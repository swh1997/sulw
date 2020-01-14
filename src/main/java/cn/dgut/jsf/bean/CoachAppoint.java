package cn.dgut.jsf.bean;

import lombok.Data;

import java.util.List;

@Data
public class CoachAppoint {
    private int ca_id; // 预约教练表id
    private int member_id; // 会员id
    private int coach_id; // 教练id
    private String ca_startTime;//预约教练日期
    private String ca_endTime;//预约教练日期
    private ClassHour classHour;
    private String ca_state; // 预约教练状态
    private String ca_result; // 预约教练结果
    private List<String> temp;//存放时段

}
