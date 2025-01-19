import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min = 1;  // Default minimum number
        int max = 100;  // Default maximum number
        int maxAttempts = 5;  // Maximum attempts allowed
        int totalScore = 0;  // Total score across rounds
        boolean playAgain = true;

        System.out.println("Welcome to the Enhanced Number Guessing Game!");
        System.out.println("Try to guess the number within the specified range.");

        // Leaderboard file
        File leaderboardFile = new File("leaderboard.txt");

        // Game loop for multiple rounds
        while (playAgain) {
            System.out.println("\nSelect Difficulty:");
            System.out.println("1. Easy (Range: 1-50)");
            System.out.println("2. Medium (Range: 1-100)");
            System.out.println("3. Hard (Range: 1-200)");
            System.out.print("Enter your choice (1/2/3): ");
            int difficulty = scanner.nextInt();

            // Set range based on difficulty
            switch (difficulty) {
                case 1 -> {
                    min = 1;
                    max = 50;
                    maxAttempts = 7;
                }
                case 2 -> {
                    min = 1;
                    max = 100;
                    maxAttempts = 5;
                }
                case 3 -> {
                    min = 1;
                    max = 200;
                    maxAttempts = 3;
                }
                default -> System.out.println("Invalid choice! Defaulting to Medium difficulty.");
            }

            int targetNumber = random.nextInt(max - min + 1) + min;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nStarting a new round!");
            System.out.println("You have " + maxAttempts + " attempts to guess the number between " + min + " and " + max);

            long startTime = System.currentTimeMillis();  // Start timer

            // Game loop for one round
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    long endTime = System.currentTimeMillis();  // End timer
                    System.out.println("Congratulations! You guessed the correct number.");
                    long timeTaken = (endTime - startTime) / 1000;  // Calculate time in seconds
                    System.out.println("You took " + timeTaken + " seconds to guess the number.");

                    totalScore += (maxAttempts - attempts + 1) * 10;  // Score calculation
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts! The correct number was: " + targetNumber);
            }

            // Ask if the user wants to play another round
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("\nGame Over! Your total score is: " + totalScore);
        saveToLeaderboard(scanner, leaderboardFile, totalScore);

        System.out.println("Leaderboard:");
        displayLeaderboard(leaderboardFile);

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    // Save score to leaderboard
    private static void saveToLeaderboard(Scanner scanner, File leaderboardFile, int totalScore) throws IOException {
        System.out.print("Enter your name for the leaderboard: ");
        String playerName = scanner.next();

        // Append to leaderboard file
        try (FileWriter writer = new FileWriter(leaderboardFile, true)) {
            writer.write(playerName + ": " + totalScore + "\n");
        }
    }

    // Display leaderboard
    private static void displayLeaderboard(File leaderboardFile) throws IOException {
        if (!leaderboardFile.exists()) {
            System.out.println("No leaderboard entries yet.");
            return;
        }

        // Read and print leaderboard entries
        try (Scanner fileScanner = new Scanner(leaderboardFile)) {
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        }
    }
}
