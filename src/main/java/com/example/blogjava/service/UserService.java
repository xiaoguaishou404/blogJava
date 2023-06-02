package com.example.blogjava.service;

import com.example.blogjava.entities.User;
import com.example.blogjava.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//业务类注解
@Service
@Component
public class UserService implements UserDetailsService {
    private BCryptPasswordEncoder  bCryptPasswordEncoder;


    private UserMapper userMapper;
    //ConcurrentHashMap线程安全
    private Map<String, User> users = new ConcurrentHashMap<>();

    @Inject
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
        save("hhh","hhh");
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (!users.containsKey(username)) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        User user = users.get(username);
        return new org.springframework.security.core.userdetails.User(username, user.getEncryptedPassword(), Collections.emptyList());
    }

    public void save(String username, String password) {
        users.put(username,new User(username,1,bCryptPasswordEncoder.encode(password)) );
    }







    public User getUserById(int id) {
        return userMapper.findUserById(id);
    }

    public User getUserByusername(String username) {
        return users.get(username);
    }


    @Override
    public String toString() {
        return "UserService{" +
                "bCryptPasswordEncoder=" + bCryptPasswordEncoder +
                ", userMapper=" + userMapper +
                ", userPasswords=" + users +
                '}';
    }
}
