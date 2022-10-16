package com.xzp;

import com.xzp.entity.Comment;
import com.xzp.entity.DetailBlog;
import com.xzp.entity.Type;
import com.xzp.query.IndexBlog;
import com.xzp.service.BlogService;
import com.xzp.service.CommentService;
import com.xzp.service.TypeService;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/2 9:56
 * @Version 1.0
 */
@SpringBootTest
public class CountTest {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService service;

    @Autowired
    TypeService typeService;

    @Test
    public void total(){
        Integer total = blogService.getCommentTotal();
        System.out.println(total);

        Integer viewsTotal = blogService.getViewsTotal();
        System.out.println(viewsTotal);
    }

    @Test
    public void search(){
        List<IndexBlog> blogs = blogService.searchBlog("spring");
        System.out.println(blogs);
    }

    @Test
    void detailtest() throws NotFoundException {
        List<Comment> comments = service.listCommentByBlogId((long) 71);
        for (Comment comment:
             comments) {
            System.out.println(comment.toString());
        }
    }

    @Test
    void gettype(){
//        List<Type> type = typeService.getAllType();
//        System.out.println(type.size());
        List<IndexBlog> blogs = blogService.getByTypeId((long) 70);
        System.out.println(blogs.size());
    }
}
