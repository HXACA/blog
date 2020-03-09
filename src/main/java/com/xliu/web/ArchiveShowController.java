package com.xliu.web;

import com.xliu.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/9 13:26
 */
@Controller
public class ArchiveShowController {
    @Autowired
    BlogService blogService;

    @RequestMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }
}
