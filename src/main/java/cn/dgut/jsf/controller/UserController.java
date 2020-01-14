package cn.dgut.jsf.controller;

import cn.dgut.jsf.bean.*;
import cn.dgut.jsf.service.AdminService;
import cn.dgut.jsf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	private String sb;
	// 查询所有的账户
	@RequestMapping("/findAllUser")
	public String findAllUser(Model model) {
		List<User> userMember = userService.findAllUserMember();
		List<User> userAdmin = userService.findAllUserAdmin();
		model.addAttribute("um", userMember);
		model.addAttribute("ua", userAdmin);
		sb="fee";
		model.addAttribute("sb", sb);
		return "welcome";
	}
	// 查询指定账户的信息跳转到修改密码页面
	@RequestMapping("/toUpdateUserPassword")
	public String toUpdateUserPassword(Model model ,String user_id) {
		User users = userService.findUserById(user_id);
		model.addAttribute("user", users);
		sb="pwd-edit";
		model.addAttribute("sb", sb);
		return "show";
	}
	// 管理员更改用户密码
	@ResponseBody
	@RequestMapping("/updateUserPassword")
	public int updateUserPassword(Model model, User user) {
		int i;
		try {
			i = userService.updatePassword(user);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}
	// 账户的启用和停用
	@ResponseBody
	@RequestMapping("/updateState")
	public int updateState(User user) {
		int i;
		try {
			i = userService.updateState(user);
		} catch (Exception e) {
			e.printStackTrace();
			i = 0;
		}
		return i;
	}
		// 前台会员登录验证(前端)
	@ResponseBody
	@RequestMapping("/loginMemberCheck")
	public String loginMemberCheck(HttpServletRequest request, User user, HttpSession session) {
		String t = null;
		try {
			User users = userService.loginCheck(user);
			if (users.equals(null)) {
				t = "0";
				session.invalidate();
			} else {
				if (users.getUser_state().equals("1")) {
					if (users.getUser_type().equals("1")) {
						Member m=userService.findMNameByUserId(users.getUser_id());
						System.out.println("登录时用户id："+users.getUser_id()+"会员id："+m.getMember_id()+"会员卡id"+m.getMemberCard().getMc_id());
						if(userService.findChByMemberId(m.getMember_id())>0) {
							m = userService.findClassHourUpByUserId(users.getUser_id());
							System.out.println("登录时用户id："+m.getUser().getUser_id()+"会员id："+m.getMember_id()+"会员卡id"+m.getMemberCard().getMc_id());
							System.out.println("会员课时："+m.getClassHourList().size());
						}
						else{
							System.out.println("会员没有购买任何课时.");
						}

						session.setAttribute("self", m);
						session.setAttribute("m_id", users.getUser_id());
						session.setAttribute("m_pwd", users.getUser_pwd());
						t = "member";
					}else {
						t = "2";
						session.invalidate();
					}
				} else {
					t = "1";
					session.invalidate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			t = "0";
			session.invalidate();
		}
		return t;
	}
	// 前端跳转到后端登录
	@RequestMapping("/toLoginWeb")
	public String toLoginWeb(HttpSession session) {
		//session.invalidate();
		return "login";
	}
	// 管理员登录跳转用的
	@RequestMapping("/admin")
	public String main(Model model) {
		sb="admin";
		model.addAttribute("sb", sb);
		return "admin";
	}
	// 超级管理员登录跳转用的
	@RequestMapping("/superAdmin")
	public String superAdmin(Model model) {
		sb="superAdmin";
		model.addAttribute("sb", sb);
		return "admin";
	}
	//后台退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("a_id");
		session.removeAttribute("a_pwd");
		session.removeAttribute("adminSelf");
		return "/login";
	}
    //前端退出
    @RequestMapping("/Weblogout")
    public String Weblogout(HttpSession session) {
        session.removeAttribute("m_id");
        session.removeAttribute("m_pwd");
        session.removeAttribute("self");
        return "/index";
    }
	// 后台登录验证
	@RequestMapping("/loginCheck")
	@ResponseBody
	public String loginCheck(HttpServletRequest request, User user, HttpSession session) {
		String t = null;
		try {
			User users = userService.loginCheck(user);
			if (users.equals(null)) {
				t = "0";
			} else {
				if (users.getUser_state().equals("1")) {
					if (users.getUser_type().equals("1")) {
						t = "member";
					}
					if (users.getUser_type().equals("2")) {
						Admin adminSelf = userService.findANameByUserId(users.getUser_id());
						//session.setAttribute("adminName", adminSelf.getAdmin_name());
						session.setAttribute("adminSelf", adminSelf);
						t = "admin";
					}
					if (users.getUser_type().equals("3")) {
						Admin superAdmin = userService.findANameByUserId(users.getUser_id());
						//session.setAttribute("superAdminName", superAdmin.getAdmin_name());
						session.setAttribute("adminSelf", superAdmin);
						t = "superAdmin";
					}
					session.setAttribute("a_id", users.getUser_id());
					session.setAttribute("a_pwd", users.getUser_pwd());
				} else {
					t = "1";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			t = "0";
		}
		return t;
	}
	// 会员更改个人密码（前端）
	@RequestMapping("/updatePasswordWeb")
	@ResponseBody
	public int updatePasswordWeb(Model model, User user, HttpSession session) {
		int i;
		try {
			i = userService.updatePassword(user);
			if (i == 1) {
				session.removeAttribute("m_id");
				session.removeAttribute("m_pwd");
				session.removeAttribute("self");
			}
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}
    // 查询出个人信息准备个人信息完善
    @RequestMapping("/toPerfectMemberWeb")
    public String toPerfectMember(Model model, HttpSession session) {
        String row = (String) session.getAttribute("m_id");
		Member m=userService.findMNameByUserId(row);
		System.out.println("登录时用户id："+row+"会员id："+m.getMember_id());
		if(userService.findChByMemberId(m.getMember_id())>0) {
			m = userService.findClassHourUpByUserId(row);
			System.out.println("登录时用户id："+m.getUser().getUser_id()+"会员id："+m.getMember_id());
		}
		else{
			System.out.println("会员没有购买任何课时.");
		}
		List<MemberClassHour> mchs=userService.findMchById(m.getMember_id());
		model.addAttribute("mch", mchs);
		List<Fee> fees=userService.findFeeById(row);
        model.addAttribute("fee", fees);
        model.addAttribute("member", m);
        sb="user";
        model.addAttribute("sb", sb);
        return "information";
    }
    @RequestMapping("/toShowSetMeal")
    public String toShowSetMeal(Model model,int sm_id){
	   SetMeal sms= userService.findSmById(sm_id);
	    model.addAttribute("sm",sms);
	    sb="sm";
        model.addAttribute("sb", sb);
	    return"show-byid";
    }
	@RequestMapping("/toShowSm")
	public String toShowSm(Model model,int mc_id){
		MemberCard mcs= userService.findMcById(mc_id);
		model.addAttribute("mc",mcs);
		sb="mc";
		model.addAttribute("sb", sb);
		return"show-byid";
	}
	@RequestMapping("/toShowCh")
	public String toShowCh(Model model,int ch_id){
		ClassHour chs= userService.findChById(ch_id);
		model.addAttribute("ch",chs);
		model.addAttribute("msg", "会员查看课时详情");
		sb="ch";
		model.addAttribute("sb", sb);
		return"show-byid";
	}

}
	

