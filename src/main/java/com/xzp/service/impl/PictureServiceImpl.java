package com.xzp.service.impl;

import com.xzp.dao.PictureDao;
import com.xzp.entity.Picture;
import com.xzp.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/30 16:47
 * @Version 1.0
 */
@Service
public class PictureServiceImpl  implements PictureService {

    @Resource
    private PictureDao pictureDao;
    @Override
    public List<Picture> getAllPicture() {
        return pictureDao.getAllPicture();
    }

    @Override
    public Picture selectPicture(Long id) {
        return pictureDao.selectPicture(id);
    }

    @Override
    public int insertPicture(Picture picture) {
        return pictureDao.insertPicture(picture);
    }

    @Override
    public int updatePicture(Picture picture) {
        return pictureDao.updatePicture(picture);
    }

    @Override
    public int deletePicture(Long id) {
        return pictureDao.deletePicture(id);
    }
}
