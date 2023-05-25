package com.example.blogjava.configuration;

import com.example.blogjava.mapper.UserMapper;
import com.example.blogjava.service.OrderService;
import com.example.blogjava.service.User;
import com.example.blogjava.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfiguration {
//    @Bean
//    public OrderService orderService(UserService userService) {
//        return new OrderService(userService);
//    }
//
//    @Bean
//    public UserService userService(UserMapper userMapper) {
//        return new UserService(userMapper);
//    }
//    @Bean
//    public UserMapper userMapper(){
//        return new UserMapper(){
//            @Override
//            public User findUserById(int id) {
//                return null;
//            }
//        };
//    }
}
