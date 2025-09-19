package com.example.numbergussinggame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Comparator;

public class HelloController {

    @FXML private TextField guessTextField;
    @FXML private Label feedbackLabel;
    @FXML private ProgressBar attemptsProgressBar;
    @FXML private Label scoreLabel;
    @FXML private Label attemptsLabel;

    private int maxAttempts = 7;
    private int remainingAttempts = maxAttempts;
    private int secretNumber;

    // In-memory leaderboard
    public static ObservableList<PlayerScore> leaderboardData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        secretNumber = generateSecretNumber();
        scoreLabel.setText("Score: 0");
        attemptsLabel.setText("Attempts Left: " + remainingAttempts);
        attemptsProgressBar.setProgress(1.0);
        feedbackLabel.setText("");
    }

    @FXML
    private void handleGuess() throws Exception {
        String input = guessTextField.getText().trim();
        if (input.isEmpty()) {
            feedbackLabel.setText("Please enter a number!");
            return;
        }

        try {
            int guess = Integer.parseInt(input);

            if (guess > secretNumber) feedbackLabel.setText("Too High!");
            else if (guess < secretNumber) feedbackLabel.setText("Too Low!");
            else {
                // Player wins
                int points = remainingAttempts * 10;
                feedbackLabel.setText("Correct! 🎉 You scored " + points + " points.");

                // Prompt player name
                TextInputDialog dialog = new TextInputDialog("Player" + (leaderboardData.size() + 1));
                dialog.setTitle("You won!");
                dialog.setHeaderText("Enter your name for the leaderboard:");
                dialog.setContentText("Name:");
                dialog.showAndWait().ifPresent(name -> addToLeaderboard(name, points));

                resetGame();
                return;
            }

            remainingAttempts--;
            attemptsProgressBar.setProgress((double) remainingAttempts / maxAttempts);
            attemptsLabel.setText("Attempts Left: " + remainingAttempts);

            if (remainingAttempts <= 0) {
                feedbackLabel.setText("Game Over! You scored 0 points.");
                // Lose → 0 points, automatically go to leaderboard
                TextInputDialog dialog = new TextInputDialog("Player" + (leaderboardData.size() + 1));
                dialog.setTitle("Game Over");
                dialog.setHeaderText("Enter your name for the leaderboard:");
                dialog.setContentText("Name:");
                dialog.showAndWait().ifPresent(name -> addToLeaderboard(name, 0));

                resetGame();
            }

        } catch (NumberFormatException e) {
            feedbackLabel.setText("Please enter a valid number!");
        }

        guessTextField.clear();
    }

    private void resetGame() {
        secretNumber = generateSecretNumber();
        remainingAttempts = maxAttempts;
        attemptsProgressBar.setProgress(1.0);
        attemptsLabel.setText("Attempts Left: " + remainingAttempts);
        guessTextField.clear();
        scoreLabel.setText("Score: 0");
    }

    private int generateSecretNumber() {
        return (int)(Math.random() * 5) + 1;
    }

    private void addToLeaderboard(String name, int score) {
        leaderboardData.add(new PlayerScore(name, score));

        // Sort descending by score
        leaderboardData.sort(Comparator.comparingInt(PlayerScore::getScore).reversed());

        // Keep top 5
        if (leaderboardData.size() > 5) {
            leaderboardData.remove(5, leaderboardData.size());
        }
    }

    @FXML
    private void showLeaderboard() throws Exception {
        HelloApplication.showLeaderboardScene();
    }
}
