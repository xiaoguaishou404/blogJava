package com.example.blogjava.controller;

import com.example.blogjava.entities.User;
import com.example.blogjava.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userSevice.getUserByUsername(username);
        if (loggedInUser == null) {
            return new ResUlt("ok", "用户没有登录", false);

        } else {

            return new ResUlt("ok", null, true, loggedInUser);

        }
    }


    @PostMapping("/auth/login")
    @ResponseBody
    public ResUlt login(@RequestBody Map<String, String> nameAndPassword) {

        String username = nameAndPassword.get("username");
        String password = nameAndPassword.get("password");
        UserDetails userDetails;
        try {
            //        取出真正的密码
            userDetails = userSevice.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
//            如果用户不存在
            return new ResUlt("fail", "用户不存在", false);
        }

//        进行比对是否正确
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//如果错误则抛出异常
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            return new ResUlt("ok", "登陆成功", true, userSevice.getUserByUsername(username));
        } catch (BadCredentialsException e) {
            return new ResUlt("fail", "密码不正确", false);
        }


    }

    private static class ResUlt {
        String status;
        String msg;
        boolean isLogin;
        Object data;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isLogin() {
            return isLogin;
        }

        public void setLogin(boolean login) {
            isLogin = login;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public ResUlt(String status, String msg, boolean isLogin) {
            this(status, msg, isLogin, null);
        }

        public ResUlt(String status, String msg, boolean isLogin, Object data) {
            this.status = status;
            this.msg = msg;
            this.isLogin = isLogin;
            this.data = data;
        }

        @Override
        public String toString() {
            return "ResUlt{" +
                    "status='" + status + '\'' +
                    ", msg='" + msg + '\'' +
                    ", isLogin=" + isLogin +
                    ", data=" + data +
                    '}';
        }
    }

}
