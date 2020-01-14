package cn.dgut.jsf.controller;

import cn.dgut.jsf.bean.*;
import cn.dgut.jsf.service.CoachService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/coach")
public class CoachController {
	@Autowired
	private CoachService coachService;
	private String sb;
	// 查询所有教练(前端)
	@RequestMapping("/findUpCoachWeb")
	public String findAllAssociationForApplyWeb(Model model, HttpSession session) {
		List<Coach> coachs=null;
		try {
			coachs = coachService.findAllCoachUp();
			model.addAttribute("coach", coachs);
			sb="coach";
			model.addAttribute("sb", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "information";
	}
	// 根据活动主题查询教练信息(前端)
	@RequestMapping("/findUpCoachByTitleWeb")
	public String findUpActivityByTitleWeb(Model model,  String condition ,String input) {
		try {
			System.out.println(condition+input);
			List<Coach> coa = coachService.findCoachUpByCoach_name(condition, input);
			model.addAttribute("coach", coa);
			sb="coach";
			model.addAttribute("sb", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "information";
	}
	@RequestMapping(value="/toAppointWeb")
	public String toAppointWeb( Model model,int ch_id,int coach_id) {
		try{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
          System.out.println("课时id："+ch_id+"教练id："+coach_id);
		if(coachService.findMinTimeByCoachIdAndChId(coach_id, ch_id, df.format(new Date()), 1)!=null) {
			CoachAppoint camin = coachService.findMinTimeByCoachIdAndChId(coach_id, ch_id, df.format(new Date()), 1);
			CoachAppoint camax = coachService.findMaxTimeByCoachIdAndChId(coach_id, ch_id, df.format(new Date()), 1);
			String startTime = camin.getCa_startTime().substring(11, 13);
			String endTime = camax.getCa_endTime().substring(11, 13);
			System.out.println("刚开始最小时间段:" + startTime + "最大时间段:" + endTime);
			int i;
			if(startTime=="09"){i=Integer.parseInt(startTime.substring(1,2))+2;}
			else{i=Integer.parseInt(startTime)+2;}
			int j=Integer.parseInt(endTime)-1;
			StringBuilder sb;
			List<String> temp=new ArrayList<String>();
			while(i<j) {
				 sb= new StringBuilder(camin.getCa_startTime());
				sb.replace(11, 13, Integer.toString(i));
				if(coachService.findendTime(sb.toString())!=null) {
				}else{//没有发现该时段有预约
					System.out.println(sb.toString().substring(11,13));
					temp.add(sb.toString().substring(11,13));
					System.out.println(temp);
				}
				i++;
			}
			model.addAttribute("temp",temp);
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
		}
		else{
			System.out.println("刚开始最小时间段:" + 0 + "最大时间段:" + 0);
			model.addAttribute("startTime", 0);
			model.addAttribute("endTime", 0);
		}
			model.addAttribute("ch_id", ch_id);
		model.addAttribute("coach_id",coach_id);
		sb="coach";
		model.addAttribute("sb", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"show-byid";
	}
	@ResponseBody
	@RequestMapping(value="/toTime")
	public CoachAppoint toTime(Model model,@RequestBody CoachAppoint ca) {
		try{
			System.out.println("开始后选择的日期:"+ca.getCa_startTime()+"教练id:"+ca.getCoach_id()+"课时 id:"+ca.getClassHour().getCh_id());
			if(coachService.findMinTimeByCoachIdAndChId(ca.getCoach_id(),ca.getClassHour().getCh_id(),ca.getCa_startTime(),0)!=null) {
				CoachAppoint temp = coachService.findMinTimeByCoachIdAndChId(ca.getCoach_id(), ca.getClassHour().getCh_id(), ca.getCa_startTime(), 0);
				ca = coachService.findMaxTimeByCoachIdAndChId(ca.getCoach_id(), ca.getClassHour().getCh_id(), ca.getCa_startTime(), 0);
				ca.setCa_startTime(temp.getCa_startTime());
				String startTime = ca.getCa_startTime().substring(11, 13);
				String endTime = ca.getCa_endTime().substring(11, 13);
				System.out.println("开始后最小时间段:" + startTime + "最大时间段:" + endTime);
				int i;
				if(startTime=="09"){i=Integer.parseInt(startTime.substring(1,2))+2;}
				else{i=Integer.parseInt(startTime)+2;}
				int j=Integer.parseInt(endTime)-1;
				StringBuilder sb;
				List<String> te=new ArrayList<String>();
				while(i<j) {
					sb= new StringBuilder(ca.getCa_startTime());
					sb.replace(11, 13, Integer.toString(i));
					if(coachService.findendTime(sb.toString())!=null) {//该时间段有预约
					}else{
						te.add(sb.toString().substring(11,13));
						System.out.println(te);
					}
					i++;
				}
				ca.setTemp(te);
			}
			else{
				ca.setCa_endTime("0");//ca_startTime=选择的日期
			}
	} catch (Exception e) {
			e.printStackTrace();
	}
		return ca;
	}


	@ResponseBody
	@RequestMapping(value="/toCoachAppointWeb")
	public Integer toCoachAppointWeb(Model model,HttpSession session,@RequestBody CoachAppoint coachClassHour) {
		int i=-1;//会员没有任何课时类型
		System.out.println("会员选择的教练的课时类型id:"+coachClassHour.getClassHour().getCh_id()+"教练id："+coachClassHour.getCoach_id());
		try {
			Member m = (Member) session.getAttribute("self");//会员信息
			/*
			if(coachService.findCoachAppointByTime(coachClassHour.getCa_startTime(),m.getMember_id())!=null){
				model.addAttribute("ch_id",coachClassHour.getClassHour().getCh_id());
				model.addAttribute("coach_id",coachClassHour.getCoach_id());
				sb="coach";
				model.addAttribute("sb", sb);
				i=-3;
				return i;
			}
			*/
			MemberClassHour memberClassHour = null;
			if(m.getClassHourList()!=null) {
				System.out.println("session的会员课时类型id:" + m.getClassHourList().get(0).getCh_id() + "session的会员课时类型多少：" + m.getClassHourList().size());
			}
			List<MemberClassHour> ClassHourList = coachService.findAllMchByMemberId(m.getMember_id());//在表里发现会员的所有课时类型
			System.out.println("表里的会员第一个课时类型id:" + ClassHourList.get(0).getCh_id() + "表里的会员课时类型多少：" + ClassHourList.size());
			int b = coachClassHour.getClassHour().getCh_id();//会员选择的教练的课时类型id
			int c = ClassHourList.size();//表里的会员课时类型多少
			if (c != 0) i = 0;
			if (i==0){
				for (; i < c; i++) {
					if (ClassHourList.get(i).getCh_id() == b) {//符合
						memberClassHour = coachService.findMchRemainingQuantity(m.getMember_id(), ClassHourList.get(i).getCh_id());//发现会员符合的课时类型的剩余课时
						if (memberClassHour.getMch_remainingQuantity() <= 1) {
							coachService.delMemberClassHour(m.getMember_id(), ClassHourList.get(i).getCh_id());//删除课时类型
						}
						else if(memberClassHour!=null||memberClassHour.getMch_remainingQuantity()>1) {
							coachService.reduceMchRemainingQuantity(m.getMember_id(), b);//减少课时
						}
						System.out.println("开始："+coachClassHour.getCa_startTime()+"结束："+coachClassHour.getCa_endTime());
						coachClassHour.setMember_id(m.getMember_id());
						coachService.CoachAppointWeb(coachClassHour);//预约教练
						break;
					}
				}
		}
            System.out.println("循环到第几个符合:"+i+"表里的会员课时类型多少:"+c);
			if(i==c)i=-2;//不符合
			sb="coach";
			model.addAttribute("sb", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
            return i;
	}



}
	

