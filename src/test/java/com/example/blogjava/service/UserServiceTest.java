package com.example.blogjava.service;


import com.example.blogjava.BlogJavaApplication;
import com.example.blogjava.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogJavaApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testAdd() {
        System.out.println(111);
        System.out.println(userService.toString());

    }


}
