package com.ssafy.fitit.controller;

import com.ssafy.fitit.model.dto.*;
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

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private ChallengeService challengeService;

    //챌린지 등록하기
    @PostMapping("/insertChallenge")
    public ResponseEntity<Integer> insertChallenge(@RequestBody Challenge challenge){
        System.out.println("데이터 잘들어옴");
        System.out.println(challenge);
        System.out.println(challenge.toString());

        //방금 등록한 챌린지의 challengeNo
        int challengeNo = challengeService.insertChallenge(challenge);

        return new ResponseEntity<Integer>(challengeNo, HttpStatus.CREATED);
    }

    //미션 등록하기
    @PostMapping("/insertMission/{challengeNo}")
    public ResponseEntity<String> insertMission(@PathVariable int challengeNo, @RequestBody ArrayList<Mission> missions){
        System.out.println(" 미션 데이터 들어옴");
        System.out.println(missions.toString());
        challengeService.insertMission(missions, challengeNo);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
    }

    //내가 만든 챌린지 수정
//    @PutMapping("/updateChallenge")
//    public ResponseEntity<String> updateChallenge(@RequestBody Challenge challenge){
//        challengeService.updateChallenge(challenge);
//        challengeService.updateMission(challenge);
//        return  new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
//    }

    //내가 만든 챌린지 삭제
    @DeleteMapping("/deleteChallenge/{challengeNo}")
    public ResponseEntity<String> deleteChallenge(@PathVariable int challengeNo){
        //연관된 미션부터 지우고 challenge 지우기

        challengeService.deleteChallenge(challengeNo);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }

    //내가 만든 챌린지 리스트
    @GetMapping("/makeChallengeList/{userNo}")
    public ResponseEntity<List<Challenge>> makeChallengeList(@PathVariable int userNo){
        List<Challenge> challengeList = challengeService.getMakeChallengList(userNo);

        return new ResponseEntity<>(challengeList,HttpStatus.OK);
    }

    //모든 챌린지
    @GetMapping("/allChallenge")
    public ResponseEntity<List<Challenge>> allChallenge(){
        List<Challenge> challengeList = challengeService.getAllChallenge();
        return new ResponseEntity<>(challengeList,HttpStatus.OK);
    }

    //챌린지 리뷰 등록
    @PostMapping("/insertChallengeReview")
    public ResponseEntity<String> insertChallengeReview(@RequestBody ChallengeReview challengeReview){
        challengeService.insertChallengeReview(challengeReview);
        return new ResponseEntity<>(SUCCESS,HttpStatus.CREATED);
    }

    //챌린지 상세 페이지 - 챌린지 정보
    @GetMapping("/detailChallenge/{challengeNo}")
    public ResponseEntity<Challenge> detailChallenge(@PathVariable int challengeNo){

        Challenge challenge = challengeService.detailChallenge(challengeNo);
        return  new ResponseEntity<>(challenge,HttpStatus.OK);
    }
    //챌린지 상세 페이지 - 미션 정보
    @GetMapping("/detailMission/{challengeNo}")
    public ResponseEntity<List<Mission>> detailMission(@PathVariable int challengeNo){

        List<Mission> mission = challengeService.detailMission(challengeNo);
        return  new ResponseEntity<>(mission,HttpStatus.OK);
    }

    //내가 참여한 챌린지들 정보
    @GetMapping("/myJoinChallengeList/{userNo}")
    public ResponseEntity<List<Challenge>> myJoinChallengeList(@PathVariable int userNo){
        List<Challenge> challengeList = challengeService.getmyJoinChallengeList(userNo);

        return new ResponseEntity<>(challengeList,HttpStatus.OK);
    }

    //이 챌린지에 참여된 사람들 정보들
    @GetMapping("/participantInfo/{challengeNo}")
    public ResponseEntity<List<Integer>> participantInfo(@PathVariable int challengeNo){
        List<Integer> participantList = challengeService.participantInfo(challengeNo);

        return new ResponseEntity<>(participantList,HttpStatus.OK);
    }
    //이 챌린지 참여 중인지
    @GetMapping("/isJoinChallenge/{challengeNo}/{userNo}")
    public ResponseEntity<Integer> isjoinChallenge(@PathVariable int challengeNo, @PathVariable int userNo){

        int isJoin = challengeService.isJoinChallenge(challengeNo, userNo);
        return new ResponseEntity<>(isJoin,HttpStatus.OK);
    }

    //챌린지 가입하기
    @GetMapping("/joinChallenge/{challengeNo}/{userNo}")
    public ResponseEntity<String> joinChallenge(@PathVariable int challengeNo, @PathVariable int userNo){
        System.out.println("챌린지 조인한다");
        System.out.println(challengeNo);
        challengeService.insertParticipant(challengeNo, userNo);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }

    //챌린지 가입 취소하기
    @DeleteMapping("/notJoinChallenge/{challengeNo}/{userNo}")
    public ResponseEntity<String> notJoinChallenge(@PathVariable int challengeNo, @PathVariable int userNo){

        challengeService.deleteParticipant(challengeNo, userNo);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }


    //미션 영상 가져오기
    @GetMapping("/missionVideo/{missionNo}")
    public ResponseEntity<Mission> missionInfo(@PathVariable int missionNo){
        Mission missionInfo = challengeService.missionInfo(missionNo);
        return new ResponseEntity<>(missionInfo,HttpStatus.OK);
    }

    //북마크 등록하기
    @PostMapping("/insertBookmark")
    public ResponseEntity<String> insertBookmark(@RequestBody Bookmark bookmark){
        System.out.println(bookmark.getMissionNo());
        System.out.println(bookmark.getUserNo());
        challengeService.insertBookMark(bookmark);
        return  new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }

    //북마크 삭제하기
    @DeleteMapping("/deleteBookmark/{bookmarkNo}")
    public ResponseEntity<String> deleteBookmark(@PathVariable int bookmarkNo){
        challengeService.deleteBookMark(bookmarkNo);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }


    //챌린지 완료
    @PutMapping("/completeMission/{challengeNo}/{userNo}")
    public ResponseEntity<String> completeChallenge(@PathVariable int challengeNo, @PathVariable int userNo){
        challengeService.updateParticipant(challengeNo,userNo);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }

    //챌린지 완료인지 아닌지
    @GetMapping("/isCompleteChallenge/{challengeNo}/{userNo}")
    public ResponseEntity<String> isCompleteChallenge(@PathVariable int challengeNo, @PathVariable int userNo){
        System.out.println("isComplete??????????????????");
        String isCompleteChallenge = challengeService.completeResult(challengeNo,userNo);
        System.out.println("isComplete??????????????????");
        System.out.println(isCompleteChallenge);
        return new ResponseEntity<>(isCompleteChallenge,HttpStatus.OK);
    }


}
