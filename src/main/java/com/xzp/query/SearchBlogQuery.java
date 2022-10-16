package com.xzp.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/27 18:58
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBlogQuery {
    private String title;
    private Long typeId;
}
