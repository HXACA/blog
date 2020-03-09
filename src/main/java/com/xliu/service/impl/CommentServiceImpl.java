package com.xliu.service.impl;

import com.xliu.bean.Comment;
import com.xliu.dao.CommentDao;
import com.xliu.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/9 10:35
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        List<Comment> comments = commentDao.findByBlogIdAndParentCommentNull(blogId, sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentId = comment.getParentComment().getId();
        if(parentId!=-1){
            comment.setParentComment(commentDao.findOne(parentId));
        }else{
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentDao.save(comment);
    }

    private List<Comment>eachComment(List<Comment> comments){
        List<Comment> result = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            result.add(c);
        }
        for (Comment comment : result) {
            List<Comment>reply = comment.getReplyComments();
            for (Comment reply1 : reply) {
                solve(reply1);
            }
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return result;
    }

    private List<Comment>tempReplys = new ArrayList<>();

    private void solve(Comment comment){
        tempReplys.add(comment);
        if(comment.getReplyComments().size()>0){
            List<Comment>replys = comment.getReplyComments();
            for (Comment reply : replys) {
                solve(reply);
            }
        }
    }

}
