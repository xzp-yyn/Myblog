package com.xzp.service.impl;

import com.xzp.dao.BlogDao;
import com.xzp.entity.Blog;
import com.xzp.entity.DetailBlog;
import com.xzp.entity.Type;
import com.xzp.query.*;
import com.xzp.service.BlogService;
import com.xzp.service.TypeService;
import com.xzp.utils.MarkDownUtil;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/27 15:44
 * @Version 1.0
 */
@Service
public class BlogServiceImpl implements BlogService {

    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Resource
    private BlogDao blogDao;

    @Resource
    private TypeService typeService;

    @Override
    public int insertBlog(Blog blog) {
        String typename = typeService.getType(blog.getTypeId()).getName();
        blog.setTypename(typename);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return blogDao.insert(blog);
    }

    @Override
    public List<BlogQuery> getallBlogQuery() {
        return blogDao.getallBlogQuery();
    }

    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteById(id);
    }

    @Override
    public int updateBlog(ShowBlog showBlog)  {
        showBlog.setUpdateTime(new Date());
        int i = blogDao.updateBlog(showBlog);
        if(i==1){
            Type type = typeService.getType(showBlog.getTypeId());
            blogDao.updateTypename(type);
        }
        return blogDao.updateBlog(showBlog);
    }

    @Override
    public ShowBlog selectByID(Long id) {
        return blogDao.selectByid(id);
    }

    @Override
    public List<BlogQuery> findBytitleAndType(SearchBlogQuery blogQuery) {
        return blogDao.findAllByTitle(blogQuery);
    }

    @Override
    public int UpdateTypename(Type type) {
        return blogDao.updateTypename(type);
    }

    @Override
    public int existType(Long id) {
        return blogDao.existType(id);
    }

    @Override
    public List<Blog> findByRecommend() {
        return blogDao.findByRecommend();
    }

    @Override
    public Blog findCurrently() {
        return blogDao.currentlyBlog();
    }

    @Override
    public Integer getViewsTotal() {
        return blogDao.getViewTotal();
    }

    @Override
    public Integer getCommentTotal() {
        return blogDao.getCommentTotal();
    }

    @Override
    public Integer getMessageTotal() {
        return blogDao.getMessageTotal();
    }

    @Override
    public int updateView(Long id) {
        return blogDao.updateView(id);
    }

    @Override
    public int updateCountByid(Long id) {
        return blogDao.getCommentCountById(id);
    }

    @Override
    public List<IndexBlog> searchBlog(String query) {
        return blogDao.searchBlog(query);
    }

    @Override
    public DetailBlog detailBlog(Long id) throws NotFoundException {
        DetailBlog blog = blogDao.detailBlog(id);
        if(blog==null){
            throw new NotFoundException("该博客找不到");
        }
        String content = blog.getContent();
        blog.setContent(MarkDownUtil.markdownToHtmlExtensions(content));
        blogDao.updateView(id);
        blogDao.getCommentCountById(id);
        return blog;
    }

    @Override
    public List<IndexBlog> getByTypeId(Long typeid) {
        return blogDao.getByTypeId(typeid);
    }

    @Override
    public List<TimeShaftBlog> getTimeShaftBlog() {
        return blogDao.getTimeShaftBlog();
    }
}
