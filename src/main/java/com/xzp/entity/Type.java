package com.xzp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/26 16:37
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {

    private Long id;
    private String name;
    private List<Blog> blogs=new ArrayList<>();
}
