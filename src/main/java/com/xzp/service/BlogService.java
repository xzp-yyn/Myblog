package com.xzp.service;

import com.xzp.entity.Blog;
import com.xzp.entity.DetailBlog;
import com.xzp.entity.Type;
import com.xzp.query.*;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/27 15:43
 * @Version 1.0
 */
public interface BlogService {
    int insertBlog(Blog blog);
    List<BlogQuery> getallBlogQuery();
    int deleteBlog(Long id);
    int updateBlog(ShowBlog showBlog);

    ShowBlog selectByID(Long id);
    List<BlogQuery>  findBytitleAndType(SearchBlogQuery blogQuery);
    int UpdateTypename(Type type);
    int existType(Long id);

    /**
     * 推荐页，显示4个文章
     * @return
     */
    List<Blog> findByRecommend();
    /**
     * 查询最新推荐
     */
    Blog findCurrently();

    List<IndexBlog> searchBlog(String query);

    Integer getViewsTotal();
    Integer getCommentTotal();
    Integer getMessageTotal();

    int updateView(Long id);

    int updateCountByid(Long id);

    DetailBlog detailBlog(Long id) throws NotFoundException;

    List<IndexBlog> getByTypeId(Long typeid);

    List<TimeShaftBlog> getTimeShaftBlog();
 }
