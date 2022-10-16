package com.xzp.service.impl;

import com.xzp.dao.TypeDao;
import com.xzp.entity.Blog;
import com.xzp.entity.Type;
import com.xzp.service.BlogService;
import com.xzp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/26 16:54
 * @Version 1.0
 */
@Service
public class TypeServiceImpl  implements TypeService {
    @Resource
    private TypeDao typeDao;

    @Resource
    private BlogService blogService;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Transactional
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public List<Type> TypeManager() {
        return typeDao.typeManager();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        int i2 = blogService.existType(type.getId());
        if(i2!=0){
            int i1 = blogService.UpdateTypename(type);
        }
        int i = typeDao.updateType(type);
        return i;
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }
}
