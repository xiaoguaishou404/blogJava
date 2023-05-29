package com.example.blogjava;

import com.example.blogjava.entities.TUser;
import com.example.blogjava.mapper.TUserMapper;
import com.example.blogjava.mapper.TUserXmlMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class BlogJavaApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private TUserXmlMapper tUserXmlMapper;

    @Test
    public void testFindTUserAll() {
        List<TUser> tUserList = tUserMapper.findTUserAll();
        System.out.println(tUserList);
    }
    @Test
    public void testFindTUserAll2() {
        List<TUser> tUserList2 = tUserXmlMapper.findTUserAll2();
        System.out.println(tUserList2);
    }


    @Test
    public void TestGet() {
        Object name = redisTemplate.boundValueOps("name").get();
        System.out.println(name);
    }

    @Test
    public void TestSet() {
        redisTemplate.boundValueOps("name").set("fff");
    }


    @Test
    void contextLoads() {
    }

}
