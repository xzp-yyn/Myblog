package com.xzp.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/7 9:44
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeShaftBlog {

    private Long id;
    private String title;
    private Date createTime;
}
