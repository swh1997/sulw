package cn.dgut.jsf.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Fee {
    private int fee_id;//费用表id
    private User user;
    private int fee_used;//支付金额
    private String fee_datetimet;//支付时间
    private SetMeal setMeal;

}
