package com.xzp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzp.entity.Picture;
import com.xzp.service.PictureService;
import com.xzp.utils.PictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/30 17:07
 * @Version 1.0
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    /**
     * 获得所有图片
     * @param model
     * @param pagenum
     * @return
     */
    @GetMapping("/pictures")
    public String pictures(Model model, @RequestParam(value = "pagenum",defaultValue = "1")Integer pagenum
                           , @ModelAttribute("message")String msg){
        PageHelper.startPage(pagenum,10);
        List<Picture> allPicture = pictureService.getAllPicture();
        PageInfo<Picture> pageInfo = new PageInfo<>(allPicture);
        model.addAttribute("pageinfo",pageInfo);
        if(msg.equals("")){
            return "admin/pictures";
        }else {
            model.addAttribute("message",msg);
        }
        return "admin/pictures";
    }
    /**
     * 跳转新增
     */
    @GetMapping("/pictures/input")
    public String insert(Model model){
        model.addAttribute("picture",new Picture());
        return "admin/pictures-input";
    }
    /**
     * 照片新增
     */
    @PostMapping("/pictures")
    public String input(@Valid Picture picture, BindingResult result, @RequestParam("pictureaddress") MultipartFile file, RedirectAttributes redirectAttributes){
        Map<String, Object> map = PictureUtil.savePicture(file);
        boolean flag = (boolean) map.get("flag");
        if(!flag){
            redirectAttributes.addFlashAttribute("message","图片上传失败！");
            return "redirect:/pictures";
        }
        picture.setPictureaddress((String) map.get("path"));
        int i = pictureService.insertPicture(picture);
        if(i==0){
            redirectAttributes.addFlashAttribute("message","新增失败！");
        }else {
            redirectAttributes.addFlashAttribute("message", "新增成功！");
        }
        return "redirect:/pictures";
    }

    /**
     * 跳转编辑页面
     */
    @GetMapping("/pictures/{id}")
    public String editinput(@PathVariable Long id,Model model){
        Picture picture = pictureService.selectPicture(id);
        model.addAttribute("picture",picture);
        return "admin/pictures-input";
    }

    /**
     * 编辑相册
     */
    @PostMapping("/pictures/{id}")
    public String update(@Valid Picture picture,RedirectAttributes redirectAttributes){
        int i = pictureService.updatePicture(picture);
        if(i==0){
            redirectAttributes.addFlashAttribute("message","更新失败");
        }else {
            redirectAttributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/pictures";
    }

    /**
     * 删除相片
     */

    @GetMapping("/pictures/delete/{id}")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        int i = pictureService.deletePicture(id);
        if(i==1){
            attributes.addFlashAttribute("message","删除成功");
        }else {
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/pictures";
    }

    @GetMapping("/PhotoWall")
    public String PhotoWall(Model model){
        List<Picture> picture = pictureService.getAllPicture();
        model.addAttribute("pictures",picture);
        return "picture";
    }

}
