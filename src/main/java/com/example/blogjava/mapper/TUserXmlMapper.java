package com.example.blogjava.mapper;

import com.example.blogjava.entities.TUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TUserXmlMapper {
    public List<TUser> findTUserAll2();
}
