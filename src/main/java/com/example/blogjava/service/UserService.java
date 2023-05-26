package com.example.blogjava.service;

import com.example.blogjava.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

//业务类注解
@Service
@Component
public class UserService {

    private UserMapper userMapper;


    @Inject
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUserById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userMapper=" + userMapper +
                '}';
    }
}
