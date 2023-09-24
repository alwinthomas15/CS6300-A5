package models;

import java.util.*;

public class JobComparisonResults {

    private List<JobOffer> jobOffers;
    private Map<JobOffer, Double> comparisonScores;
    private ComparisonSettings comparisonSettings;

    public JobComparisonResults(List<JobOffer> jobOffers, Map<JobOffer, Double> comparisonScores, ComparisonSettings comparisonSettings) {
        this.jobOffers = jobOffers;
        this.comparisonScores = comparisonScores;
        this.comparisonSettings = comparisonSettings;
    }

    public JobComparisonResults(List<JobOffer> jobOffers, ComparisonSettings comparisonSettings) {
        this.jobOffers = jobOffers;
        this.comparisonSettings = comparisonSettings;
        this.comparisonScores = new HashMap<JobOffer, Double>();
    }

    public JobComparisonResults(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }


    public void addJobOffer(JobOffer jobOffer) {
        jobOffers.add(jobOffer);
    }

    public List<JobOffer> getRankedJobOffers() {
        List<JobOffer> rankedOffers = new ArrayList<>(jobOffers);
        rankedOffers.sort(Comparator.comparingDouble(comparisonScores::get).reversed());
        return rankedOffers;
    }

    public void compareJobsOffers() {
        for (JobOffer offer : jobOffers) {
            double score = calculateScore(offer);
            comparisonScores.put(offer, score);
        }
    }

    private double calculateScore(JobOffer offer) {
        ComparisonSettings settings = this.comparisonSettings;
        JobDetails details = offer.getJobDetails();

        double score = (details.getYearlySalary() * settings.getYearlySalaryWeight())
                + (details.getYearlyBonus() * settings.getYearlyBonusWeight())
                + (details.getGymMembership() * settings.getGymMembershipWeight())
                + (details.getLeaveTime() * settings.getLeaveTimeWeight())
                + ((details.getYearlySalary() * details.getRetirementMatch() / 100) * settings.getRetirementMatch())
                + (details.getPetInsurance() * settings.getPetInsuranceWeight());

        return score;
    }

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public Map<JobOffer, Double> getComparisonScores() {
        return comparisonScores;
    }

    public void setComparisonScores(Map<JobOffer, Double> comparisonScores) {
        this.comparisonScores = comparisonScores;
    }

    public ComparisonSettings getComparisonSettings() {
        return comparisonSettings;
    }

    public void setComparisonSettings(ComparisonSettings comparisonSettings) {
        this.comparisonSettings = comparisonSettings;
    }
}
