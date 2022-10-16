package com.xzp;

import com.xzp.dao.UserDao;
import com.xzp.entity.Blog;
import com.xzp.entity.Type;
import com.xzp.entity.User;
import com.xzp.query.BlogQuery;
import com.xzp.query.SearchBlogQuery;
import com.xzp.service.BlogService;
import com.xzp.utils.MD5Util;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/25 18:48
 * @Version 1.0
 */
@SpringBootTest
public class UserTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BlogService blogService;
    @Test
   public  void usertest(){
//        User xzp = userDao.findBynameandpassword("xzp","123456");
//        System.out.println(xzp);
        User user = new User();
        user.setAvatar("http://localhost:8080/images/2.jpg");
        user.setCreateTime(DateUtil.now());
        user.setPassword(MD5Util.code("201014"));
        user.setEmail("3210625646@qq.com");
        user.setNickname("咫尺也相思");
        user.setUsername("薛");
        user.setType(1);
        user.setUpdateTime(DateUtil.now());
        int i = userDao.insertUser(user);
        System.out.println(i);
    }

    @Test
    public void Test(){
//        int i = blogService.existType((long) 62);
//        if(i!=0){
//            Type type = new Type();
//            type.setId((long) 62);
//            type.setName("微服务");
//            int i1 = blogService.UpdateTypename(type);
//            System.out.println(i1);
//        }else {
//            System.out.println(i);
//        }
        SearchBlogQuery query = new SearchBlogQuery();
        query.setTitle("redis");
        query.setTypeId((long) 2);
        List<BlogQuery> bytitleAndType = blogService.findBytitleAndType(query);
        System.out.println(bytitleAndType.size());
    }

    @Test
    public void find(){
        Blog currently = blogService.findCurrently();
//        System.out.println(currently.getTitle());
//        System.out.println(currently.getDescription());
//        System.out.println(currently.getFirstPicture());
//        System.out.println(currently.getCommentCount());
//        System.out.println(currently.getViews());
        System.out.println(currently.getUpdateTime());
    }

}
