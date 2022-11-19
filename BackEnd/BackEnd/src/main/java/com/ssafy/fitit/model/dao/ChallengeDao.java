package com.ssafy.fitit.model.dao;

import com.ssafy.fitit.model.dto.Bookmark;
import com.ssafy.fitit.model.dto.Challenge;
import com.ssafy.fitit.model.dto.Mission;

import java.util.List;

public interface ChallengeDao {

    //챌린지 만들기
    int insertChallenge(Challenge challenge);

    //방금 만든 챌린지의 challengeNo
    int recetChallengeNo();

    //챌린지 만들때 미션 생성
    void insertMission(Mission mission);

    //챌린지 수정하가ㅣ
    int updateChallenge(Challenge challenge);
    void updateMission(Mission mission);

    //챌린지 , 미션 삭제하기
    int deleteMission(int challengeNo);
    int deleteChallenge(int challengeNo);

    //내가 만든 챌린지 정보들
    List<Challenge> selectAllMakeChallenge(int userNo);


    //북마크 등록하기
    int insertBookMark(Bookmark bookmark);

    //북마크 정보 불러오기
    List<Bookmark> selectBookMark(int userNo);



    //내가 가입한 챌린지 정보들

    //전체 챌린지 정보(나중에 챌린지 분류)


    //특정 챌린지 상세 정보


}
