package com.team.house.pcontroller;

import com.team.house.sms.SendMsgUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class SmsController {

    //发请求，发送验证码
    @RequestMapping("sendCode")
    @ResponseBody
    public String sendCode(String tel, HttpSession session){
        //生成四位随机数
        int code = (int)Math.round(Math.random()+1*1000);
        //将验证码保存到session中，并设置存活时长
        session.setAttribute("code",code);
        session.setMaxInactiveInterval(10*60);  //秒

        String msg = "验证码是："+code+"请不要让其他人获取";

        //发送消息
        SendMsgUtil sendMsgUtil = new SendMsgUtil();
        int result = sendMsgUtil.sendMsg(tel, msg);
        return "{\"result\":"+result+"}";
    }

    @RequestMapping("login2")
    @ResponseBody
    public String longin2(String inputCode, HttpSession session){
        //输入的验证码与手机的验证码做比较
        String code = session.getAttribute("code").toString();
        if (!code.equals(inputCode)){
            return "redirect:login2.jsp";
        }else{
            //通过手机号，查询当前用户信息
            //...
            return "page/success";
        }
    }
}
