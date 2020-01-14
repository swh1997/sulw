package cn.dgut.jsf.controller;

import cn.dgut.jsf.bean.Admin;
import cn.dgut.jsf.bean.Notice;
import cn.dgut.jsf.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	private String sb;
	// 查询所有公告信息（会员前端）
	@RequestMapping("/findNoticeWeb")
	public String findNoticeWeb(Model model) {
		List<Notice> notices = noticeService.findAllNotice();
		model.addAttribute("notice", notices);
		sb="notice";
		model.addAttribute("sb", sb);
		return "information";
	}
	// 查询所有公告信息（系统管理员）
	@RequestMapping("/findAllNotice")
	public String findAllNotice(Model model) {
		List<Notice> notices = noticeService.findAllNotice();
		System.out.println("系统公告");
		model.addAttribute("notice", notices);
		sb="notice";
		model.addAttribute("sb", sb);
		return "welcome";
	}

	// 根据id展示公告内容
	@RequestMapping("/toShowNotice")
	public String toShowNotice(Model model,int notice_id) {
		Notice notices = noticeService.findNoticeById(notice_id);
		model.addAttribute("notice", notices);
		sb="notice";
		model.addAttribute("sb", sb);
		return "show-byid";
	}
	// 查询出指定的公告信息准备修改
	@RequestMapping("/toUpdateNotice")
	public String toUpdateNotice(Model model, int notice_id) {
		Notice notices = noticeService.findNoticeById(notice_id);
		model.addAttribute("notice", notices);
		sb="notice-edit";
		model.addAttribute("sb", sb);
		return "show";
	}
	// 修改公告信息
	@RequestMapping("/updateNotice")
	public String updateNotice(HttpServletRequest request, @RequestParam("file") MultipartFile file, Notice notice, Model model) {
		try {
			if (!file.isEmpty()) {
				// 上传文件路径
				String path = "F:/jsf/src/main/resources/files/notice";
				System.out.println("上传的文件路径："+path);
				// 上传文件名
				String filename = file.getOriginalFilename();
				File filepath = new File(path, filename);
				// 判断路径是否存在，如果不存在就创建一个
				if (!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				// 将上传文件保存到一个目标文件夹里
				file.transferTo(new File(path + File.separator + filename));//File.separator 在 UNIX 系统上，此字段的值为 '/'；在 Microsoft Windows 系统上，它为 '\'。
				notice.setNotice_file(filename);
			}
			int i = noticeService.updateNotice(notice);
			if (i == 1) {
				model.addAttribute("msg", "修改成功！");
			} else
				model.addAttribute("msg", "修改失败！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "msg";
	}
	// 删除公告
	@ResponseBody
	@RequestMapping("/deleteNotice")
	public int deleteNotice(HttpServletRequest request) {
		String row = request.getParameter("notice_id");
		int i;
		try {
			i = noticeService.deleteNotice(row);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return i;
	}

	// 公告状态的转变（公告的撤下和发布）
	@ResponseBody
	@RequestMapping("/updateNoticeState")
	public int updateNoticeState(@RequestBody Notice notice) {
		int i;
		try {
					i = noticeService.updateNoticeState(notice);
		} catch (Exception e) {
			e.printStackTrace();
			i = 0;
		}
		return i;
	}
	// 跳转到增加公告页面
	@RequestMapping("/toAddNotice")
	public String toAddNotice(Model model) {
		sb="notice-add";
		model.addAttribute("sb", sb);
		return "show";
	}
	// 添加公告并上传附件
	@RequestMapping("/addNotice")
	public String addNotice(HttpServletRequest request, @RequestParam("file") MultipartFile file, Notice notice, Model model) {
		try {
			// 上传文件自动绑定到MultipartFile
			Date date = new Date();
			SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
			notice.setNotice_date(SDF.format(date.getTime()));
			// 如果文件名不为空，写入上传路径
			if (!file.isEmpty()) {
				// 上传文件路径
				String path = "F:/jsf/src/main/resources/files/notice";
				System.out.println("上传的文件路径："+path);
				// 上传文件名
				String filename = file.getOriginalFilename();
				File filepath = new File(path, filename);
				// 判断路径是否存在，如果不存在就创建一个
				if (!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				// 将上传文件保存到一个目标文件夹里
				file.transferTo(new File(path + File.separator + filename));
				notice.setNotice_file(filename);
			} else {

			}
			noticeService.addNotice(notice);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "公告添加失败");
			toAddNotice(model);
		}

		return "redirect:/notice/findAllNotice";
	}
	// 下载附件
	@RequestMapping("/downloadNoticeFile")
	public void downloadNoticeFile(@RequestParam("notice_file") String notice_file, HttpServletRequest request,
								   HttpServletResponse response) {
		try {
			String path = "F:/jsf/src/main/resources/files/notice" +  File.separator  + notice_file;
			// 获取输入流
			InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
			// 转码，免得文件名中文乱码
			notice_file = URLEncoder.encode(notice_file, "UTF-8");
			// 设置文件下载头
			response.addHeader("Content-Disposition", "attachment;filename=" + notice_file);
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





}
	

