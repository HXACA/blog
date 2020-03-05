package com.xliu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/5 18:54
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
