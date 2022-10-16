package com.xzp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzp.entity.Blog;
import com.xzp.entity.Comment;
import com.xzp.entity.DetailBlog;
import com.xzp.entity.User;
import com.xzp.query.BlogQuery;
import com.xzp.query.SearchBlogQuery;
import com.xzp.query.ShowBlog;
import com.xzp.service.BlogService;
import com.xzp.service.CommentService;
import com.xzp.service.TypeService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/27 15:49
 * @Version 1.0
 */
@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CommentService cservice;

    /**
     * 跳转到新增页面
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String bloginput(Model model){
        model.addAttribute("types",typeService.TypeManager());
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }
    /**
     * 博客新增
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        //新增的时候需要传递blog对象，blog对象需要有user
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        int b = blogService.insertBlog(blog);
        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
            session.setAttribute("totalblog",blogService.getallBlogQuery().size());
        }
        return "redirect:/blogs";
    }
    /**
     * 获得所有blog
     * @param model
     * @param pagenum
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(Model model, @RequestParam(value = "pagenum",defaultValue = "1")Integer pagenum,
                        @ModelAttribute("message")String msg){
        String orderBy="update_time desc";
        PageHelper.startPage(pagenum,10,orderBy);
        List<BlogQuery> blogs = blogService.getallBlogQuery();
        PageInfo<BlogQuery> bloginfo = new PageInfo<>(blogs);
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("bloginfo",bloginfo);
        if(msg==null){
            return "admin/blogs";
        }
        model.addAttribute("message",msg);
        return "admin/blogs";
    }

    /**
     * 跳转编辑
     */
    @GetMapping("/blogs/edit/{id}")
    public String editBlog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.selectByID(id));
        model.addAttribute("types",typeService.TypeManager());
        return "admin/blogs-input";
    }
    /**
     * 编辑修改文章
     */
    @PostMapping("/blogs/{id}")
    public String editpost(@Valid ShowBlog blog,RedirectAttributes redirectAttributes){
        int i = blogService.updateBlog(blog);
        if(i==0){
            redirectAttributes.addFlashAttribute("message","修改失败");
        }else {
            redirectAttributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/blogs";
    }

    /**
     * 删除博客
     */
    @GetMapping("/delete/{id}")
    public String deleteblog(@PathVariable Long id,Model model,RedirectAttributes redirectAttributes,HttpSession session){
        int i = blogService.deleteBlog(id);
        if(i==0){
            redirectAttributes.addAttribute("message","删除失败");
        }else {
            redirectAttributes.addAttribute("message","删除成功");
            session.setAttribute("totalblog",blogService.getallBlogQuery().size());
        }
        return "redirect:/blogs";
    }
    /**
     * 搜索博客
     * admin/blogs::bloglist 因为返回的内容是全部html
     * 所以：：bloglist只返回模板中的片段
     */
    @PostMapping("/blog/find")
    public String searchBlog(SearchBlogQuery searchBlogQuery,Model model,
                             @RequestParam(value = "pagenum",defaultValue = "1")Integer pagenum){
        List<BlogQuery> blogQueries = blogService.findBytitleAndType(searchBlogQuery);
        PageHelper.startPage(pagenum,5);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogQueries);
        model.addAttribute("bloginfo",pageInfo);
        model.addAttribute("types",typeService.getAllType());
        return "admin/blogs::bloglist";
    }

    /**
     * 跳转到博客详情页
     */
    @GetMapping("/blog/{id}")
    public String detailBlog(@PathVariable Long id,Model model) throws NotFoundException {
        DetailBlog blog = blogService.detailBlog(id);
        List<Comment> comments = cservice.listCommentByBlogId(id);
        model.addAttribute("blog",blog);
        model.addAttribute("comments",comments);
        return "blog";
    }
}
