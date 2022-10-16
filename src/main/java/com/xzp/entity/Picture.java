package com.xzp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/26 16:42
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    private Long id;
    private String picturename;
    private String picturetime;
    private String pictureaddress;
    private String picturedescription;
}
