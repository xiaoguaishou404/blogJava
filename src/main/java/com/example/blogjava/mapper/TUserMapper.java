package com.example.blogjava.mapper;

import com.example.blogjava.entities.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
//使用@Mapper可以声明为Bean，@Repository可以省略。
//@Repository
public interface TUserMapper {
    @Select("SELECT * FROM t_user")
    public List<TUser> findTUserAll();
}
