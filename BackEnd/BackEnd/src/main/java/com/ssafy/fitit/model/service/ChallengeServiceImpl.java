package com.ssafy.fitit.model.service;

import com.ssafy.fitit.model.dao.ChallengeDao;
import com.ssafy.fitit.model.dto.Challenge;
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

        return challengeDao.selectAllMakeChallenge(userNo);
    }
}
