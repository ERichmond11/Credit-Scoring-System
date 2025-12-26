package com.emmanuel.creditrisk.backend.dto;

public class CreditRiskResponse {

    private int score;            // 0–100
    private String riskLevel;     // LOW / MEDIUM / HIGH
    private String decision;      // APPROVE / REVIEW / DECLINE
    private String reason;        // short explanation

    public CreditRiskResponse() {}

    public CreditRiskResponse(int score, String riskLevel, String decision, String reason) {
        this.score = score;
        this.riskLevel = riskLevel;
        this.decision = decision;
        this.reason = reason;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}