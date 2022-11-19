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
    public User getUser(String id) {
        return userDao.selectOne(id);
    }

    @Override
    public User getUserbyUserNo(int userNo) {
        return userDao.selectByUserNo(userNo);
    }


}
