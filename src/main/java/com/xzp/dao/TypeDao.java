package com.xzp.dao;

import com.xzp.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/26 16:46
 * @Version 1.0
 */
@Mapper
public interface TypeDao {
    @Insert("insert into t_type values(#{id},#{name})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int saveType(Type type);

    @Select("select * from t_type where id=#{id}")
    Type getType(Long id);

    List<Type> getAllType();

    @Select("select * from t_type")
    List<Type> typeManager();


    //默认字段内容不区分大小写，BINARY可以实现区分大小写
    @Select("select * from t_type where BINARY name=#{name}")
    Type getTypeByName(String name);

    @Update("update t_type t  set t.name=#{name} where id=#{id}")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int  updateType(Type type);

    @Delete("delete from t_type where id=#{id}")
    void deleteType(Long id);


}
