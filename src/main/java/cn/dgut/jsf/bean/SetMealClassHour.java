package cn.dgut.jsf.bean;

import lombok.Data;

@Data
public class SetMealClassHour {
    private int smch_id;//..
    private int ch_id;//课时id
    private int sm_id;//套餐id
    private int  smch_quantity;//套餐课时
    private int smch_present;//赠送课时
}
