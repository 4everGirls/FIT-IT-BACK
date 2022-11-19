package com.ssafy.fitit.controller;

import com.ssafy.fitit.model.dto.User;
import com.ssafy.fitit.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userApi")
public class UserController {

    private static final String SUCCESS = "succes";
    private static final String FAIL = "fail";

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        userService.insertUser(user);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("id") String id, @RequestParam("password") String password){
        User loginUser = userService.getUser(id);
        if(loginUser != null && password.equals(loginUser.getPassword())){
            return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(FAIL,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{userNo}")
    public ResponseEntity<User> myprofile(@PathVariable int userNo){
        return new ResponseEntity<User>(userService.getUserbyUserNo(userNo),HttpStatus.OK);
    }


}
