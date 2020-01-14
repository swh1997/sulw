package cn.dgut.jsf.bean;

import lombok.Data;

@Data
public class Activity {
    private int act_id;//活动id
    private String act_name;//活动主题
    private String act_content;//活动内容
    private String act_time;//活动开始时间
    private String act_state;//活动状态
    private Coach coach;//活动教练

}
