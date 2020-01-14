package cn.dgut.jsf.bean;

import lombok.Data;

@Data
public class Admin {
	private int admin_id; // 管理员id
	private User user; // 用户id
	private String admin_name; // 姓名
}
