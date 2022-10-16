package com.xzp.service.impl;

import com.xzp.dao.FriendLinkDao;
import com.xzp.entity.FriendLink;
import com.xzp.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/3 10:16
 * @Version 1.0
 */
@Service
public class FriendServiceImpl  implements FriendLinkService {

    @Resource
    private FriendLinkDao dao;
    @Override
    public List<FriendLink> listFriendLink() {
        return dao.listFriendLink();
    }

    @Override
    public int saveFriendLink(FriendLink friendLink) {
        return dao.saveFriendLink(friendLink);
    }

    @Override
    public FriendLink getFriendLinkByBlogaddress(String blogaddress) {
        return dao.getFriendLinkByBlogaddress(blogaddress);
    }

    @Override
    public FriendLink getFriendLink(Long id) {
        return dao.getFriendLink(id);
    }

    @Override
    public int updateFriendLink(FriendLink friendLink) {
        return dao.updateFriendLink(friendLink);
    }

    @Override
    public int deleteFriendLink(Long id) {
        return dao.deleteFriendLink(id);
    }
}
