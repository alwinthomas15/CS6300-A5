package models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class JobComparisonResults {
    public JobComparisonResults(List<JobOffer> jobOffers, Map<JobOffer, Double> comparisonScores) {
        this.jobOffers = jobOffers;
        this.comparisonScores = comparisonScores;
    }

    public JobComparisonResults(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    private List<JobOffer> jobOffers;
    private Map<JobOffer, Double> comparisonScores;

    public void compareJobs() {
        for (JobOffer offer : jobOffers) {
            double score = calculateScore(offer);
            comparisonScores.put(offer, score);
        }
    }

    public List<JobOffer> getRankedJobOffers() {
        List<JobOffer> rankedOffers = new ArrayList<>(jobOffers);
        rankedOffers.sort(Comparator.comparingDouble(comparisonScores::get).reversed());
        return rankedOffers;
    }

    private double calculateScore(JobOffer offer) {
        // Calculate the comparison score for a job offer based on ComparisonSettings
        ComparisonSettings settings = new ComparisonSettings();
        // Retrieve weight values from settings

        JobDetails details = offer.getJobDetails();
        double score = (details.getYearlySalary() * settings.getYearlySalaryWeight())
                + (details.getYearlyBonus() * settings.getYearlyBonusWeight())
                + (details.getGymMembership() * settings.getGymMembershipWeight())
                + (details.getLeaveTime() * settings.getLeaveTimeWeight())
                + ((details.getYearlySalary() * details.getRetirementMatch() / 100) * settings.getRetirementMatch())
                + (details.getPetInsurance() * settings.getPetInsuranceWeight());

        return score;
    }


}
