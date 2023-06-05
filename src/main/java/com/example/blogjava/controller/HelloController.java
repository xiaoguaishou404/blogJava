package com.example.blogjava.controller;

import com.example.blogjava.entities.Author;
import com.example.blogjava.service.OrderService;
import com.example.blogjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Arrays;

@RestController
public class HelloController {

    private OrderService orderService;
//    private UserService userService;

    @Value("${authorName}")
    private String authorName;
    @Value("${author.age}")
    private int authorAge;
    @Value("${author.hobbyList}")
    private String[] authorHobbyList;

    @Autowired
    private Environment env;
    @Autowired
    private Author author;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/redis/get/{key}")
    public Object get(@PathVariable("key") String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @PostMapping("/redis/set/{key}/{value}")
    public Object set(@PathVariable("key") String key, @PathVariable("value") String value) {
        redisTemplate.opsForValue().set(key, value);
        return "set success";
    }


    @Inject
    public HelloController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/main")
    public String index() {
        System.out.println(env.getProperty("author.hobbyList"));
        System.out.println(authorName);
        System.out.println(authorAge);
        System.out.println(Arrays.toString(authorHobbyList));

        System.out.println(author);
        return this.orderService.PlaceOrder(3, "").toString();
    }

}