package cn.dgut.jsf.bean;

import lombok.Data;

@Data
public class MemberCard {
    private int mc_id;//会员卡id
    private String mc_pyte;//会员卡类型
    private int mc_quantity;//会员卡期限
    private String mc_content;//会员卡内容
    private int mc_price;//会员卡价格

}
