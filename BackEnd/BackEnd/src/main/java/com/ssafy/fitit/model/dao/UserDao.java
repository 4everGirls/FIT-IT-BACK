package com.ssafy.fitit.model.dao;

import com.ssafy.fitit.model.dto.User;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserDao {

    //유저 등록
    int insertUser(User user);

    //아이디로 유저 정보 조회
    User selectOne(String id);

    //아이디 중복확인

    //userNo로 유저 정보 조회
    User selectByUserNo(int userNo);
}
