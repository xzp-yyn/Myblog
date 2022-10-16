package com.xzp.service;

import com.xzp.entity.Type;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/26 16:54
 * @Version 1.0
 */
public interface TypeService {
    //新增保存分类
    int saveType(Type type);

    //根据id查询分类
    Type getType(Long id);

    //获取分类下博客信息
    List<Type> getAllType();


    List<Type> TypeManager();

    //根据分类名称查询分类
    Type getTypeByName(String name);

    //编辑修改分类
    int updateType(Type type);

    //删除分类
    void deleteType(Long id);
}
