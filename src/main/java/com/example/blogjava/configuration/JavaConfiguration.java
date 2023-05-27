package com.example.blogjava.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Configuration
public class JavaConfiguration {
//    @Service
//    @Component
//    可以不写@Bean，使用上面两个注解的其中一个实现自动装配
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
