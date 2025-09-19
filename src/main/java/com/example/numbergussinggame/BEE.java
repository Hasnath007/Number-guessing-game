package com.example.numbergussinggame;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BEE {

    @FXML private TableView<PlayerScore> leaderboardTable;
    @FXML private TableColumn<PlayerScore, String> nameColumn;
    @FXML private TableColumn<PlayerScore, Integer> scoreColumn;
    @FXML private Button backButton;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());


        leaderboardTable.setItems(HelloController.leaderboardData);
    }

    @FXML
    private void backToGame() throws Exception {
        HelloApplication.showGameScene();
    }
}
