package cn.dgut.jsf.controller;

import cn.dgut.jsf.bean.*;
import cn.dgut.jsf.service.SetMealService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private SetMealService setMealService;

    @RequestMapping("/jsppay")
    public String toLoginWeb() {
        return "/pay";
    }
/*
    @ResponseBody
    @RequestMapping("/pay")
    public void payController(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig2.CHARSET, AlipayConfig2.ALIPAY_PUBLIC_KEY, AlipayConfig2.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
        //商品描述，可空
        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig2.CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }
*/

@ResponseBody
@PostMapping("/AliPay")
public String goPay(HttpServletResponse response,HttpSession session,@RequestParam("sm_id") Integer sm_id) throws IOException {
    System.out.println("套餐ID："+sm_id);
   // Member m = (Member) session.getAttribute("self");
    String m_id=(String) session.getAttribute("m_id");
    if (m_id==null){
       return "用户没有登录";
    }
    SetMeal setMeal = setMealService.findSetMealById(sm_id);//找到套餐
    //获得初始化的AlipayClient
    AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

    //设置请求参数
    AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
    alipayRequest.setReturnUrl(AlipayConfig.return_url);
    alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
       Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String out_trade_no =sdf.format(date);
        //付款金额，必填
        String total_amount = String.valueOf(setMeal.getSm_price());
        //订单名称，必填
        String subject = setMeal.getSm_name();
        //商品描述，可空
        String body = setMeal.getSm_name();
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
       // int i=-1;
    try {
        String result= alipayClient.pageExecute(alipayRequest).getBody();
            System.out.println(result);
        Boolean flag=setMealService.insertFee(m_id,setMeal.getSm_price(),out_trade_no,setMeal.getSm_id());//插入订单
         if(!flag){
             return"创建fee失败";
         }
            /*
            MemberCard stmc = setMeal.getMemberCard();//套餐里的会员卡
            System.out.println("会员id："+m.getMember_id()+"会员卡日期数量："+stmc.getMc_quantity());
            MemberCard mc=m.getMemberCard();//会员卡
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
                i=setMealService.buySetMealTwoById(m);//插入
                System.out.println("没有会员卡:"+m.getMember_mcEnd());
            }
            else {
                if (stmc.getMc_quantity() == 365)
                    m.setMember_mcEnd(addMonth(m.getMember_mcEnd(), 12));
                if (stmc.getMc_quantity() == 90)
                    m.setMember_mcEnd(addMonth(m.getMember_mcEnd(), 3));
                if (stmc.getMc_quantity() == 30)
                    m.setMember_mcEnd(addMonth(m.getMember_mcEnd(), 1));
                long days=getDaySub(m.getMember_mcStart(),m.getMember_mcEnd());
                if(mc.getMc_id()==1&&days>90)mc.setMc_id(2);//设置会员卡id为2
                if(mc.getMc_id()==2&&days>365)mc.setMc_id(3);//设置会员卡id为3
                i=setMealService.buySetMealTwoByIdTwo(m);//修改
                System.out.println("有会员卡:"+m.getMember_mcEnd());
            }
            List<SetMealClassHour> smchs = null;
            for (ClassHour smch : setMeal.getClassHourList()) {//会员买的套餐的课时类型
                smchs = setMealService.findClassHourQuantityById(sm_id);//发现套餐中的课时的数量
                for (SetMealClassHour ch : smchs) {
                    if (ch.getCh_id() == smch.getCh_id()) {//找到套餐课时对应的数量后
                        System.out.println("发现套餐中的课时的数量后的那个课时id："+ch.getCh_id());
                        if (setMealService.findMchById(ch, m.getMember_id()) == null) {//发现会员该课时修改数量，否则插入。
                            i=setMealService.buySetMealById(ch, m.getMember_id());//插入
                        }
                        else {
                            i=setMealService.buySetMealByIdTwo(ch, m.getMember_id());//修改
                        }
                    }
                }
            }

            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            response.getWriter().write(result);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
             */
             return result;
        }catch (AlipayApiException e) {
        System.out.println("订单请求错误");
        e.printStackTrace();
        return "fail";
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

}
