package cn.dgut.jsf.bean;

import lombok.Data;

import java.util.List;

@Data
public class ClassHour {
    private int ch_id;//课时id
    private String ch_type;//课时类型
    private String ch_content;//课时内容
    private int ch_price;//课时价格
    private List<Member> memberList;//有哪些会员购买这个课时类型
    private List<Coach> coachList;//这个课时有哪些教练
}
