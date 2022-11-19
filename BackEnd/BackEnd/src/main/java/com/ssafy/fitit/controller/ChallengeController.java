package com.ssafy.fitit.controller;

import com.ssafy.fitit.model.dto.Challenge;
import com.ssafy.fitit.model.dto.Mission;
import com.ssafy.fitit.model.dto.Participant;
import com.ssafy.fitit.model.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/challengeApi")
public class ChallengeController {

    private static final String SUCCESS = "succes";
    private static final String FAIL = "fail";

    @Autowired
    private ChallengeService challengeService;

    @PostMapping("/insertChallenge")
    public ResponseEntity<String> insertChallenge(@RequestBody Challenge challenge){

        challengeService.insertChallenge(challenge);
        challengeService.insertMission(challenge);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
    }

    @PutMapping("/updateChallenge")
    public ResponseEntity<String> updateChallenge(@RequestBody Challenge challenge){
        challengeService.updateChallenge(challenge);
        challengeService.updateMission(challenge);
        return  new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }

    @DeleteMapping("/deleteChallenge/{challengeNo}")
    public ResponseEntity<String> deleteChallenge(@PathVariable int challengeNo){
        //연관된 미션부터 지우고 challenge 지우기

        challengeService.deleteChallenge(challengeNo);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }

    @GetMapping("/makeChallengeList/{userNo}")
    public ResponseEntity<List<Challenge>> makeChallengeList(@PathVariable int userNo){
        List<Challenge> challengeList = challengeService.getMakeChallengList(userNo);
        return new ResponseEntity<>(challengeList,HttpStatus.OK);
    }

    @PostMapping("/joinChallenge")
    public ResponseEntity<String> joinChallenge(@RequestBody Participant participant){
        challengeService.insertParticipant(participant.getChallengeNo(), participant.getUserNo());
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }



}
