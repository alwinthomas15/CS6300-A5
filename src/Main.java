import models.ComparisonService;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Job Comparison App!");
        ComparisonService ui = new ComparisonService();
        ui.start();
    }
}
