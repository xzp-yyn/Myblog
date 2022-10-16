package com.xzp.dao;

import com.xzp.entity.FriendLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/3 10:07
 * @Version 1.0
 */
@Mapper
public interface FriendLinkDao {
    //查询友链管理列表
    List<FriendLink> listFriendLink();

    //新增友链
    int saveFriendLink(FriendLink friendLink);

    //根据网址查询友链
    FriendLink getFriendLinkByBlogaddress(String blogaddress);

    //根据id查询友链
    FriendLink getFriendLink(Long id);

    //编辑修改友链
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int updateFriendLink(FriendLink friendLink);

    //删除友链
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int deleteFriendLink(Long id);

}
