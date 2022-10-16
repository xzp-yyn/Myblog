package com.xzp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzp.entity.FriendLink;
import com.xzp.service.FriendLinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/3 10:19
 * @Version 1.0
 */
@Controller
public class FriendLinkController {
    @Resource
    private FriendLinkService fservice;
    /**
     * 获取所有友链
     */
    @GetMapping("/links")
    public String getall(Model model, @RequestParam(value = "pagenum",defaultValue = "1")Integer pagenum,
                         @ModelAttribute String msg
    ){
        List<FriendLink> links = fservice.listFriendLink();
        PageHelper.startPage(pagenum,10);
        PageInfo<FriendLink> pageInfo = new PageInfo<>(links);
        model.addAttribute("links",pageInfo);
        if(!msg.equals("") || msg!=null){
            model.addAttribute("message",msg);
        }
        return "admin/friendlinks";
    }

    /**
     * 跳转新增
     * @param model
     * @return
     */
    @GetMapping("/links/input")
    public String toinput(Model model, @ModelAttribute String msg){
        model.addAttribute("link",new FriendLink());
        if(!msg.equals("")|| msg!=null){
            model.addAttribute("message",msg);
        }
        return "admin/friendlinks-input";
    }

    /**
     * 保存
     *
     * @param friendLink         友链
     * @param result             结果
     * @param redirectAttributes 重定向属性
     * @return {@link String}
     */
    @PostMapping("/links")
    public String save(@Valid  FriendLink friendLink , BindingResult result, RedirectAttributes redirectAttributes){
        FriendLink type1 = fservice.getFriendLinkByBlogaddress(friendLink.getBlogaddress());
        if (type1 != null) {
            redirectAttributes.addFlashAttribute("message", "不能添加相同的网址");
            return "redirect:/links/input";
        }

        if(result.hasErrors()){
            return "admin/friendlinks-input";
        }
        friendLink.setCreateTime(new Date());
        int F = fservice.saveFriendLink(friendLink);
        if (F == 0 ) {
            redirectAttributes.addFlashAttribute("message", "新增失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/links";
    }

    /**
     * 跳转编辑
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/links/{id}")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("link", fservice.getFriendLink(id));
        return "admin/friendlinks-input";
    }

    /**
     * 编辑保存
     * @param friendLink
     * @param attributes
     * @return
     */
    @PostMapping("/links/{id}")
    public String editPost(@Valid FriendLink friendLink, RedirectAttributes attributes) {
        int t = fservice.updateFriendLink(friendLink);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/links";
    }

    /**
     * 删除
     *
     * @param id         id
     * @param attributes 属性
     * @return {@link String}
     */
    @GetMapping("/links/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        int i = fservice.deleteFriendLink(id);
        if (i == 0 ) {
            attributes.addFlashAttribute("message", "删除失败");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/links";
    }

    @GetMapping("/showlinks")
    public String getalllinks(Model model){
        List<FriendLink> links = fservice.listFriendLink();
        model.addAttribute("links",links);
        return "friends";
    }
}
