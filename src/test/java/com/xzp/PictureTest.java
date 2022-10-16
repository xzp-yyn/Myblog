package com.xzp;

import com.xzp.entity.Picture;
import com.xzp.service.PictureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/30 16:51
 * @Version 1.0
 */
@SpringBootTest
public class PictureTest {

    @Autowired
    private PictureService pictureService;

    @Test
    void test(){
        List<Picture> pictures = pictureService.getAllPicture();
        System.out.println(pictures+"\t");

//        Picture picture = new Picture();
//        picture.setPictureaddress("http://192.168.137.1:8080/images/南阳.jpg");
//        picture.setPicturedescription("我的老家");
//        picture.setPicturename("南阳");
//        picture.setPicturetime("河南南阳");
//        int i1 = pictureService.insertPicture(picture);
//        System.out.println(i1);
//
//        Picture picture1 = pictureService.selectPicture((long) 1);
//        System.out.println(picture1);
//
//        Picture picture2 = new Picture();
//        picture2.setPicturetime("河南南阳");
//        picture2.setId((long) 2);
//        int i = pictureService.updatePicture(picture2);
//        System.out.println(i);
    }

    @Test
    void test2(){
        String string="D:\\blog-springboot\\src\\main\\resources\\static\\images\\南阳.jpg";
        Object s = string.substring(44, string.length());
        System.out.println(s);
    }


}
