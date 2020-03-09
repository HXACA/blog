package com.xliu.web;

import com.xliu.bean.Comment;
import com.xliu.bean.User;
import com.xliu.service.BlogService;
import com.xliu.service.CommentService;
import com.xliu.service.TagService;
import com.xliu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/9 12:59
 */
@Controller
public class CommentController {

    @Autowired
    BlogService blogService;

    @Autowired
    CommentService commentService;

    @RequestMapping("comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog::commentList";
    }

    @RequestMapping("comments")
    public String post(Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        comment.setAvatar("/images/avatar.png");
        comment.setAdminComment(false);
        comment.setBlog(blogService.getBlog(comment.getBlog().getId()));
        if(user!=null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" +  comment.getBlog().getId();
    }
}
