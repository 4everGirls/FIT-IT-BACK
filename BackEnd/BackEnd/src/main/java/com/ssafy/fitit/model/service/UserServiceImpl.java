package com.ssafy.fitit.model.service;

import com.ssafy.fitit.model.dao.UserDao;
import com.ssafy.fitit.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public String idCheck(String id) {
        String ID = id.trim();
        int idNum = userDao.idCheck(ID);
        if(idNum == 0){
            return "success";
        }else{
            return "fail";
        }

    }

    @Override
    public String nicknameCheck(String nickname) {
        String NICKNAME = nickname.trim();
        int nicknameNum = userDao.nicknameCheck(NICKNAME);
        if(nicknameNum == 0){
            return "success";
        }else{
            return "fail";
        }
    }

    @Override
    public User getUser(String id) {
        return userDao.selectOne(id);
    }

    @Override
    public User getUserbyUserNo(int userNo) {
        return userDao.selectByUserNo(userNo);
    }


}
