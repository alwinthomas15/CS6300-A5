package models;

public class ComparisonSettings {
    private int yearlySalaryWeight;
    private int yearlyBonusWeight;
    private int gymMembershipWeight;
    private int leaveTimeWeight;
    private int retirementMatch;
    private int petInsuranceWeight;

    public int getYearlySalaryWeight() {
        return yearlySalaryWeight;
    }

    public void setYearlySalaryWeight(int yearlySalaryWeight) {
        this.yearlySalaryWeight = yearlySalaryWeight;
    }

    public int getYearlyBonusWeight() {
        return yearlyBonusWeight;
    }

    public void setYearlyBonusWeight(int yearlyBonusWeight) {
        this.yearlyBonusWeight = yearlyBonusWeight;
    }

    public int getGymMembershipWeight() {
        return gymMembershipWeight;
    }

    public void setGymMembershipWeight(int gymMembershipWeight) {
        this.gymMembershipWeight = gymMembershipWeight;
    }

    public int getLeaveTimeWeight() {
        return leaveTimeWeight;
    }

    public void setLeaveTimeWeight(int leaveTimeWeight) {
        this.leaveTimeWeight = leaveTimeWeight;
    }

    public int getRetirementMatch() {
        return retirementMatch;
    }

    public void setRetirementMatch(int retirementMatch) {
        this.retirementMatch = retirementMatch;
    }

    public int getPetInsuranceWeight() {
        return petInsuranceWeight;
    }

    public void setPetInsuranceWeight(int petInsuranceWeight) {
        this.petInsuranceWeight = petInsuranceWeight;
    }
}
