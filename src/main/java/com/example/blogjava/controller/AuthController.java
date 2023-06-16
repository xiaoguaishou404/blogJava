package com.example.blogjava.controller;

import com.example.blogjava.entities.Result;
import com.example.blogjava.entities.User;
import com.example.blogjava.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Map;

@Controller
public class AuthController {
    private UserService userSevice;
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;

    @Inject
    public AuthController(UserService userSevice, AuthenticationManager authenticationManager) {
        this.userSevice = userSevice;

        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/auth")
    @ResponseBody
    public Object auth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userSevice.getUserByUsername(authentication == null ? null : authentication.getName());
        if (loggedInUser == null) {
            return Result.failure("User is not logged in");
        } else {
            return new Result("ok", null, true, loggedInUser);
        }
    }


    @PostMapping("/auth/login")
    @ResponseBody
    public Result login(@RequestBody Map<String, String> nameAndPassword) {
        String username = nameAndPassword.get("username");
        String password = nameAndPassword.get("password");
        UserDetails userDetails;
        try {
            //        取出用户
            userDetails = userSevice.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
//            如果用户不存在
            return Result.failure("用户不存在");
        }
//        进行比对是否正确
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//        如果错误则抛出异常
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            return new Result("ok", "登录成功", true, userSevice.getUserByUsername(username));
        } catch (BadCredentialsException e) {
            return Result.failure("密码不正确");
        }


    }

    @PostMapping("/auth/register")
    @ResponseBody
    public Result register(@RequestBody Map<String, String> nameAndPassword) {
        String username = nameAndPassword.get("username");
        String password = nameAndPassword.get("password");
        if (username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0) {
            return Result.failure("用户名或密码不能为空");
        }
        if (username.length() > 15 || username.length() < 3 || password.length() > 15 || password.length() < 3) {
            return Result.failure("用户名或密码长度不合法");
        }


        try {
            userSevice.save(username, password);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return Result.failure("用户名已经存在");
        }
        return new Result("ok", "注册成功", false);


    }

    @GetMapping("/auth/logout")
    @ResponseBody
    public Object logout() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userSevice.getUserByUsername(username);
        if (loggedInUser == null) {
            return Result.failure("User is not logged in");
        } else {
            SecurityContextHolder.clearContext();
            return new Result("ok", "注销成功", false);
        }
    }


}
