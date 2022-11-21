package com.ssafy.fitit.model.dto;

import java.sql.Timestamp;
import java.util.List;

public class Challenge {

    private int challengeNo;
    private int userNo;
    private String challengeName;
    private String challengeContent;
    private String startDate;
    private String endDate;
    private int point;

    private List<Mission> missions;

    private List<ChallengeReview> reviews;

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public List<ChallengeReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ChallengeReview> reviews) {
        this.reviews = reviews;
    }

    public Challenge() {
    }

    public int getChallengeNo() {
        return challengeNo;
    }

    public void setChallengeNo(int challengeNo) {
        this.challengeNo = challengeNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public String getChallengeContent() {
        return challengeContent;
    }

    public void setChallengeContent(String challengeContent) {
        this.challengeContent = challengeContent;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "challengeNo=" + challengeNo +
                ", userNo=" + userNo +
                ", challengeName='" + challengeName + '\'' +
                ", challengeContent='" + challengeContent + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", point=" + point +
                ", missions=" + missions +
                ", reviews=" + reviews +
                '}';
    }
}
