package com.emmanuel.creditrisk.backend.service;

import com.emmanuel.creditrisk.backend.event.EventProducer;
import com.emmanuel.creditrisk.backend.dto.CreditRiskRequest;
import com.emmanuel.creditrisk.backend.dto.CreditRiskResponse;
import org.springframework.stereotype.Service;

@Service
public class CreditRiskService {

    private final EventProducer eventProducer;

    public CreditRiskService(EventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    public CreditRiskResponse score(CreditRiskRequest req) {
        int creditScore = req.getCreditScore();
        int income = req.getAnnualIncome();
        int debt = req.getExistingDebt();
        int missed = req.getMissedPaymentsLast12Months();

        // Normalize credit score (300–850) into 0–60 points
        double creditComponent = ((creditScore - 300.0) / 550.0) * 60.0;
        creditComponent = clamp(creditComponent, 0, 60);

        // Income component: up to 20 points (cap at 120k)
        double incomeComponent = (Math.min(income, 120000) / 120000.0) * 20.0;
        incomeComponent = clamp(incomeComponent, 0, 20);

        // Debt penalty: up to -15 points (cap at 80k)
        double debtPenalty = (Math.min(debt, 80000) / 80000.0) * 15.0;

        // Missed payments penalty: each missed payment costs 5 points (cap at 25)
        double missedPenalty = Math.min(missed * 5.0, 25.0);

        double raw = creditComponent + incomeComponent - debtPenalty - missedPenalty;
        int score = (int) Math.round(clamp(raw, 0, 100));

        String riskLevel;
        if (score >= 70) riskLevel = "LOW";
        else if (score >= 50) riskLevel = "MEDIUM";
        else riskLevel = "HIGH";

        String decision;
        String reason;

        if (score >= 70 && missed <= 1) {
            decision = "APPROVE";
            reason = "Strong credit profile based on score + payment history.";
        } else if (score >= 50) {
            decision = "REVIEW";
            reason = "Borderline profile — manual review recommended.";
        } else {
            decision = "DECLINE";
            reason = "High risk based on scoring factors.";
        }

        // Fire-and-forget event (won’t break app if Kafka is down)
        eventProducer.publish("credit_risk_scored",
                "score=" + score + ", decision=" + decision + ", risk=" + riskLevel);

        return new CreditRiskResponse(score, riskLevel, decision, reason);
    }

    private static double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }
}