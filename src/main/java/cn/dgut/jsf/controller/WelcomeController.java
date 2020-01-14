package cn.dgut.jsf.controller;

import cn.dgut.jsf.bean.Activity;
import cn.dgut.jsf.bean.FacilityEquipment;
import cn.dgut.jsf.bean.Notice;
import cn.dgut.jsf.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	@Autowired
	private WelcomeService welcomeService;
	private String sb;
	// 登录成功跳转
	@RequestMapping("/member")
	public String forWelcome() {
		return "index";
	}
	// 查询所有正在举行的活动信息
	@RequestMapping("/activity")
	@ResponseBody
	public List<Activity> activity(Model model) {
		System.out.println("11111");
		List<Activity> acts = null;
		try {
			acts = welcomeService.findActivityUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acts;
	}
	// 查询所有可用设备场地的信息
	@RequestMapping("/equipment")
	@ResponseBody
	public List<FacilityEquipment> job(Model model) {
		List<FacilityEquipment> facilityEquipments = null;
		try {
			facilityEquipments = welcomeService.findEquipmentUp();
			System.out.println("11111");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return facilityEquipments;
	}
	// 查询所有生效的公告信息
	@RequestMapping("/notice")
	@ResponseBody
	public List<Notice> notice(Model model) {
		List<Notice> notices = null;
		try {
			notices = welcomeService.findNoticeUp();
             System.out.println("11111");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notices;
	}
	// 查询身份证对应的地区
	@RequestMapping(value="/findNaticePlace",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findNaticePlace(HttpServletRequest request ) {
		String idcard= request.getParameter("idcard");
		int nativePlaceCode = Integer.parseInt(idcard.substring(0, 6));
		String naticePlace = NaticePlace.getNativePlace(nativePlaceCode);
		System.out.println(naticePlace);
		return naticePlace;
	}

	// 管理员首页统计的信息
	@RequestMapping("/adminWelcome")
	public String adminWelcome(Model model) {
		try {
			int coa=welcomeService.findCoachs();
			int u=welcomeService.findUsers();
			int m=welcomeService.findMembers();
			int ad=welcomeService.findAdmins();
			int ac=welcomeService.findActivities();
			int fe=welcomeService.findFes();
			int n=welcomeService.findNotices();
			sb="welcome";
			model.addAttribute("sb",sb);
			model.addAttribute("coa", coa);
			model.addAttribute("u", u);
			model.addAttribute("m", m);
			model.addAttribute("ad", ad);
			model.addAttribute("ac", ac);
			model.addAttribute("fe", fe);
			model.addAttribute("n", n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "welcome";
	}


}
	

