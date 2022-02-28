package pre.fei.controllers;


import com.sun.java.swing.plaf.motif.MotifSplitPaneDivider;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pre.fei.config.EvmConfig;
import pre.fei.service.IUserService;
import pre.fei.vo.IConst;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class FFController {

    @Autowired
    IUserService service;


    @Autowired
    EvmConfig config;

    @RequestMapping("/birthday/index")
    public ModelAndView ffINdex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/pc/birthday_happy_index.html");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        String username = request.getParameter("username");
        String passwd = request.getParameter("password");
        System.out.println("username = " + username + ", passwd = " + passwd);
        boolean login = service.login(username, passwd);
        if (login){
            Cookie cookie = new Cookie(IConst.COOKIE_USER, username);
            response.addCookie(cookie);
            modelAndView.setViewName("/pc/main.html");
        }else {
            modelAndView.setViewName("/pc/error.html");
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/getPicByUserName")
    public String getPicByname(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        if (name == null || name.length() == 0){
            return "";
        }
        String picByUsername = service.getPicByUsername(name);
        if (picByUsername == null){
            return "";
        }
        return picByUsername;
    }

}
