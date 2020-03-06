package com.xliu.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/6 16:30
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @RequestMapping("/blogs")
    public String blogs(){
        return "admin/blogs";
    }
}
