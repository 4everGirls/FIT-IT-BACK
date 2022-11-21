package com.ssafy.fitit.model.service;

import com.ssafy.fitit.model.dao.ChallengeDao;
import com.ssafy.fitit.model.dto.Challenge;
import com.ssafy.fitit.model.dto.ChallengeReview;
import com.ssafy.fitit.model.dto.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService{

    @Autowired
    private ChallengeDao challengeDao;

    @Override
    public int insertChallenge(Challenge challenge) {
        System.out.println("서비스에서 챌린지 이름");
        System.out.println(challenge.getChallengeName());
        return challengeDao.insertChallenge(challenge);
    }

    @Override
    public void insertMission(Challenge challenge) {
        List<Mission> missions = challenge.getMissions();
        //방금 등록된 challenge의 challenge no를 가져와라
        int challengeNo = challengeDao.recetChallengeNo();

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

    @Override
    public void updateMission(Challenge challenge) {
        List<Mission> missions = challenge.getMissions();
        for(Mission mission : missions){

            challengeDao.updateMission(mission);
        }
    }

    @Override
    public int deleteChallenge(int challengeNo) {
        //미션 먼저 삭제
        challengeDao.deleteMission(challengeNo);

        return challengeDao.deleteChallenge(challengeNo);
    }

    @Override
    public List<Challenge> getMakeChallengList(int userNo) {
        List<Challenge> challengeAndMission = challengeDao.selectAllMakeChallenge(userNo);
        for(Challenge challenge : challengeAndMission){
            int challengeNo = challenge.getChallengeNo();
            //특정 챌린지 넘버의 미션들
            List<Mission> missions =challengeDao.selectMissionByChallengeNo(challengeNo);

            //미션 정보를 challenge에 넣어
             challenge.setMissions(missions);

            //특정 챌린지 넘버의 리뷰들
            List<ChallengeReview> reviews = challengeDao.selectChallengeReviewByChallengeNo(challengeNo);

            //챌린지 정보를 challenge에 넣어
            challenge.setReviews(reviews);
        }

        return challengeAndMission;
    }

    @Override
    public List<Challenge> getAllChallenge() {

        List<Challenge> challengeAndMission = challengeDao.selectAllChallenge();
        for(Challenge challenge : challengeAndMission){
            int challengeNo = challenge.getChallengeNo();
            //특정 챌린지 넘버의 미션들
            List<Mission> missions =challengeDao.selectMissionByChallengeNo(challengeNo);

            //미션 정보를 challenge에 넣어
            challenge.setMissions(missions);

            //특정 챌린지 넘버의 리뷰들
            List<ChallengeReview> reviews = challengeDao.selectChallengeReviewByChallengeNo(challengeNo);

            //챌린지 정보를 challenge에 넣어
            challenge.setReviews(reviews);
        }

        return challengeAndMission;
    }

    @Override
    public int insertChallengeReview(ChallengeReview challengeReview) {
        return challengeDao.insertChallengeReview(challengeReview);
    }

    @Override
    public Challenge detailChallenge(int challengeNo) {

        Challenge challenge = challengeDao.oneChallengeByChallengeNo(challengeNo);

        //특정 챌린지 넘버의 미션들
        List<Mission> missions =challengeDao.selectMissionByChallengeNo(challengeNo);

        //미션 정보를 challenge에 넣어
        challenge.setMissions(missions);

        //특정 챌린지 넘버의 리뷰들
        List<ChallengeReview> reviews = challengeDao.selectChallengeReviewByChallengeNo(challengeNo);

        //챌린지 정보를 challenge에 넣어
        challenge.setReviews(reviews);

        return challenge;
    }


    @Override
    public int insertParticipant(int challenge_no, int user_no) {
        return challengeDao.insertParticipant(challenge_no,user_no);
    }
}
