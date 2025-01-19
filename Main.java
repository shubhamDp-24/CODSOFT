import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Student Grade Calculator!");

        // Step 1: Input - Number of subjects and marks for each subject
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        int[] marks = new int[numberOfSubjects];
        int totalMarks = 0;

        System.out.println("Enter the marks obtained in each subject (out of 100): ");
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
            totalMarks += marks[i];
        }

        // Step 2: Calculate Total Marks and Average Percentage
        double averagePercentage = (double) totalMarks / numberOfSubjects;

        // Step 3: Grade Calculation
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Step 4: Display Results
        System.out.println("\n--- Results ---");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
        System.out.println("Grade: " + grade);

        System.out.println("\nThank you for using the Student Grade Calculator!");
        scanner.close();
    }
}
