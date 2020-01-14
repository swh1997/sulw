package cn.dgut.jsf.bean;

import lombok.Data;

@Data
public class Notice {
    private int notice_id;//公告id
    private String notice_title;//公告主题
    private String notice_content;//公告内容
    private String notice_date;//公告发布日期
    private String notice_file;//公告文件
    private String notice_state;//公告状态
    private Admin admin;
}
