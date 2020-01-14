package cn.dgut.jsf.controller;

import cn.dgut.jsf.bean.Member;
import cn.dgut.jsf.bean.User;
import cn.dgut.jsf.service.AdminService;
import cn.dgut.jsf.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	// 跳转会员注册的
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "member-register";
	}
	// 注册会员账户和填写信息	z
	@RequestMapping("/addMember")
	@ResponseBody
	public int addMember(Model model, HttpServletRequest request, Member member, User user) {
		int i = 0;
		user.setUser_type("1");
		System.out.println("注册时用户id："+user.getUser_id());
		try {
			i = memberService.addUser(user); // 注册账户

			if (i == 1) {
				member.setUser(user);
				i += memberService.addMember(member);// 添加会员信息
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(i==1) {
				memberService.deleteMember(user.getUser_id());//删除账户
			}
		}
		return i;
	}
	// 修改会员信息
	@RequestMapping("/updateMember")
	@ResponseBody
	public int updateMember(Member member) {
		int i;
		try {
		    System.out.println("微信号:"+member.getMember_wechat()+member.getMember_id());
			i = memberService.updateMember(member);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}




}
	

