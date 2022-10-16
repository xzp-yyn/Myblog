package com.xzp.service;
import com.xzp.entity.Picture;

import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/30 16:46
 * @Version 1.0
 */
public interface PictureService {
    List<Picture> getAllPicture();
    Picture selectPicture(Long id);
    int insertPicture(Picture picture);
    int updatePicture(Picture picture);
    int deletePicture(Long id);
}
