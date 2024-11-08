package com.example.cathayunited.vo;

import java.math.BigDecimal;

public class PriceChangeCalculatorVo {

    // 漲跌
    private BigDecimal calculateDifference;

    // 漲跌幅
    private BigDecimal calculateRateOfChange;

    public PriceChangeCalculatorVo(BigDecimal calculateDifference, BigDecimal calculateRateOfChange) {
        this.calculateDifference = calculateDifference;
        this.calculateRateOfChange = calculateRateOfChange;
    }

    public BigDecimal getCalculateDifference() {
        return calculateDifference;
    }

    public void setCalculateDifference(BigDecimal calculateDifference) {
        this.calculateDifference = calculateDifference;
    }

    public BigDecimal getCalculateRateOfChange() {
        return calculateRateOfChange;
    }

    public void setCalculateRateOfChange(BigDecimal calculateRateOfChange) {
        this.calculateRateOfChange = calculateRateOfChange;
    }
}
