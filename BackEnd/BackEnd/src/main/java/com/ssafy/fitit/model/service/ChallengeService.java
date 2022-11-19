package com.ssafy.fitit.model.service;

import com.ssafy.fitit.model.dto.Challenge;
import com.ssafy.fitit.model.dto.Mission;

import java.util.List;

public interface ChallengeService {

    //챌린지 등록
    int insertChallenge(Challenge challenge);

    //미션 등록
    void insertMission(Challenge challenge);

    //챌린지 수정
    int updateChallenge(Challenge challenge);
    void updateMission(Challenge challenge);

    //챌린지+미션 삭제
    int deleteChallenge(int challengeNo);


    //내가 만든 챌린지 정보들
    List<Challenge> getMakeChallengList(int userNo);

    //챌린지 가입하기
    int insertParticipant(int challenge_no, int user_no);

}
