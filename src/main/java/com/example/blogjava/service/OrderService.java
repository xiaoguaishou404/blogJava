package com.example.blogjava.service;

import com.example.blogjava.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
@Service
@Component
public class OrderService {

    private UserService userService;

    @Inject
    public OrderService(UserService userService) {
        this.userService = userService;
    }

    public User PlaceOrder(int id, String name) {
        return userService.getUserById(id);
    }
}
