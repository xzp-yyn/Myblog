package com.xzp.dao;

import com.xzp.entity.Blog;
import com.xzp.entity.DetailBlog;
import com.xzp.query.*;
import com.xzp.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/27 15:37
 * @Version 1.0
 */
@Mapper
public interface BlogDao {
    int insert(Blog blog);

    List<BlogQuery> getallBlogQuery();

    @Options(useGeneratedKeys = true,keyProperty = "id")
    int deleteById(Long id);

    List<BlogQuery> findAllByTitle(SearchBlogQuery blogQuery);

    int updateBlog(ShowBlog showBlog);

    ShowBlog selectByid(Long id);

    @Update("update t_blog set typename=#{name} where type_id=#{id}")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int updateTypename(Type type);

    @Select("SELECT count(1) from t_blog where type_id = #{id} limit 1")
    int existType(Long id);

    List<Blog> findByRecommend();

    Blog currentlyBlog();

    List<IndexBlog> getByTypeId(Long typeid);

    //搜索博客列表
    //@Param("query")是将参数映射到xml中，否则会报错
    List<IndexBlog> searchBlog(@Param("query") String query);

    //统计访问总数
    Integer getViewTotal();
    //统计评论
    Integer getCommentTotal();
//    统计留言总数
    Integer getMessageTotal();

    //查询博客详情
    DetailBlog detailBlog(Long id);

    int updateView(Long id);

    //根据博客id查询出评论数量
    int getCommentCountById(Long id);

    List<TimeShaftBlog> getTimeShaftBlog();

}
