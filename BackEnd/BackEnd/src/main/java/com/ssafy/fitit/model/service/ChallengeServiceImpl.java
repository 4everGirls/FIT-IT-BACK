package com.ssafy.fitit.model.service;

import com.ssafy.fitit.model.dao.ChallengeDao;
import com.ssafy.fitit.model.dao.UserDao;
import com.ssafy.fitit.model.dto.Challenge;
import com.ssafy.fitit.model.dto.ChallengeReview;
import com.ssafy.fitit.model.dto.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChallengeServiceImpl implements ChallengeService{

    @Autowired
    private ChallengeDao challengeDao;

    @Override
    public int insertChallenge(Challenge challenge) {

        System.out.println("챌린지 등록");
        //챌린지 등록
        challengeDao.insertChallenge(challenge);

        //방금만든 챌린지의 challengeNo
        int challengeNO = challengeDao.recentChallengeNo();
        return challengeNO;
    }

    @Override
    public void insertMission(List<Mission> missions, int challengeNo) {

        for(Mission mission : missions){
            //챌린지 넘버는 넣어주기
            mission.setChallengeNo(challengeNo);
            challengeDao.insertMission(mission);
        }

    }

    @Override
    public int updateChallenge(Challenge challenge) {
        return challengeDao.updateChallenge(challenge);
    }

//    @Override
//    public void updateMission(Challenge challenge) {
//        List<Mission> missions = challenge.getMissions();
//        for(Mission mission : missions){
//
//            challengeDao.updateMission(mission);
//        }
//    }

    @Override
    public int deleteChallenge(int challengeNo) {
        //미션 먼저 삭제
        challengeDao.deleteMission(challengeNo);

        return challengeDao.deleteChallenge(challengeNo);
    }

    @Override
    public List<Challenge> getMakeChallengList(int userNo) {
        List<Challenge> challengeAndMission = challengeDao.selectAllMakeChallenge(userNo);

        return challengeAndMission;
    }

    @Override
    public List<Challenge> getAllChallenge() {

        List<Challenge> challengeAndMission = challengeDao.selectAllChallenge();

        return challengeAndMission;
    }

    @Override
    public int insertChallengeReview(ChallengeReview challengeReview) {
        return challengeDao.insertChallengeReview(challengeReview);
    }

    @Override
    public Challenge detailChallenge(int challengeNo) {

        Challenge challenge = challengeDao.oneChallengeByChallengeNo(challengeNo);


        return challenge;
    }

    @Override
    public List<Mission> detailMission(int challengeNo) {
        //특정 챌린지 넘버의 미션들
        List<Mission> missions =challengeDao.selectMissionByChallengeNo(challengeNo);

        return missions;
    }

    @Override
    public List<Challenge> getmyJoinChallengeList(int userNo) {
        //내가 가입한 챌린지들의 정보
        List<Integer> challengeNo = challengeDao.getmyJoinChallengeNo(userNo);

        List<Challenge> getmyJoinChallengeList = new ArrayList<>();
        //내가 가입한 챌린지 넘버로 내가 가입한 챌린지 리스트들 가져오기
        for(int no: challengeNo){
            getmyJoinChallengeList.add(challengeDao.oneChallengeByChallengeNo(no));
        }
        return getmyJoinChallengeList;
    }

    @Override
    public int isJoinChallenge(int challengeNo, int userNo) {
        //내가 가입한 챌린지들의 정보
        List<Integer> challengeNoList = challengeDao.getmyJoinChallengeNo(userNo);
        int isJoinChallenge = challengeNoList.size();
        return isJoinChallenge;
    }

    @Override
    public Mission missionInfo(int missionNo) {
        return challengeDao.selectOneMissionByMissionNo(missionNo);
    }


    @Override
    public int insertParticipant(int challengeNo, int userNo) {
        Map<String,Integer> newMap = new HashMap<>();
        newMap.put("challengeNo", challengeNo);
        newMap.put("userNo", userNo);

        return challengeDao.insertParticipant(newMap);
    }
}
