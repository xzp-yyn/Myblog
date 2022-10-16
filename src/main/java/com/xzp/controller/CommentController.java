package com.xzp.controller;

import com.xzp.entity.Comment;
import com.xzp.entity.DetailBlog;
import com.xzp.entity.User;
import com.xzp.service.BlogService;
import com.xzp.service.CommentService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/6 10:16
 * @Version 1.0
 */
@Controller
public class CommentController {

    @Autowired
    BlogService blogService;

    @Autowired
    private CommentService cservice;

    @Value("${avatar}")
    private String avatar;

    @GetMapping("/comment/{blogid}")
    public String comments(Model model, @PathVariable Long blogid){
        List<Comment> comments = cservice.listCommentByBlogId(blogid);
        model.addAttribute("comments",comments);
        return "blog::bloglist";
    }
    @PostMapping("/addcomment")
    public String addcomment(Comment comment, Model model, HttpSession session){
        Long blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(avatar);
        }
        if(comment.getParentComment().getId()!=null){
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        comment.setCreateTime(new Date());
        cservice.saveComment(comment);
        List<Comment> comments = cservice.listCommentByBlogId(blogId);
        model.addAttribute("comments",comments);
        return "blog::bloglist";
    }

    @GetMapping("/comment/delete/{id}")
    public String delete(@PathVariable("id")Long commentid,Model model,Comment comment) throws NotFoundException {
        comment=cservice.getCommentByid(commentid);
        Long blogId = comment.getBlogId();
        cservice.deleteComment(comment,commentid);
        DetailBlog detailBlog = blogService.detailBlog(blogId);
        List<Comment> comments = cservice.listCommentByBlogId(blogId);
        model.addAttribute("blog",detailBlog);
        model.addAttribute("comments",comments);
        return "blog";
    }

}
