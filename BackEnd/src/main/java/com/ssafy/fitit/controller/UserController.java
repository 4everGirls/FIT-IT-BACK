package com.ssafy.fitit.controller;

import com.ssafy.fitit.model.dto.User;
import com.ssafy.fitit.model.service.UserService;
import com.ssafy.fitit.model.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userApi")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        userService.insertUser(user);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping("/idCheck/{id}")
    public ResponseEntity<String> idCheck(@PathVariable String id){
        String msg = userService.idCheck(id);
        if(msg.equals("success")){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    @GetMapping("/nicknameCheck/{nickname}")
    public ResponseEntity<String> nicknameCheck(@PathVariable String nickname){
        String msg = userService.nicknameCheck(nickname);
        if(msg.equals("success")){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }


//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam("id") String id, @RequestParam("password") String password){
//        User loginUser = userService.getUser(id);
//        if(loginUser != null && password.equals(loginUser.getPassword())){
//            return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
//        }else{
//            return new ResponseEntity<String>(FAIL,HttpStatus.BAD_REQUEST);
//        }
//    }
    @PostMapping("/login")
    ResponseEntity<Map<String, Object>> login(@RequestParam("id") String id, @RequestParam("password") String password) {

        User user = userService.getUser(id);
        System.out.println(user);
        HashMap<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        // user를 받아서 DB에서 확인을 해야죠.!!!!!!
        // service -> dao -> db -> 그결과를 가지고 뚜따뚜따 해야함
        try {
            if (user != null && user.getPassword().equals(password)) {
                result.put("access-token", jwtUtil.createToken("id", user.getId()));
                result.put("message", SUCCESS);
                result.put("user", user);
                status = HttpStatus.ACCEPTED;
            }else {
                System.out.println("11111");
                result.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("222");
            result.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String,Object>>(result, status);
    }

    @GetMapping("/user/{userNo}")
    public ResponseEntity<User> myprofile(@PathVariable int userNo){
        return new ResponseEntity<User>(userService.getUserbyUserNo(userNo),HttpStatus.OK);
    }


}
