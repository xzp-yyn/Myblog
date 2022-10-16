package com.xzp.service.impl;

import com.xzp.dao.CommentDao;
import com.xzp.entity.Comment;
import com.xzp.service.BlogService;
import com.xzp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/6 9:30
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentDao commentDao;

    @Resource
    BlogService blogService;

    private List<Comment> replyList=new ArrayList<>();

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //父评论
        List<Comment> comment = commentDao.findParentComment(blogId, Long.valueOf(-1));
        for (Comment comment1:
             comment) {
            Long id = comment1.getId();
            String Pnickname = comment1.getNickname();
            List<Comment> comment2 = commentDao.findFirstComment(blogId, id); //一级子评论

            combine(blogId,comment2,Pnickname);
            comment1.setReplycomment(replyList);
            replyList=new ArrayList<>();
        }
        return comment;
    }

    private void combine(Long blogid,List<Comment> childcomment,String parentNickname){
        if(childcomment.size()>0){
            for(Comment firstcomment : childcomment){
                String parentnickname = firstcomment.getNickname();
                firstcomment.setParentNickname(parentNickname);
                replyList.add(firstcomment);
                Long childId = firstcomment.getId();
                //查询出子二级评论
                getSecondComment(blogid, childId, parentNickname);
            }
        }
    }

    private void getSecondComment(Long blogid,Long childid,String nickname){
        List<Comment> secondComments = commentDao.findChildComment(blogid,childid);
        if(secondComments.size() > 0){
            for(Comment replayComment : secondComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(nickname);
                Long replayId = replayComment.getId();
                replyList.add(replayComment);
                getSecondComment(blogid,replayId,parentNickname);
            }
        }
    }

    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int i = commentDao.addComment(comment);
        if(i==1){
            blogService.updateCountByid(comment.getBlogId());
        }
        return i;
    }

    @Override
    public int deleteComment(Comment comment, Long id) {
        int i = commentDao.deleteComment(id);
        blogService.updateCountByid(comment.getBlogId());
        return i;
    }

    @Override
    public Comment getCommentByid(Long id) {
        return commentDao.getCommentByid(id);
    }
}
