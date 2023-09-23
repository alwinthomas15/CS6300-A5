package models;

public class JobDetails {
    public JobDetails(String title, String company, String location, int costOfLivingIndex, double yearlySalary, double yearlyBonus, double gymMembership, int leaveTime, int retirementMatch, double petInsurance) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.costOfLivingIndex = costOfLivingIndex;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.gymMembership = gymMembership;
        this.leaveTime = leaveTime;
        RetirementMatch = retirementMatch;
        this.petInsurance = petInsurance;
    }

    private String title;
    private String company;
    private String location;
    private int costOfLivingIndex;
    private double yearlySalary;
    private double yearlyBonus;
    private double gymMembership;
    private int leaveTime;
    private int RetirementMatch;
    private double petInsurance;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCostOfLivingIndex() {
        return costOfLivingIndex;
    }

    public void setCostOfLivingIndex(int costOfLivingIndex) {
        this.costOfLivingIndex = costOfLivingIndex;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public double getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(double yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public double getGymMembership() {
        return gymMembership;
    }

    public void setGymMembership(double gymMembership) {
        this.gymMembership = gymMembership;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getRetirementMatch() {
        return RetirementMatch;
    }

    public void setRetirementMatch(int retirementMatch) {
        RetirementMatch = retirementMatch;
    }

    public double getPetInsurance() {
        return petInsurance;
    }

    public void setPetInsurance(double petInsurance) {
        this.petInsurance = petInsurance;
    }
}
