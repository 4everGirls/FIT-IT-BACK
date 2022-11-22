package com.ssafy.fitit.model.service;

import com.ssafy.fitit.model.dto.Challenge;
import com.ssafy.fitit.model.dto.ChallengeReview;
import com.ssafy.fitit.model.dto.Mission;

import java.util.List;

public interface ChallengeService {

    //챌린지 등록
    int insertChallenge(Challenge challenge);

    //미션 등록
    void insertMission(List<Mission> missions, int challengeNo);

    //챌린지 수정
    int updateChallenge(Challenge challenge);
    //void updateMission(Challenge challenge);

    //챌린지+미션 삭제
    int deleteChallenge(int challengeNo);


    //내가 만든 챌린지 정보들
    List<Challenge> getMakeChallengList(int userNo);

    //전체 챌린지 정보들 (댓글 정보까지)
    List<Challenge> getAllChallenge();


    //챌린지 리뷰등록
    int insertChallengeReview(ChallengeReview challengeReview);

    //챌린지 상세페이지 - 챌린지
    Challenge detailChallenge(int challengeNo);

    //챌린지 상세페이지 - 미션
    List<Mission> detailMission(int challengeNo);

    //내가 참여한 챌린지
    List<Challenge> getmyJoinChallengeList(int userNo);

    //이 챌린지 참여중인지
    int isJoinChallenge(int challengeNo, int userNo);

    //미션 정보
    Mission missionInfo(int missionNo);







    //챌린지 가입하기
    int insertParticipant(int challenge_no, int user_no);

}
