package cn.dgut.jsf.controller;

import cn.dgut.jsf.bean.*;
import cn.dgut.jsf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	private String sb;
	// 查询所有课程信息（系统管理员）
	@RequestMapping("/findAllClassHour")
	public String findAllClassHour(Model model) {
		List<ClassHour> chs = adminService.findAllClassHour();
		System.out.println("课程");
		model.addAttribute("ch", chs);
		sb="classHour";
		model.addAttribute("sb", sb);
		return "welcome";
	}
	// 删除课程
	@ResponseBody
	@RequestMapping("/deleteClassHour")
	public int deleteClassHour(int ch_id) {
		int i;
		try {
			i = adminService.deleteClassHour(ch_id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return i;
	}
	// 跳转到增加课程页面
	@RequestMapping("/toAddClassHour")
	public String toAddClassHour(Model model) {
		sb="classHour-add";
		model.addAttribute("sb", sb);
		return "show";
	}
	// 增加课程
	@RequestMapping("/addClassHour")
	public String addClassHour(Model model ,ClassHour classHour) {
		try {
			int i = adminService.addClassHour(classHour);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "课程添加失败");
			toAddClassHour(model);
		}
		return "redirect:/admin/findAllClassHour";
	}
	// 跳转到增加课程页面
	@RequestMapping("/toUpdateClassHour")
	public String toUpdateClassHour(Model model,int ch_id) {
		ClassHour chs = adminService.findChById(ch_id);
		model.addAttribute("ch", chs);
		sb="classHour-edit";
		model.addAttribute("sb", sb);
		return "show";
	}
	// 增加课程页面
	@RequestMapping("/updateClassHour")
	public String updateClassHour(Model model,ClassHour classHour) {
     try{
		int i = adminService.updateClassHour(classHour);
		if (i == 1) {
			model.addAttribute("msg", "修改成功！");
		} else
			model.addAttribute("msg", "修改失败！");
	} catch (Exception e) {
		e.printStackTrace();
	}
		return "msg";
	}



	// 查询所有会员卡信息（系统管理员）
	@RequestMapping("/findAllMemberCard")
	public String findAllMemberCard(Model model) {
		List<MemberCard> mcs = adminService.findAllMemberCard();
		System.out.println("会员卡");
		model.addAttribute("mc", mcs);
		sb="memberCard";
		model.addAttribute("sb", sb);
		return "welcome";
	}
	// 删除课程
	@ResponseBody
	@RequestMapping("/deleteMemberCard")
	public int deleteMemberCard(int mc_id) {
		int i;
		try {
			i = adminService.deleteMemberCard(mc_id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return i;
	}
	// 跳转到增加课程页面
	@RequestMapping("/toAddMemberCard")
	public String toAddMemberCard(Model model) {
		sb="memberCard-add";
		model.addAttribute("sb", sb);
		return "show";
	}
	// 增加课程
	@RequestMapping("/addMemberCard")
	public String addMemberCard(Model model ,MemberCard memberCard) {
		try {
			int i = adminService.addMemberCard(memberCard);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "会员卡添加失败");
			toAddMemberCard(model);
		}
		return "redirect:/admin/findAllMemberCard";
	}
	// 跳转到增加课程页面
	@RequestMapping("/toUpdateMemberCard")
	public String toUpdateMemberCard(Model model,int mc_id) {
		System.out.println("修改的会员卡id："+mc_id);
		MemberCard mcs = adminService.findMcById(mc_id);
		model.addAttribute("mc", mcs);
		sb="memberCard-edit";
		model.addAttribute("sb", sb);
		return "show";
	}
	// 增加课程页面
	@RequestMapping("/updateMemberCard")
	public String updateMemberCard(Model model,MemberCard memberCard) {
		try{
			int i = adminService.updateMemberCard(memberCard);
			if (i == 1) {
				model.addAttribute("msg", "修改成功！");
			} else
				model.addAttribute("msg", "修改失败！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "msg";
	}



	// 查询所有场地设备信息（系统管理员）
	@RequestMapping("/findAllEqu")
	public String findAllEqu(Model model) {
		List<FacilityEquipment> equs = adminService.findAllEqu();
		System.out.println("会员卡");
		model.addAttribute("equ", equs);
		sb="equ";
		model.addAttribute("sb", sb);
		return "welcome";
	}
	// 删除场地设备
	@ResponseBody
	@RequestMapping("/deleteEqu")
	public int deleteEqu(int equ_id) {
		int i;
		try {
			i = adminService.deleteEqu(equ_id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return i;
	}
	// 跳转到增加场地设备页面
	@RequestMapping("/toAddEqu")
	public String toAddEqu(Model model) {
		sb="equ-add";
		model.addAttribute("sb", sb);
		return "show";
	}
	// 增加场地设备
	@RequestMapping("/addEqu")
	public String addEqu(Model model ,FacilityEquipment equ) {
		try {
			int i = adminService.addEqu(equ);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "场地设备添加失败");
			toAddEqu(model);
		}
		return "redirect:/admin/findAllEqu";
	}
	// 跳转到修改场地设备页面
	@RequestMapping("/toUpdateEqu")
	public String toUpdateEqu(Model model,int equ_id) {
		System.out.println("修改的会员卡id："+equ_id);
		FacilityEquipment equs = adminService.findEquById(equ_id);
		model.addAttribute("equ", equs);
		sb="equ-edit";
		model.addAttribute("sb", sb);
		return "show";
	}
	// 修改场地设备页面
	@RequestMapping("/updateEqu")
	public String updateEqu(Model model,FacilityEquipment equ) {
		try{
			int i = adminService.updateEqu(equ);
			if (i == 1) {
				model.addAttribute("msg", "修改成功！");
			} else
				model.addAttribute("msg", "修改失败！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "msg";
	}


	// 查询所有教练信息（系统管理员）
	@RequestMapping("/findAllCoach")
	public String findAllCoach(Model model) {
		List<Coach> coachs = adminService.findAllCoach();
		System.out.println("教练");
		model.addAttribute("coa", coachs);
		sb="coach";
		model.addAttribute("sb", sb);
		return "welcome";
	}
	// 删除教练
	@ResponseBody
	@RequestMapping("/deleteCoach")
	public int deleteCoach(int coach_id) {
		int i;
		try {
			i = adminService.deleteCoach(coach_id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return i;
	}
	// 跳转到增加教练页面
	@RequestMapping("/toAddCoach")
	public String toAddCoach(Model model) {
		List<ClassHour> chs=adminService.findcAllCh();
		model.addAttribute("ch", chs);
		sb="coa-add";
		model.addAttribute("sb", sb);
		return "show";
	}
	// 增加教练
	@RequestMapping("/addCoach")
	public String addCoach(Model model ,Coach coach) {
		try {
			int i = adminService.addCoach(coach);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "教练添加失败");
			toAddCoach(model);
		}
		return "redirect:/admin/findAllCoach";
	}
	// 跳转到修改教练页面
	@RequestMapping("/toUpdateCoach")
	public String toUpdateCoach(Model model,int coach_id) {
		System.out.println("修改的教练id："+coach_id);
		Coach coachs = adminService.findCoachById(coach_id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		if(adminService.findCa(coach_id,df.format(new Date()))==true){
			model.addAttribute("msg","提示：该教练未来已有人预约，不能修改所授课程！");
		}
		else {
			model.addAttribute("msg",1);
			List<ClassHour> chs = adminService.findcAllCh();
			model.addAttribute("ch", chs);
		}
			model.addAttribute("coa", coachs);
		sb="coa-edit";
		model.addAttribute("sb", sb);
		return "show";
	}
	//通过id发现课程
	@ResponseBody
	@RequestMapping("/findChById")
	public ClassHour findChById(Model model,int ch_id) {
		ClassHour chs=adminService.findChById(ch_id);
		return chs;
	}
	// 修改教练页面
	@RequestMapping("/updateCoach")
	public String updateCoach(Model model, Coach coach) {
		try{
			int i=0;
			 i= adminService.updateCoach(coach);
			if (i == 1) {
				model.addAttribute("msg", "修改成功！");
			} else
				model.addAttribute("msg", "修改失败！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "msg";
	}
    // 修改教练状态页面
    @ResponseBody
    @RequestMapping("/updateCoachState")
    public int  updateCoachState(Coach coach) {
        int i;
	    try{
	        System.out.println(coach.getCoach_state());
           i= adminService.updateCoachState(coach);

        } catch (Exception e) {
            e.printStackTrace();
            i=0;
        }
        return i;
    }



    // 查询所有会员信息（系统管理员）
    @RequestMapping("/findAllMember")
    public String findAllMember(Model model) {
        List<Member> members;
            members = adminService.findAllMember();
        System.out.println("会员");
        model.addAttribute("m", members);
        sb="member";
        model.addAttribute("sb", sb);
        return "welcome";
    }
    // 删除会员
    @ResponseBody
    @RequestMapping("/deleteMember")
    public int deleteMember(int member_id) {
        int i;
        try {
            i = adminService.deleteMember(member_id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return i;
    }
    // 跳转到修改会员页面
    @RequestMapping("/toUpdateMember")
    public String toUpdateMember(Model model,int member_id) {
        System.out.println("修改的会员id："+member_id);
        Member members = adminService.findMemberById(member_id);
        List<ClassHour> ch =members.getClassHourList();
        if(ch.equals(null)){
            model.addAttribute("msg","没有私教");
        }else{
            model.addAttribute("msg","有私教");
        }
        model.addAttribute("ch", ch);
        model.addAttribute("m", members);
        sb="m-edit";
        model.addAttribute("sb", sb);
        return "show";
    }
    //日期相减
    public static long getDaySub(String beginDateStr,String endDateStr) {

        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate;
        Date endDate;
        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
            day = (endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("day:"+day);

        return day;
    }
    // 修改教练页面
    @RequestMapping("/updateMember")
    public String updateMember(Model model, Member member) {
        try{
            int i=0;
           // MemberCard mc=member.getMemberCard();
            long days = getDaySub(member.getMember_mcStart(), member.getMember_mcEnd());
            if (days < 90) member.getMemberCard().setMc_id(1);//设置会员卡id为1
            if (days >= 90) member.getMemberCard().setMc_id(2);//设置会员卡id为2
            if (days >= 365)member.getMemberCard().setMc_id(3);//设置会员卡id为3
            i= adminService.updateMember(member);
            if (i == 1) {
                model.addAttribute("msg", "修改成功！");
            } else
                model.addAttribute("msg", "修改失败！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "msg";
    }
    // 根据id展示会员卡内容
    @RequestMapping("/toShowMc")
    public String toShowMc(Model model,int mc_id) {
        MemberCard mcs = adminService.findMcById(mc_id);
        model.addAttribute("mc", mcs);
        sb="mc";
        model.addAttribute("sb", sb);
        return "show-byid";
    }
    // 根据id展示私教内容
    @RequestMapping("/toShowCh")
    public String toShowCh(Model model,MemberClassHour mch) {
        ClassHour chs = adminService.findChById(mch.getCh_id());
        System.out.println("会员卡："+mch.getMember_id()+"课时："+mch.getCh_id());
        if(mch.getMember_id()==0){
            model.addAttribute("msg", "会员查看课时详情");
        }
         mch=adminService.findMchById(mch);
        model.addAttribute("ch", chs);
        model.addAttribute("mch", mch);
        sb="ch";
        model.addAttribute("sb", sb);
        return "show-byid";
    }


    // 查询指定账户的信息跳转到修改密码页面
    @RequestMapping("/toUpdatePassword")
    public String toUpdatePassword(Model model) {
        sb="pwd";
        model.addAttribute("sb", sb);
        return "welcome";
    }
    // 管理员更改个人密码
    @RequestMapping("/updatePassword")
    @ResponseBody
    public int updatePassword(Model model, User user, HttpSession session) {
        int i;
        try {
            i = adminService.updatePassword(user);
            if (i == 1) {
                session.setAttribute("a_pwd",user.getUser_pwd());
            }
        } catch (Exception e) {
            i = 0;
        }
        return i;
    }


	// 查询所有套餐信息（系统管理员）
	@RequestMapping("/findAllSm")
	public String findAllSm(Model model) {
		List<SetMeal> sms = adminService.findAllSm();
		System.out.println("套餐");
		model.addAttribute("sm", sms);
        List<SetMeal> onlysms = adminService.findAllChSm();
        model.addAttribute("onlysm", onlysms);
		sb="sm";
		model.addAttribute("sb", sb);
		return "welcome";
	}
	// 删除套餐
	@ResponseBody
	@RequestMapping("/deleteSm")
	public int deleteSm(int sm_id) {
		int i;
		try {
			i = adminService.deleteSm(sm_id);
            //还要删除t_setmealclasshour的关系
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return i;
	}
	// 跳转到增加套餐页面
	@RequestMapping("/toAddSm")
	public String toAddSm(Model model) {
		List<ClassHour> chs=adminService.findcAllCh();
		List<MemberCard> mcs=adminService.findAllMemberCard();
		model.addAttribute("ch", chs);
		model.addAttribute("mc", mcs);
		sb="sm-add";
		model.addAttribute("sb", sb);
		return "show";
	}
	// 增加套餐
	@RequestMapping("/addSm")
	public String addSm(Model model ,SetMeal sm) {
		try {
			int i = adminService.addSm(sm);
			//还要增加t_setmealclasshour的关系
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "套餐添加失败");
			toAddSm(model);
		}
		return "redirect:/admin/findAllSm";
	}
	// 跳转到修改套餐页面（还没做到这！！）
	@RequestMapping("/toUpdateSm")
	public String toUpdateSm(Model model,int sm_id) {
		System.out.println("修改的教练id："+sm_id);
		SetMeal sms = adminService.findSmById(sm_id);
		List<ClassHour> chs=adminService.findcAllCh();
		List<MemberCard> mcs=adminService.findAllMemberCard();
		model.addAttribute("ch", chs);
		model.addAttribute("mc", mcs);
		model.addAttribute("sm", sms);
		sb="sm-edit";
		model.addAttribute("sb", sb);
		return "show";
	}
	//通过id发现会员卡
	@ResponseBody
	@RequestMapping("/findMcById")
	public MemberCard findMcById(Model model,int mc_id) {
		MemberCard mcs=adminService.findMcById(mc_id);
		return mcs;
	}


}
	

