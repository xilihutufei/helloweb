package pre.fei.controllers;


import com.sun.java.swing.plaf.motif.MotifSplitPaneDivider;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pre.fei.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@ResponseBody
public class FFController {

    @Autowired
    IUserService service;

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
        boolean login = service.login(username, passwd);
        if (login){
            modelAndView.setViewName("/pc/demo.html");
        }else {
            modelAndView.setViewName("/pc/error.html");
        }
        return modelAndView;
    }

}
