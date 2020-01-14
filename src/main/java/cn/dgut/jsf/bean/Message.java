package cn.dgut.jsf.bean;

import lombok.Data;

import java.security.Timestamp;
@Data
public class Message {
    private int mes_id; // 留言id*
    private String mes_title; // 留言主题*
    private String mes_content;// 留言内容*
    private String mes_time; // 留言发送时间
    private String mes_read; // 留言状态*
    private Member member;
}
