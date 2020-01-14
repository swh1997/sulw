package cn.dgut.jsf.bean;

import lombok.Data;

@Data
public class MemberClassHour {
    private int mch_id;//..
    private int ch_id;//课时id
    private int member_id;//会员id
    private int mch_remainingQuantity;//剩余课时
    private String mch_state;//课时状态
}
