package com.emmanuel.creditrisk.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreditRiskRequest {

    @NotNull
    @Min(0)
    private Integer annualIncome; // in dollars

    @NotNull
    @Min(300)
    @Max(850)
    private Integer creditScore;

    @NotNull
    @Min(0)
    private Integer existingDebt; // in dollars

    @NotNull
    @Min(0)
    @Max(24)
    private Integer missedPaymentsLast12Months;

    public CreditRiskRequest() {}

    public CreditRiskRequest(Integer annualIncome, Integer creditScore, Integer existingDebt, Integer missedPaymentsLast12Months) {
        this.annualIncome = annualIncome;
        this.creditScore = creditScore;
        this.existingDebt = existingDebt;
        this.missedPaymentsLast12Months = missedPaymentsLast12Months;
    }

    public Integer getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Integer annualIncome) {
        this.annualIncome = annualIncome;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Integer getExistingDebt() {
        return existingDebt;
    }

    public void setExistingDebt(Integer existingDebt) {
        this.existingDebt = existingDebt;
    }

    public Integer getMissedPaymentsLast12Months() {
        return missedPaymentsLast12Months;
    }

    public void setMissedPaymentsLast12Months(Integer missedPaymentsLast12Months) {
        this.missedPaymentsLast12Months = missedPaymentsLast12Months;
    }
}