package com.xzp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzp.entity.Type;
import com.xzp.query.IndexBlog;
import com.xzp.service.BlogService;
import com.xzp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/6 16:45
 * @Version 1.0
 */
@Controller
public class TypePageController {

    @Autowired
    TypeService typeService;

    @Autowired
    BlogService blogService;

    @GetMapping("/showtypes/{id}")
    public String typepage(@RequestParam(value = "pagenum",defaultValue = "1")Integer pagenum, Model model, @PathVariable Long id){
        List<Type> allType = typeService.getAllType();
        if (id == -1) {
            id = allType.get(0).getId();
        }
        model.addAttribute("types", allType);
        List<IndexBlog> blogs = blogService.getByTypeId(id);

        PageHelper.startPage(pagenum, 100);
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageinfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
