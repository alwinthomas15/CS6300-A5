package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ComparisonService {

    private List<JobOffer> jobOffers;
    private List<JobComparisonResults> comparisonResults;
    private ComparisonSettings comparisonSettings;

    private Scanner scanner;

    public ComparisonService() {
        this.jobOffers = new ArrayList<>();
        this.comparisonResults = new ArrayList<>();
        this.comparisonSettings = new ComparisonSettings();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        showMainMenu();
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Enter or Edit Job Details");
            System.out.println("2. Enter Job Offer");
            System.out.println("3. Adjust Comparison Settings");
            System.out.println("4. Compare Job Offers");
            System.out.println("5. View Saved Job Offers");
            System.out.println("6. Exit");
            System.out.print("Enter: ");

            int choice = Integer.valueOf(scanner.nextLine());
            switch (choice) {
                case 1:
                    enterOrEditJobDetails();
                    break;
                case 2:
                    enterJobOffer();
                    break;
                case 3:
                    adjustComparisonSettings();
                    break;
                case 4:
                    compareJobOffers();
                    break;
                case 5:
                    viewSavedJobOffers();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public JobOffer jobDetailsPrompt() {
        // Gather job details from the user
        System.out.print("Enter job title: ");
        String title = scanner.nextLine();
        System.out.print("Enter company name: ");
        String company = scanner.nextLine();
        System.out.print("Enter job location (city, state): ");
        String location = scanner.nextLine();
        System.out.print("Enter cost of living index: ");
        int costOfLivingIndex = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter yearly salary: ");
        double yearlySalary = Double.valueOf(scanner.nextLine());
        System.out.print("Enter yearly bonus: ");
        double yearlyBonus = Double.valueOf(scanner.nextLine());
        System.out.print("Enter gym membership allowance: ");
        double gymMembership = Double.valueOf(scanner.nextLine());
        System.out.print("Enter leave time (in days): ");
        int leaveTime = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter 401K match (%): ");
        int _401kMatch = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter pet insurance cost: ");
        double petInsurance = Double.valueOf(scanner.nextLine());
        JobDetails jobDetails = new JobDetails(title, company, location, costOfLivingIndex, yearlySalary, yearlyBonus, gymMembership, leaveTime, _401kMatch, petInsurance);

        int count = this.jobOffers.stream().mapToInt(JobOffer::getId)
                .max()
                .orElse(1);


        JobOffer currentJobOffer = new JobOffer(count,jobDetails);

        return currentJobOffer;
    }

    public void enterOrEditJobDetails() {
        // Create JobDetails and JobOffer objects
        if(!this.jobOffers.isEmpty()) {
            JobOffer editJobOffer = null;
            //Display existing Job offers and let users select a specific offer to edit the details
            this.jobOffers.stream().forEach(JobOffer::printOffer);
            System.out.println("=============================");
            System.out.println("Enter 0 to return to main menu");
            System.out.println("Enter the ID of the Job Offer to edit it further: ");
            System.out.print("Enter: ");
            int choice = Integer.valueOf(scanner.nextLine());

            Optional<JobOffer> result = this.jobOffers.stream()
                    .filter(jobOffer -> jobOffer.getId()==choice)
                    .findFirst();

            if(result.isPresent()) {
               editJobOffer = result.get();

                System.out.println(" The job offer you selected is: ");
                editJobOffer.printOffer();
                System.out.println("=======================");
                System.out.println("Enter new details for the Job Id of: " + editJobOffer.getId());

                JobOffer newJobOffer = jobDetailsPrompt();
                newJobOffer.setId(choice);
                persistJobOffer(saveOrCancelPrompt(),newJobOffer);
            } else {
                System.out.println("The Job Offer for Id: " + choice + " cannot be found in the database!");
            }

        }
        else {
            //Prompt users to enter new job details of an offer
            System.out.println("There are no job offers present, please enter details for the job!");
            JobOffer jobOffer = jobDetailsPrompt();
            persistJobOffer(saveOrCancelPrompt(),jobOffer);
        }
    }

    public void editCurrentJob() {

    }

    public String saveOrCancelPrompt() {
        System.out.print("Do you want to save this job offer? (Enter 'Yes' or 'No'): ");
        return scanner.nextLine();

    }

    public void persistJobOffer(String val, JobOffer jobOffer) {
        if (val.toLowerCase().equals("yes") || val.toLowerCase().equals("y")) {
            this.jobOffers.add(jobOffer);
            System.out.println("Job offer with the Id: " + jobOffer.getId() +" has been added successfully!");
        } else {
            System.out.println("Job offer with the Id: " + jobOffer.getId() +" has not been added!");
        }
    }

    public boolean enterOrEditCurrentJob(JobOffer jobOffer) {
        // Logic to enter or edit current job details
        // You can add validation and error handling here
        // Update the jobOffers list with the provided job offer
        this.jobOffers.add(jobOffer);
        return true; // For simplicity, assume success
    }

    public void enterJobOffer() {
        // Logic to enter a new job offer
        // You can interact with the user to gather job offer details
        // Create a new models.JobOffer object and add it to the jobOffers list
        JobOffer jobOffer = jobDetailsPrompt();

        //perhaps make this a boolean? to see if the user saved it or not, so the user can compare this SPECIFIC one with a specific ONE from the list of the arrayList
        persistJobOffer(saveOrCancelPrompt(),jobOffer);

        // Next steps
        System.out.println("\nOptions (Enter a number):");
        System.out.println("1. Enter another offer");
        System.out.println("2. Return to main menu");
        System.out.println("3. Compare offer (if saved)");
        System.out.println("4. Exit");

//        int number = scanner.nextInt();

        switch (1) {
            case 1:
                enterJobOffer();
                break;
            case 2:
                showMainMenu();
                break;
            case 3:
                compareJobOffers();
                break;
            case 4:
                System.out.println("System Ends!");
                //TODO: Not sure?
                //scanner.close();
                //System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");

        }
    }



    public void adjustComparisonSettings() {
        // Logic to adjust comparison settings
        // You can interact with the user to adjust weights in the models.ComparisonSettings object
//         Gather comparison settings from the user
        System.out.print("Enter yearly salary weight: ");
        int yearlySalaryWeight = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter yearly bonus weight: ");
        int yearlyBonusWeight = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter gym membership weight: ");
        int gymMembershipWeight = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter leave time weight: ");
        int leaveTimeWeight = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter 401K match weight: ");
        int _401kMatchWeight = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter pet insurance weight: ");
        int petInsuranceWeight = Integer.valueOf(scanner.nextLine());

        // Update the comparison settings
        comparisonSettings.setYearlySalaryWeight(yearlySalaryWeight);
        comparisonSettings.setYearlyBonusWeight(yearlyBonusWeight);
        comparisonSettings.setGymMembershipWeight(gymMembershipWeight);
        comparisonSettings.setLeaveTimeWeight(leaveTimeWeight);
        comparisonSettings.setRetirementMatch(_401kMatchWeight);
        comparisonSettings.setPetInsuranceWeight(petInsuranceWeight);

        System.out.println("Comparison settings updated successfully.");
    }

    public void compareJobOffers() {
        // Logic to compare job offers
        // You can use the JobComparisonResult class to manage the comparison
        // Display the ranked job offers to the user
        if (jobOffers.size() < 2) {
            System.out.println("You need at least two job offers to compare.");
            return;
        }

        // Create a JobComparisonResult object with the list of job offers
        JobComparisonResults comparisonResult = new JobComparisonResults(jobOffers);

        // Calculate comparison scores
        comparisonResult.compareJobs();

        // Get ranked job offers based on scores
        List<JobOffer> rankedOffers = comparisonResult.getRankedJobOffers();

        // Display the ranked job offers
        System.out.println("\nRanked Job Offers:");
        int rank = 1;
        for (JobOffer offer : rankedOffers) {
            System.out.println("Rank " + rank + ": " + offer.getJobDetails().getTitle() + " at " + offer.getJobDetails().getCompany());
            rank++;
        }
    }

    public void viewSavedJobOffers() {
        if(this.jobOffers.isEmpty()) {
            System.out.println("There are no job offers saved!");
        }
        else {
            this.jobOffers.stream().forEach(JobOffer::printOffer);
        }
    }


}