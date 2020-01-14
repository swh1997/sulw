package cn.dgut.jsf.controller;

import cn.dgut.jsf.bean.*;
import cn.dgut.jsf.service.AdminService;
import cn.dgut.jsf.service.SetMealService;
import cn.dgut.jsf.service.UserService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jnlp.ClipboardService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/setMeal")
public class SetMealController {
	@Autowired
	private SetMealService setMealService;
	private String sb;
	// 查询所有正在举行的套餐信息(前端)
	@RequestMapping("/findUpSetMealWeb")
	public String findUpActivityWeb(Model model) {
		try {
			List<SetMeal> setMeals = setMealService.findSetMealUp();
			model.addAttribute("setMeal", setMeals);
			sb="setMeal";
			model.addAttribute("sb", sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "information";
	}
	//套餐课时数量
	@ResponseBody
	@RequestMapping("/toClassHourQuantity")
	public List<SetMealClassHour> findUpActivityWeb(int sm_id) {
		List<SetMealClassHour> smchs=null;
		try {
			smchs = setMealService.findClassHourQuantityById(sm_id);
			System.out.println("课时数量:"+smchs.get(0).getSmch_quantity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return smchs;
	}
	//实现日期加一天的方法
	public static String addDay(String s, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s));
			cd.add(Calendar.DATE, n);//增加一天
			//cd.add(Calendar.MONTH, n);//增加一个月

			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
	}
	//实现日期加一月的方法
	public static String addMonth(String s, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s));
			//cd.add(Calendar.DATE, n);//增加一天
			cd.add(Calendar.MONTH, n);//增加一个月
			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
	}
	//实现日期加一年的方法
	public static String addYear(String s, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s));
			//cd.add(Calendar.DATE, n);//增加一天
			cd.add(Calendar.YEAR, n);//增加一年

			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
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
	//套餐购买
	@PostMapping("/toSetMealBuyWeb")
    public String return_url(HttpServletRequest request, HttpSession session) throws Exception {
        System.out.println("支付宝沙箱异步跳转！！！！！！！！！！！");
        String m_id = (String) session.getAttribute("m_id");
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        System.out.println("map信息：" + requestParams.toString());
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";

            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            System.out.println("异步验签---- " + valueStr);
            params.put(name, valueStr);
        }
        System.out.println(params.size());
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.sign_type); //调用SDK验证签名
            if(signVerified) {
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //付款金额
        String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Fee fee = setMealService.getPayInfoByCID(m_id, sdf.format(out_trade_no));
        if (fee != null) {//支付成功
            int sm_id = fee.getSetMeal().getSm_id();
            int i = -1;
            Member m = (Member) session.getAttribute("self");
            SetMeal setMeal = setMealService.findSetMealById(sm_id);//找到套餐
            MemberCard stmc = setMeal.getMemberCard();//套餐里的会员卡
            System.out.println("会员id：" + m.getMember_id() + "会员卡日期数量：" + stmc.getMc_quantity());
            MemberCard mc = m.getMemberCard();//会员卡
            if (mc.getMc_id() == 0) {//会员已经有该会员卡，就相加，否则插入，超过则改变卡名。
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
                mc.setMc_id(setMeal.getMemberCard().getMc_id());//设置会员卡id为套餐中会员卡的id
                m.setMember_mcStart(df.format(new Date()));//设置购买套餐时间（即会员卡开始时间）
                if (stmc.getMc_quantity() == 365)
                    m.setMember_mcEnd(addMonth(df.format(new Date()), 12));//设置会员卡结束时间
                if (stmc.getMc_quantity() == 90)
                    m.setMember_mcEnd(addMonth(df.format(new Date()), 3));
                if (stmc.getMc_quantity() == 30)
                    m.setMember_mcEnd(addMonth(df.format(new Date()), 1));
                m.setMemberCard(mc);//设置会员的会员卡
                i = setMealService.buySetMealTwoById(m);//插入
                System.out.println("没有会员卡:" + m.getMember_mcEnd());
            } else {
                if (stmc.getMc_quantity() == 365)
                    m.setMember_mcEnd(addMonth(m.getMember_mcEnd(), 12));
                if (stmc.getMc_quantity() == 90)
                    m.setMember_mcEnd(addMonth(m.getMember_mcEnd(), 3));
                if (stmc.getMc_quantity() == 30)
                    m.setMember_mcEnd(addMonth(m.getMember_mcEnd(), 1));
                long days = getDaySub(m.getMember_mcStart(), m.getMember_mcEnd());
                if (mc.getMc_id() == 1 && days > 90) mc.setMc_id(2);//设置会员卡id为2
                if (mc.getMc_id() == 2 && days > 365) mc.setMc_id(3);//设置会员卡id为3
                i = setMealService.buySetMealTwoByIdTwo(m);//修改
                System.out.println("有会员卡:" + m.getMember_mcEnd());
            }
            List<SetMealClassHour> smchs = null;
            for (ClassHour smch : setMeal.getClassHourList()) {//会员买的套餐的课时类型
                smchs = setMealService.findClassHourQuantityById(sm_id);//发现套餐中的课时的数量
                for (SetMealClassHour ch : smchs) {
                    if (ch.getCh_id() == smch.getCh_id()) {//找到套餐课时对应的数量后
                        System.out.println("发现套餐中的课时的数量后的那个课时id：" + ch.getCh_id());
                        if (setMealService.findMchById(ch, m.getMember_id()) == null) {//发现会员该课时修改数量，否则插入。
                            i = setMealService.buySetMealById(ch, m.getMember_id());//插入
                        } else {
                            i = setMealService.buySetMealByIdTwo(ch, m.getMember_id());//修改
                        }
                    }
                }
            }
            i = 1;
            return "success";
                }else {
                   return "failure";
                }
            }else {
                System.out.println("验签失败");
                return "failure";
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("验证错误");
            return "failure";
        }
        }

    @GetMapping("/return")
    public String returns(HttpServletRequest request,HttpSession session,Model model)throws Exception {
        System.out.println("支付宝沙箱同步跳转！！！！！！！！！！！");
        String m_id = (String) session.getAttribute("m_id");
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        System.out.println("map信息：" + requestParams.toString());
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";

            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            System.out.println("同步验签---- " + valueStr);
            params.put(name, valueStr);
        }
        System.out.println(params.size());
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.sign_type); //调用SDK验证签名
            if (signVerified) {
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //付款金额
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
                  System.out.println("订单号："+ out_trade_no);
                Fee fee = setMealService.getPayInfoByCID(m_id, out_trade_no);
                if (fee != null) {//支付成功
                    int sm_id = fee.getSetMeal().getSm_id();
                    int i = -1;
                    Member m=setMealService.findMNameByUserId(m_id);
                    System.out.println("登录时用户id："+m_id+"会员id："+m.getMember_id()+"会员卡id"+m.getMemberCard().getMc_id());
                    if(setMealService.findChByMemberId(m.getMember_id())>0) {
                        m = setMealService.findClassHourUpByUserId(m_id);
                        System.out.println("登录时用户id："+m.getUser().getUser_id()+"会员id："+m.getMember_id());
                    }
                    else{
                        System.out.println("会员没有购买任何课时.");
                    }
                    SetMeal setMeal = setMealService.findSetMealById(sm_id);//找到套餐
                    MemberCard stmc = setMeal.getMemberCard();//套餐里的会员卡
                    System.out.println("会员id：" + m.getMember_id() + "会员卡日期数量：" + stmc.getMc_quantity());
                    MemberCard mc = m.getMemberCard();//会员卡
                    if (mc.getMc_id() == 0) {//会员已经有该会员卡，就相加，否则插入，超过则改变卡名。
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
                        mc.setMc_id(setMeal.getMemberCard().getMc_id());//设置会员卡id为套餐中会员卡的id
                        m.setMember_mcStart(df.format(new Date()));//设置购买套餐时间（即会员卡开始时间）
                        if (stmc.getMc_quantity() == 365)
                            m.setMember_mcEnd(addMonth(df.format(new Date()), 12));//设置会员卡结束时间
                        if (stmc.getMc_quantity() == 90)
                            m.setMember_mcEnd(addMonth(df.format(new Date()), 3));
                        if (stmc.getMc_quantity() == 30)
                            m.setMember_mcEnd(addMonth(df.format(new Date()), 1));
                        m.setMemberCard(mc);//设置会员的会员卡
                        i = setMealService.buySetMealTwoById(m);//插入
                        System.out.println("没有会员卡:" + m.getMember_mcEnd());
                    } else {
                        if (stmc.getMc_quantity() == 365)
                            m.setMember_mcEnd(addMonth(m.getMember_mcEnd(), 12));
                        if (stmc.getMc_quantity() == 90)
                            m.setMember_mcEnd(addMonth(m.getMember_mcEnd(), 3));
                        if (stmc.getMc_quantity() == 30)
                            m.setMember_mcEnd(addMonth(m.getMember_mcEnd(), 1));
                        long days = getDaySub(m.getMember_mcStart(), m.getMember_mcEnd());
                        if (mc.getMc_id() == 1 && days > 90) mc.setMc_id(2);//设置会员卡id为2
                        if (mc.getMc_id() == 2 && days > 365) mc.setMc_id(3);//设置会员卡id为3
                        i = setMealService.buySetMealTwoByIdTwo(m);//修改
                        System.out.println("有会员卡:" + m.getMember_mcEnd());
                    }
                    List<SetMealClassHour> smchs = null;
                    for (ClassHour smch : setMeal.getClassHourList()) {//会员买的套餐的课时类型
                        smchs = setMealService.findClassHourQuantityById(sm_id);//发现套餐中的课时类型的数量
                        for (SetMealClassHour ch : smchs) {
                            if (ch.getCh_id() == smch.getCh_id()) {//找到套餐课时类型对应的数量后
                                System.out.println("发现套餐中的课时类型的数量后的那个课时id：" + ch.getCh_id());
                                if (setMealService.findMchById(ch, m.getMember_id()) == null) {//发现会员该课时类型，修改数量，否则插入。
                                    i = setMealService.buySetMealById(ch, m.getMember_id());//插入
                                } else {
                                    i = setMealService.buySetMealByIdTwo(ch, m.getMember_id());//修改
                                }
                            }
                        }
                    }
                    i = 1;
                    List<SetMeal> setMeals = setMealService.findSetMealUp();
                    model.addAttribute("setMeal", setMeals);
                    sb="setMeal";
                    model.addAttribute("sb", sb);
                    return "information";
                }else {
                    return "failure";
                }
            } else {
                System.out.println("验签失败");
               return "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("验证错误");
            return "failure";
        }
    }

}
	

