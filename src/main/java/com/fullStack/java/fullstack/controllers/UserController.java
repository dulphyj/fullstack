package com.fullStack.java.fullstack.controllers;

import com.fullStack.java.fullstack.dao.UserDao;
import com.fullStack.java.fullstack.models.User;
import com.fullStack.java.fullstack.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;


    private boolean validateToken(String token){
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }
    /*@RequestMapping(value = "api/user/{id}", method = RequestMethod.GET)
    public User getUser (@PathVariable Long id){
        User user = new User();
        user.setId(id);
        user.setName("Lalo");
        user.setLastName("Landa");
        user.setEmail("lalolanda@ut.com");
        user.setPhone("555-12345");
        return user;
    }*/

    @RequestMapping(value = "api/users")
    public List<User> getUsers (@RequestHeader(value = "Authorization") String token){
        if(!validateToken(token)){
            return null;
        }
        return userDao.getUsers();
    }

    @RequestMapping(value = "api/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser (@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        if(!validateToken(token)){
            return;
        }
        userDao.delete(id);
    }

    @RequestMapping(value = "api/user", method = RequestMethod.POST)
    public void registerUser (@RequestBody User user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024,1, user.getPassword());
        user.setPassword(hash);
        userDao.post(user);
    }
}
