package models;

import java.util.*;

public class ComparisonService {

    private List<JobOffer> jobOffers;
    private ComparisonSettings comparisonSettings;
    private JobOffer currentJob;
    private Scanner scanner;

    public ComparisonService() {
        this.jobOffers = new ArrayList<>();
        this.comparisonSettings = new ComparisonSettings();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        showMainMenu();
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Enter or edit current Job Details");
            System.out.println("2. Enter Job Offer");
            System.out.println("3. Adjust Comparison Settings");
            System.out.println("4. Compare Job Offers");
            System.out.println("5. View Saved Job Offers");
            System.out.println("6. View current Job Offer");
            System.out.println("7. Exit");
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
                    viewCurrentJobOffer();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void enterOrEditJobDetails() {

        if (Objects.nonNull(this.currentJob)) {
            //Print current job
            this.currentJob.printOffer();
            System.out.println("=============================");
            System.out.println("Enter 1 to edit the current job");
            System.out.println("Enter 2 to return to main menu");
            System.out.print("Enter: ");
            int choice = Integer.valueOf(scanner.nextLine());

            switch (choice) {
                case 1:
                    editCurrentJob();
                    break;
                case 2:
                    showMainMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } else {
            //Prompt users to enter new job details of an offer
            System.out.println("There is no current job saved. Please enter details for your current job!");
            JobOffer jobOffer = jobDetailsPrompt();
            jobOffer.setId(0);
            persistJobOffer(saveOrCancelPrompt(), jobOffer, true);
        }
    }

    public void editCurrentJob() {
        JobOffer editedCurrentJob = jobDetailsPrompt();
        editedCurrentJob.setId(0);
        persistJobOffer(saveOrCancelPrompt(),editedCurrentJob,true);
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
                .orElse(0);


        JobOffer currentJobOffer = new JobOffer(count + 1, jobDetails);

        return currentJobOffer;
    }

    public String saveOrCancelPrompt() {
        System.out.print("Do you want to save this job offer? (Enter 'Yes' or 'No'): ");
        return scanner.nextLine();

    }

    public boolean persistJobOffer(String val, JobOffer jobOffer, boolean isCurrentJob) {
        if (val.equalsIgnoreCase("yes") || val.equalsIgnoreCase("y")) {
            if (isCurrentJob) {
                this.currentJob = jobOffer;
            } else {
                this.jobOffers.add(jobOffer);
            }
            System.out.println("Job offer with the Id: " + jobOffer.getId() + " has been added successfully!");
            return true;
        } else {
            System.out.println("Job offer with the Id: " + jobOffer.getId() + " has not been added!");
            return false;
        }
    }

    public void enterJobOffer() {
        // Logic to enter a new job offer
        // Create a JobOffer object and add it to the jobOffers list
        JobOffer jobOffer = jobDetailsPrompt();

        //perhaps make this a boolean? to see if the user saved it or not, so the user can compare this SPECIFIC one with a specific ONE from the list of the arrayList
        boolean isSaved = persistJobOffer(saveOrCancelPrompt(), jobOffer, false);

        // Next steps
        System.out.println("\nOptions (Enter a number):");
        System.out.println("1. Enter another offer");
        System.out.println("2. Return to main menu");
        System.out.println("3. Compare offer (if saved)");
        System.out.println("4. Exit");

        int val = Integer.valueOf(scanner.nextLine());

        switch (val) {
            case 1:
                //Loops back to this
                enterJobOffer();
                break;
            case 2:
                showMainMenu();
                break;
            case 3:
                //Exits back to main menu
                if (isSaved && Objects.nonNull(this.currentJob)) {
                    compareLastSavedJobWithCurrentJob(jobOffer);
                } else {
                    System.out.println("Either you did not save the last job offer, or the current job is not available to compare!");
                }
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
        System.out.print("Enter yearly salary weight: ");
        int yearlySalaryWeight = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter yearly bonus weight: ");
        int yearlyBonusWeight = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter gym membership weight: ");
        int gymMembershipWeight = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter leave time weight: ");
        int leaveTimeWeight = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter 401K match weight: ");
        int retirementMatch = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter pet insurance weight: ");
        int petInsuranceWeight = Integer.valueOf(scanner.nextLine());

        comparisonSettings.setYearlySalaryWeight(yearlySalaryWeight);
        comparisonSettings.setYearlyBonusWeight(yearlyBonusWeight);
        comparisonSettings.setGymMembershipWeight(gymMembershipWeight);
        comparisonSettings.setLeaveTimeWeight(leaveTimeWeight);
        comparisonSettings.setRetirementMatch(retirementMatch);
        comparisonSettings.setPetInsuranceWeight(petInsuranceWeight);
        System.out.println("Comparison settings updated successfully.");
    }

    public void compareJobOffers() {
        // Logic to compare job offers
        // Display the ranked job offers to the user
        if (jobOffers.size() < 2 ) {
            System.out.println("You need at least two job offers to compare.");
            return;
        }

        // Create a JobComparisonResult object with the list of job offers
        JobComparisonResults comparisonResult = new JobComparisonResults(jobOffers, comparisonSettings);

        if(Objects.nonNull(this.currentJob)) {
            comparisonResult.addJobOffer(this.currentJob);
        }

        // Calculate comparison scores
        comparisonResult.compareJobOffers();

        // Get ranked job offers based on scores
        List<JobOffer> rankedOffers = comparisonResult.getRankedJobOffers();

        // Display the ranked job offers
        System.out.println("\nRanked Job Offers:");
        for (JobOffer offer : rankedOffers) {
            double score = comparisonResult.getComparisonScores().get(offer);
            System.out.println("Score: " + score + ", Title: " + offer.getJobDetails().getTitle() + ", Company: " + offer.getJobDetails().getCompany());

        }
        System.out.println("Choose an action");
        System.out.println("1. Compare two jobs");
        System.out.println("2. Return to main menu");
        System.out.print("Enter: ");

        int val = Integer.valueOf(scanner.nextLine());

        switch (val) {
            case 1:
                compareJobsInDetail(comparisonResult);
            case 2:
                showMainMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }


    }

    public void compareJobsInDetail(JobComparisonResults comparisonResult) {
        System.out.println("Choose two jobs to compare in detail");
        System.out.print("Enter the Job Id of the first job: ");
        int choiceA = Integer.valueOf(scanner.nextLine());
        System.out.print("Enter the Job Id of the second job: ");
        int choiceB = Integer.valueOf(scanner.nextLine());

        Optional<JobOffer> jobA = comparisonResult.getJobOffers().stream()
                .filter(jobOffer -> jobOffer.getId()==choiceA)
                .findFirst();
        Optional<JobOffer> jobB = comparisonResult.getJobOffers().stream()
                .filter(jobOffer -> jobOffer.getId()==choiceB)
                .findFirst();

        if(jobA.isPresent() && jobB.isPresent()) {
            System.out.println();
            System.out.println("------- First Job ----------");
            jobA.get().printOffer(comparisonResult.getComparisonScores().get(jobA.get()));
            System.out.println("------- Second Job ----------");
            jobB.get().printOffer(comparisonResult.getComparisonScores().get(jobB.get()));
        }
        else {
            System.out.println("Id(s) given are not present in the database, thus cannot be compared!");
        }

    }

    public void compareLastSavedJobWithCurrentJob(JobOffer jobOffer) {
        // Create a JobComparisonResult object with the list of job offers
        JobComparisonResults comparisonResult = new JobComparisonResults(new ArrayList<>(Arrays.asList(jobOffer,this.currentJob)), comparisonSettings);
        // Calculate comparison scores
        comparisonResult.compareJobOffers();

        // Get ranked job offers based on scores
        List<JobOffer> rankedOffers = comparisonResult.getRankedJobOffers();

        // Display the ranked job offers
        System.out.println("\nRanked Job Offers:");
        for (JobOffer offer : rankedOffers) {
            double score = comparisonResult.getComparisonScores().get(offer);
            System.out.println("Score: " + score + ", Title: " + offer.getJobDetails().getTitle() + ", Company: " + offer.getJobDetails().getCompany());

        }
    }

    public void viewSavedJobOffers() {
        if (this.jobOffers.isEmpty()) {
            System.out.println("There are no job offers saved!");
        } else {
            this.jobOffers.stream().forEach(JobOffer::printOffer);
        }
    }

    public void viewCurrentJobOffer() {
        if (Objects.nonNull(this.currentJob)) {
            this.currentJob.printOffer();
        } else {
            System.out.println("There is no current job saved!");
        }
    }


}