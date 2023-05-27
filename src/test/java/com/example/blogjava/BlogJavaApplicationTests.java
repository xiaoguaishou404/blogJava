package com.example.blogjava;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BlogJavaApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void TestGet() {
        Object name = redisTemplate.boundValueOps("name").get();
        System.out.println(name);
    }

    @Test
    public void TestSet() {
        redisTemplate.boundValueOps("name").set("CodeGeeX");
    }


    @Test
    void contextLoads() {
    }

}
