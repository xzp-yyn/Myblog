package com.xzp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/26 16:41
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendLink {
    private Long id;
    private String blogname;
    private String blogaddress;
    private String pictureaddress;
    private Date createTime;
}
