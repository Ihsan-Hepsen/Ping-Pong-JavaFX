package pingPong.view.mainMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainMenuView extends BorderPane {

    private Label heading;
    private VBox fieldContainer;
    private Label player1Label;
    private TextField player1NameField;
    private Label player2Label;
    private TextField player2NameField;
    private Label roundLabel;
    private ComboBox<Integer> roundComboBox;
    private Button playButton;
    private final Label version = new Label("v1.4.3");

    public MainMenuView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        heading = new Label("Ping Pong");
        fieldContainer = new VBox();
        player1Label = new Label("Enter name for Player-1");
        player1NameField = new TextField();
        player2Label = new Label("Enter name for Player-2");
        player2NameField = new TextField();
        roundLabel = new Label("Number of Rounds");
        roundComboBox = new ComboBox<>();
        fillComboBox();
        playButton = new Button("PLAY");
    }

    private void layoutNodes() {
        final String headingStyle = "-fx-font-size: 52; -fx-font-family: 'OCR A Extended'; -fx-text-fill: #fff";
        final String buttonStyle = "-fx-font-size: 14; -fx-border-color: #fff; -fx-background-color: #000;" +
                " -fx-text-fill: #fff; -fx-font-family: 'OCR A Extended'; -fx-border-radius: 0.5";
        final String textStyle = "-fx-font-family: 'OCR A Extended'; -fx-text-fill: #fff;" +
                "-fx-font-size: 14";
        // the heading
        heading.setStyle(headingStyle);
        heading.setPadding(new Insets(0, 0 , 35.0, 0));
        setAlignment(heading, Pos.CENTER);
        setTop(heading);

        // player name fields & the VBox
        player1Label.setStyle(textStyle);
        player2Label.setStyle(textStyle);
        roundLabel.setStyle(textStyle);

        player1NameField.setMaxSize(175.0, 15.0);
        player2NameField.setMaxSize(175.0, 15.0);
        roundComboBox.setMaxSize(86.0, 15.0);
        player1NameField.setAlignment(Pos.CENTER);
        player2NameField.setAlignment(Pos.CENTER);

        fieldContainer.setAlignment(Pos.TOP_CENTER);
        fieldContainer.setSpacing(8.0);
        fieldContainer.getChildren().addAll(player1Label, player1NameField, player2Label, player2NameField,
                roundLabel, roundComboBox);
        setCenter(fieldContainer);

        // the 'play' button
        playButton.setStyle(buttonStyle);
        playButton.setPrefSize(135.0, 45.06);
        setMargin(playButton, new Insets(7.0));
        setAlignment(playButton, Pos.CENTER);
        setBottom(playButton);

        // general layout
        setPadding(new Insets(10.0));
        this.setStyle("-fx-background-color: #000");
    }

    private void fillComboBox() {
        roundComboBox.getItems().add(3);
        roundComboBox.getItems().add(5);
        roundComboBox.getItems().add(10);
        roundComboBox.getItems().add(15);
        roundComboBox.getItems().add(20);
    }

    // -*- package private getters -*-
    TextField getPlayer1NameField() {
        return player1NameField;
    }

    TextField getPlayer2NameField() {
        return player2NameField;
    }

    ComboBox<Integer> getRoundComboBox() {
        return roundComboBox;
    }

    Button getPlayButton() {
        return playButton;
    }
}
