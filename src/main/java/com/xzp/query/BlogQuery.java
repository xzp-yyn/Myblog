package com.xzp.query;

import com.xzp.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/27 18:03
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BlogQuery {
    private Long id;
    private String title;
    private Date updateTime;

    private Boolean recommend;
    private Boolean published;
    private String typename;
    private Long typeId;
}
