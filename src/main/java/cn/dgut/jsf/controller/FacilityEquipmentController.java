package cn.dgut.jsf.controller;

import cn.dgut.jsf.bean.FacilityEquipment;
import cn.dgut.jsf.service.FacilityEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/facilityEquipment")
public class FacilityEquipmentController {
	@Autowired
	private FacilityEquipmentService facilityEquipmentService;
	private String sb;
	// 查询所有正在线的场地设备信息（前端）
	@RequestMapping("/findUpFacilityEquipmentWeb")
	public String findUpJobWeb(Model model) {
		try {
			List<FacilityEquipment> facilityEquipments = facilityEquipmentService.findFacilityEquipmentUp();
			model.addAttribute("equipment", facilityEquipments);
            sb="facilityequipment";
            model.addAttribute("sb", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "information";
	}
	// 根据名称查询场地设备信息（前端）
	@RequestMapping("/findFacilityEquipmentByTitleWeb")
	public String findJobByTitleWeb(Model model,String condition, String input) {
		try {
			List<FacilityEquipment> facilityEquipments = facilityEquipmentService.findFacilityEquipmentByTitle(condition, input);
			model.addAttribute("equipment", facilityEquipments);
            sb="facilityequipment";
            model.addAttribute("sb", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "information";
	}
	// 已审核的列表展示活动详情
	@RequestMapping("/toShowFacilityEquipment")
	public String toShowActivity(Model model,int equ_id) {
		try {
			FacilityEquipment equ = facilityEquipmentService.findFacilityEquipmentById(equ_id);
			model.addAttribute("equ", equ);
			sb="facilityequipment";
			model.addAttribute("sb", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show-byid";
	}


}
	

