package com.xzp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/26 16:14
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private boolean appreciation;
    private boolean commentabled;
    private Date createTime;
    private Date updateTime;

    private String description;
    private boolean published;
    private boolean recommend;
    private boolean shareStatement;
    private Integer views;
    private Integer commentCount;
    private String typename;

    private Type type;
    private Long userId;
    private Long typeId;
    private User user;

    private List<Comment> blogs=new ArrayList<>();

}
