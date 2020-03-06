package com.xliu.web.admin;

import com.xliu.bean.User;
import com.xliu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/6 16:05
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String loginPage(HttpSession session){
        if(session.getAttribute("user")!=null){
            return "redirect:/admin/blogs";
        }

        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        if(session.getAttribute("user")!=null){
            return "redirect:/admin/blogs";
        }
        User user = userService.checkUser(username,password);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin/";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin/";
    }

}
