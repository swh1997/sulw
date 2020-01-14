package cn.dgut.jsf.controller;

import cn.dgut.jsf.bean.Admin;
import cn.dgut.jsf.bean.Inform;
import cn.dgut.jsf.bean.Member;
import cn.dgut.jsf.bean.MemberCard;
import cn.dgut.jsf.service.AdminService;
import cn.dgut.jsf.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/inform")
public class InformController {
	@Autowired
	private InformService informService;
	private String sb;
	// 查询管理员发送的所有通知
	@RequestMapping("/findInformByAdmin")
	public String findInformByAdmin(Model model, HttpSession session) {
		try {
			List<Inform> informs=informService.findAllInform();
			sb="inform";
			model.addAttribute("sb",sb);
			model.addAttribute("receiveInform",informs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "welcome";
	}
	// 展示通知内容
	@RequestMapping("/toShowInform")
	public String toShowInform(Model model,int inform_id) {
		try {
			Inform inform = informService.findInformById(inform_id);
			informService.changeRead(inform_id);
			sb="inform";
			model.addAttribute("sb",sb);
			model.addAttribute("inform", inform);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show-byid";
	}
	// 跳转去添加通知
	@RequestMapping("/toAddInform")
	public String toAddInform(Model model) {
		try {
			List<MemberCard> mcs = informService.findAllMcName();
			sb="inform-add";
			model.addAttribute("sb",sb);
			model.addAttribute("mc", mcs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	// 根据会员卡查询人员
	@ResponseBody
	@RequestMapping("/findMemberByMcId")
	public List<Member> findMemberByMcId(Integer mc_id) {
		System.out.println(mc_id);
		List<Member> members = informService.findMenberByMcId(mc_id);
		return members;
	}
	// 发送通知并且添加接收表信息
	@RequestMapping("/addInform")
	public String addInform(Inform inform,@RequestParam("file") MultipartFile file) {
		try {
			// 检查是否需要上传文件
			// 判断是否有文件
			if (!file.isEmpty()) {
				// 上传文件路径
				String path = "F:/jsf/src/main/resources/files/inform";
				// 上传文件名
				String filename = file.getOriginalFilename();
				File filepath = new File(path, filename);
				// 判断路径是否存在，如果不存在就创建一个
				if (!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				// 将上传文件保存到一个目标文件夹里
				file.transferTo(new File(path + File.separator + filename));
				inform.setInform_file(filename);// 通知中添加附件的名字
			} // 上传文件操作结束
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			inform.setInform_time(df.format(new Date()));//设置通知时间
			int i = informService.addInform(inform);// 添加通知消息
			System.out.println("i=" + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/inform/findInformByAdmin";
	}
	// 下载通知中的附件
	@RequestMapping("/downloadInformFile")
	public void downloadInformFile(@RequestParam("inform_file") String inform_file, HttpServletRequest request, HttpServletResponse response) {
		try {
			String path = "F:/jsf/src/main/resources/files/inform"+  File.separator  + inform_file;
			// 获取输入流
			InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
			// 转码，免得文件名中文乱码
			inform_file = URLEncoder.encode(inform_file, "UTF-8");
			// 设置文件下载头
			response.addHeader("Content-Disposition", "attachment;filename=" + inform_file);
			// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
			response.setContentType("multipart/form-data");
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			int len = 0;
			while ((len = bis.read()) != -1) {
				out.write(len);
				out.flush();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 查询会员自身收到的所有通知并分类(前端)
	@RequestMapping("/findMyselfInformWeb")
	public String findMyselfInformWeb(Model model, HttpSession session) {
		Member self = (Member) session.getAttribute("self");
		try {
			List<Inform> allInform = informService.findMemberReceiveInform(self.getMember_id());
			List<Inform> InformNotRead = informService.findMemberReceiveInformNotRead(self.getMember_id());
			List<Inform> InformRead = informService.findMemberReceiveInformRead(self.getMember_id());
			model.addAttribute("notRead", InformNotRead);
			model.addAttribute("Read", InformRead);
			model.addAttribute("allInform", allInform);
			sb="inform";
			model.addAttribute("sb",sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "information";
	}
	// 删除通知信息
	@ResponseBody
	@RequestMapping("/deleteInform")
	public int deleteInform(int inform_id) {
		int i;
		try {
			i = informService.deleteInform(inform_id);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

}
	

