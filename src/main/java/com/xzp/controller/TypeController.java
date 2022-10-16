package com.xzp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzp.entity.Type;
import com.xzp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/26 17:00
 * @Version 1.0
 */
@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;


    /**
     * listall
     * @param model   模型
     * @param pagenum pagenum
     * @return {@link String}
     *///分页查询分类列表
    @GetMapping("/types")
    public String listall(Model model, @RequestParam(value = "pagenum",defaultValue = "1")Integer pagenum,
                          @ModelAttribute(value = "message")String msg
    ){
        String orderBy="id desc";
        PageHelper.startPage(pagenum,5,orderBy);
        List<Type> allType = typeService.TypeManager();
        PageInfo<Type> pageInfo = new PageInfo<Type>(allType);
        model.addAttribute("pageinfo",pageInfo);
        if(msg.equals("")){
            return "admin/types";
        }
        model.addAttribute("message",msg);
        return "admin/types";
    }

    /**
     * 输入
     * @param model 模型
     * @return {@link String}
     */
    @GetMapping("/types/input")
    public String input(Model model,@ModelAttribute("message")String msg){
        model.addAttribute("type",new Type());
        if(msg.equals("")){
            return "admin/types-input";
        }
        model.addAttribute("message",msg);
        return "admin/types-input";
    }


    /**
     *@Valid进行数据校验
     * @param type       类型
     * @return {@link String}
     */
    @PostMapping("/types")
    public String insertType(@Valid Type type,Model model,RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1!=null){
            attributes.addAttribute("message","不能添加重复的分类");
            return "redirect:/types/input";
        }
        int i = typeService.saveType(type);
        if(i==0){
            attributes.addAttribute("message","新增失败");
        }else {
           attributes.addAttribute("message","新增成功");
        }
        return "redirect:/types";
    }

    /**
     * 编辑类型
     * @param id    id
     * @param model 模型
     * @return {@link String}
     */
    @GetMapping("/types/{id}/input")
    public String editType(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能更新重复的分类");
            return "redirect:/types/input";
        }
        int t = typeService.updateType(type);
        if (t == 0 ) {
            attributes.addAttribute("message", "编辑失败");
        } else {
            attributes.addAttribute("message", "编辑成功");
        }
        return "redirect:/types";
    }


    /**
     * 删除
     * @param id         id
     * @param attributes 属性
     * @return {@link String}
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/types";
    }



}
