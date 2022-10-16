package com.xzp.dao;

import com.xzp.entity.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/30 16:32
 * @Version 1.0
 */
@Mapper
public interface PictureDao {
    List<Picture> getAllPicture();
    Picture selectPicture(Long id);
    int insertPicture(Picture picture);
    int updatePicture(Picture picture);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    int deletePicture(Long id);



}
