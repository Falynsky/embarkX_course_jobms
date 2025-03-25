package com.falynsky.jobms.app.helpers;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalaryBonusCalculator {

    private final static int companyExistenceTime = 12;

    private final double currentSallary;
    private final int employmentTime;

    public double calculateBonus(int currentBonus) {
        double persentageOfBonus = getPersentageOfBonus();
        double calculatedBonus = calculateBonus(currentBonus, persentageOfBonus);
        return currentSallary + calculatedBonus;
    }

    private double calculateBonus(int currentBonus, double persentageOfBonus) {
        return currentBonus * persentageOfBonus;
    }

    private double getPersentageOfBonus() {
        return (double) employmentTime / companyExistenceTime;
    }

}
