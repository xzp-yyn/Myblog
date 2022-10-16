package com.xzp.controller;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.xzp.entity.Blog;
import com.xzp.query.IndexBlog;
import com.xzp.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/25 16:30
 * @Version 1.0
 */
@Controller
public class IndexController {

    @Resource
    private BlogService blogService;


    @GetMapping("/")
    public String index(Model model,HttpSession session){
        session.setAttribute("totalblog",blogService.getallBlogQuery().size());
        session.setAttribute("views",blogService.getViewsTotal());
        session.setAttribute("comments",blogService.getCommentTotal());
        session.setAttribute("msg",blogService.getMessageTotal());
        List<Blog> recommend = blogService.findByRecommend();
        model.addAttribute("blog",recommend);
        model.addAttribute("newest",blogService.findCurrently());
        return "index";
    }

    @PostMapping("/search")
    public String searchBlog(@RequestParam("query")String query,Model model,@RequestParam(value = "pagenum",defaultValue = "1")Integer pagenum){

        PageHelper.startPage(pagenum, 100);
        List<IndexBlog> searchBlog = blogService.searchBlog(query);
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageinfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }
}
