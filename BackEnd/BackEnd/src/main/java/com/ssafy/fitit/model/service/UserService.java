package com.ssafy.fitit.model.service;

import com.ssafy.fitit.model.dto.User;

public interface UserService {

    //회원 등록
    int insertUser(User user);

    //아이디 중복 체크
    String idCheck(String id);

    //닉네임 중복체크
    String nicknameCheck(String nickname);

    //아이디로 유저 정보 조회
    User getUser(String id);

    //userNo로 유저 정보 조회
    User getUserbyUserNo(int userNo);

}
