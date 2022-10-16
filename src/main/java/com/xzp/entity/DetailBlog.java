package com.xzp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/3 18:02
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailBlog {
    //博客信息
    private Long id;
    private String firstPicture;
    private String flag;
    private String title;
    private String content;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private boolean commentabled;
    private boolean shareStatement;
    private boolean appreciation;
    private String nickname;
    private String avatar;
    //分类名称
    private String typeName;
}
