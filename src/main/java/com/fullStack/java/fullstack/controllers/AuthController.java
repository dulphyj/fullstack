package com.fullStack.java.fullstack.controllers;

import com.fullStack.java.fullstack.models.User;
import com.fullStack.java.fullstack.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fullStack.java.fullstack.dao.UserDao;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        User userLogin = userDao.verifyCredentials(user);
        if (userLogin != null){
            String token = jwtUtil.create(String.valueOf(userLogin.getId()), userLogin.getEmail());
            return token;
        }
        return "FAIL";
    }
}
