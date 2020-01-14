package cn.dgut.jsf.bean;

import lombok.Data;

import java.util.List;

@Data
public class SetMeal {
    private int sm_id;//套餐id
    private String sm_name;
    private MemberCard memberCard;
    private List<ClassHour> classHourList;
    private int sm_price;

}
