package com.xzp.dao;

import com.mysql.cj.log.Log;
import com.xzp.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/6 8:05
 * @Version 1.0
 */
@Mapper
public interface CommentDao {

    int addComment(Comment comment);

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int deleteComment(Long id);

    List<Comment> findFirstComment(@Param("blogid") Long blogid,@Param("id") Long id);

    List<Comment> findChildComment(@Param("blogid") Long blogid,@Param("childid") Long chidid);

    List<Comment> findParentComment(@Param("blogid") Long blogid, @Param("parentid") Long ParentCommentID);

    Comment getCommentByid(Long id);
}
