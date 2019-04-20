package com.hg.seckill.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by YE
 * 2018-11-26 15:28
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String dologin(String username, String password, Map<String, Object> map){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        }catch (UnknownAccountException | IncorrectCredentialsException e1) {
            if(username != null && !username.equals("")){
                map.put("username", username);
            }
            map.put("error", new Object());
            return "login";
        }
        return "redirect:goods";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:login";
    }
}
