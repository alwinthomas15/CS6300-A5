package models;

public class JobOffer {
    public JobOffer(int id, JobDetails jobDetails, boolean isSaved) {
        this.jobDetails = jobDetails;
        this.isSaved = isSaved;
    }

    public JobOffer(int id, JobDetails jobDetails) {
        this.id = id;
        this.jobDetails = jobDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private JobDetails jobDetails;
    private boolean isSaved;

    public JobDetails getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(JobDetails jobDetails) {
        this.jobDetails = jobDetails;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;

    }

    public void markAsUnSaved() {
        this.isSaved = false;
    }

    public void printOffer() {
        System.out.println("---------");
        System.out.println("Job Id: " + this.id);
        System.out.println(this.id + ": Title: " + this.jobDetails.getTitle());
        System.out.println(this.id + ": Company: " + this.jobDetails.getCompany());
        System.out.println(this.id + ": Location: " + this.jobDetails.getLocation());
        System.out.println(this.id + ": Cost of living: " + this.jobDetails.getCostOfLivingIndex());
        System.out.println(this.id + ": Yearly Salary: " + this.jobDetails.getYearlySalary());
        System.out.println(this.id + ": Yearly Bonus: " + this.jobDetails.getYearlyBonus());
        System.out.println(this.id + ": Gym Membership: " + this.jobDetails.getGymMembership());
        System.out.println(this.id + ": Leave Time: " + this.jobDetails.getLeaveTime());
        System.out.println(this.id + ": 401K Match: " + this.jobDetails.getRetirementMatch());
        System.out.println(this.id + ": Pet Insurance: " + this.jobDetails.getPetInsurance());
    }

    public void printOffer(Double score) {
        System.out.println("---------");
        System.out.println("Job Id: " + this.id);
        System.out.println(this.id + ": Score: " + score);
        System.out.println(this.id + ": Title: " + this.jobDetails.getTitle());
        System.out.println(this.id + ": Company: " + this.jobDetails.getCompany());
        System.out.println(this.id + ": Location: " + this.jobDetails.getLocation());
        System.out.println(this.id + ": Cost of living: " + this.jobDetails.getCostOfLivingIndex());
        System.out.println(this.id + ": Yearly Salary: " + this.jobDetails.getYearlySalary());
        System.out.println(this.id + ": Yearly Bonus: " + this.jobDetails.getYearlyBonus());
        System.out.println(this.id + ": Gym Membership: " + this.jobDetails.getGymMembership());
        System.out.println(this.id + ": Leave Time: " + this.jobDetails.getLeaveTime());
        System.out.println(this.id + ": 401K Match: " + this.jobDetails.getRetirementMatch());
        System.out.println(this.id + ": Pet Insurance: " + this.jobDetails.getPetInsurance());
    }
}
