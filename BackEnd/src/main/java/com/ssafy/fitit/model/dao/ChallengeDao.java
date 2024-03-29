package com.ssafy.fitit.model.dao;

import com.ssafy.fitit.model.dto.*;

import java.util.List;
import java.util.Map;

public interface ChallengeDao {

    //챌린지 만들기
    int insertChallenge(Challenge challenge);

    //방금 만든 챌린지의 challengeNo
    int recentChallengeNo();

    //챌린지 만들때 미션 생성
    void insertMission(Mission mission);

    //챌린지 수정하가ㅣ
    int updateChallenge(Challenge challenge);
    void updateMission(Mission mission);

    //챌린지 , 미션 삭제하기
    int deleteMission(int challengeNo);
    int deleteChallenge(int challengeNo);

    //특정 챌린지의 미션 정보
    List<Mission> selectMissionByChallengeNo(int challengeNo);


    //내가 만든 챌린지 정보들
    List<Challenge> selectAllMakeChallenge(int userNo);

    //전체 챌린지 정보들 (댓글 정보까지)
    List<Challenge> selectAllChallenge();

    //챌린지 리뷰등록
    int insertChallengeReview(ChallengeReview challengeReview);

    //챌린지 리뷰 리스트
    List<ChallengeReview> selectChallengeReviewByChallengeNo(int challengeNo);

    //특정 챌린지
    Challenge oneChallengeByChallengeNo(int challengeNo);



    //챌린지 가입하기
    int insertParticipant(Map<String,Integer> map);



    //미션 컴플릿 넣기

    int insertMissionComplete(MissionComplete missionComplete);

    //챌린지 참여자 정보 가져오기
    int getParticipantNo(Map<String,Integer> map);
    //챌린지 가입 취소하가ㅣ
    int deleteParticipant(int participantNo);

    //내가 가입한 챌린지들의 챌린지 no
    List<Integer> getmyJoinChallengeNo (int userNo) ;

    //챌린지의 참여한 사람 정보
    List<Integer> participantInfo(int challengeNo);


    //전체 챌린지 정보(나중에 챌린지 분류)


    //특정 챌린지 상세 정보

    //북마크 등록하기
    int insertBookMark(Bookmark bookmark);

    //북마크 삭제하기
    int deleteBookMark(int bookmarkNo);

    //북마크 정보 불러오기
    List<Bookmark> selectBookMark(int userNo);

    //미션 no로 미션 1개 검색
    Mission selectOneMissionByMissionNo( int missionNo);

    //챌린지 완료
    int updateParticipant(int participantNo);

    //챌린지 완료인지 아닌지 상태
    String completeResult(int participantNo);

    //가입한 사람들 리스트
    List<String> selectAllParticipantReturnName(int challengNo);

    //가입한 사람들 수
    int selectParticipantCount(int challengNo);





}
