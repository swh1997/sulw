package cn.dgut.jsf.controller;

import cn.dgut.jsf.bean.Activity;
import cn.dgut.jsf.bean.Coach;
import cn.dgut.jsf.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    private String sb;
    // 查询所有正在举行的活动信息(前端)
    @RequestMapping("/findUpActivityWeb")
        public String findUpActivityWeb(Model model) {
            try {
                List<Activity> acts = activityService.findActivityUp();
                System.out.println(acts.get(0).getCoach().getCoach_name()+acts.get(0).getAct_id()+acts.get(0).getCoach().getCoach_id());
                model.addAttribute("activity", acts);
                sb="activity";
                model.addAttribute("sb", sb);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "information";
    }
    // 根据活动主题查询正在举行的活动信息(前端)
    @RequestMapping("/findUpActivityByTitleWeb")
    public String findUpActivityByTitleWeb(Model model,  String condition ,String input) {
        try {
            System.out.println(condition+input);
            List<Activity> act = activityService.findActivityUpByAct_name(condition, input);
            model.addAttribute("activity", act);
            sb="activity";
            model.addAttribute("sb", sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "information";
    }
    // 已审核的列表展示活动详情
    @RequestMapping("/toShowActivity")
    public String toShowActivity(Model model,int act_id) {
        try {
            Activity act = activityService.findActivityById(act_id);
            model.addAttribute("act", act);
            sb="activity";
            model.addAttribute("sb", sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "show-byid";
    }
    // 查询所有的活动信息（管理员）
    @RequestMapping("/findAllActivity")
    public String findAllActivity(Model model) {
        try {
            List<Activity> act = activityService.findActivityUp();
            model.addAttribute("act", act);
            sb="activity";
            model.addAttribute("sb", sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "welcome";
    }
    // 活动状态的转变（活动的上线和下架）
    @ResponseBody
    @RequestMapping("/updateActivityState")
    public int updateActivityState(Activity act) {
        int i;
        try {
            i = activityService.updateActivityState(act);
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        return i;
    }
    // 跳转去添加通知
    @RequestMapping("/toAddActivity")
    public String toAddInform(Model model) {
        try {
            List<Coach> coas = activityService.findAllCoaName();
            sb="act-add";
            model.addAttribute("sb",sb);
            model.addAttribute("coa", coas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "show";
    }
    // 根据教练特长查询教练
    @ResponseBody
    @RequestMapping("/findCoachByCoaExpert")
    public List<Coach>  findCoachByCoaExpert(String coach_expert) {
        System.out.println(coach_expert);
        List<Coach> coaches = activityService.findCoachByCoaExpert(coach_expert);
        return coaches;
    }
  //新增活动
    @RequestMapping("/addActivity")
    public String  addActivity(Activity activity) {
        int i;
        try{
        i=activityService.addActivity(activity);
    } catch (Exception e) {
        e.printStackTrace();
    }
		return "redirect:/activity/findAllActivity";

    }

}
