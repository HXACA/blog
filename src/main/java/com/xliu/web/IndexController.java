package com.xliu.web;

import com.xliu.bean.Comment;
import com.xliu.bean.User;
import com.xliu.service.BlogService;
import com.xliu.service.CommentService;
import com.xliu.service.TagService;
import com.xliu.service.TypeService;
import com.xliu.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/5 18:54
 */
@Controller
public class IndexController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;


    @RequestMapping("/")
    public String index(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.getTopType(6));
        model.addAttribute("tags", tagService.getTopTags(6));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlog(6));
        return "index";
    }

    @RequestMapping("/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model, @RequestParam String query) {
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @RequestMapping("/blog/{id}")
    public String blogDetail(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvertBlog(id));
        return "blog";
    }

    @RequestMapping("about")
    public String about(){
        return "about";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlog(3));
        return "_fragments::newblogList";
    }

}
