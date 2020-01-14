package cn.dgut.jsf.bean;

import lombok.Data;

@Data
public class Inform {
    private int inform_id;
    private String inform_title;
    private String inform_content;
    private String inform_time;
    private Member member;
    private Admin admin;
    private String inform_file;
    private String inform_read;
}
