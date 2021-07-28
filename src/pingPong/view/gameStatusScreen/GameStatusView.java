package pingPong.view.gameStatusScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameStatusView extends BorderPane {

    private Label heading;
    private Label player1;
    private Label player2;
    private Label p1Score;
    private Label p2Score;
    private Label winnerLabel;
    private Button quitButton;

    private HBox centerContainer;
    private HBox bottomContainer;
    private VBox topContainer;
    private VBox p1container;
    private VBox p2container;


    public GameStatusView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        heading = new Label("GAME STATUS");
        player1 = new Label();
        player2 = new Label();
        p1Score = new Label("0");
        p2Score = new Label("0");
        winnerLabel = new Label();
        quitButton = new Button("QUIT");
        centerContainer = new HBox();
        topContainer = new VBox();
        bottomContainer = new HBox();
        p1container = new VBox();
        p2container = new VBox();
    }

    private void layoutNodes() {
        setAlignment(heading, Pos.CENTER);
        setAlignment(winnerLabel, Pos.CENTER);
        topContainer.setAlignment(Pos.CENTER);
        topContainer.getChildren().addAll(heading, winnerLabel);
        setTop(topContainer);

        p1container.getChildren().addAll(player1, p1Score);
        p2container.getChildren().addAll(player2, p2Score);
        centerContainer.getChildren().addAll(p1container, p2container);

        setAlignment(player1, Pos.TOP_LEFT);
        setAlignment(player2, Pos.TOP_RIGHT);
        centerContainer.setAlignment(Pos.TOP_CENTER);
        centerContainer.setSpacing(50.0);
        centerContainer.setPadding(new Insets(50.0, 0, 5.0, 0));
        p1container.setAlignment(Pos.TOP_LEFT);
        p2container.setAlignment(Pos.TOP_RIGHT);

        player1.setPadding(new Insets(0, 0, 15.0, 0));
        player2.setPadding(new Insets(0, 0, 15.0, 0));

        setCenter(centerContainer);

        bottomContainer.getChildren().addAll(quitButton);
        setAlignment(quitButton, Pos.CENTER_LEFT);
        bottomContainer.setAlignment(Pos.BASELINE_CENTER);
        bottomContainer.setPadding(new Insets(3.0));
        bottomContainer.setSpacing(60.0);
        setBottom(bottomContainer);

        styleNodes();
        this.setStyle("-fx-background-color: #000");
        this.setPadding(new Insets(10.0));
    }

    private void styleNodes() {
        final String buttonStyle = "-fx-font-size: 20; -fx-border-color: #fff; -fx-background-color: #000;" +
                " -fx-text-fill: #fff; -fx-font-family: 'OCR A Extended'; -fx-border-radius: 1.3";
        final String commonProperties = "-fx-font-family: 'OCR A Extended'; -fx-text-fill: #fff";
        heading.setStyle("-fx-font-size: 45;" + commonProperties);
        winnerLabel.setStyle("-fx-font-size: 24;" + commonProperties);
        player1.setStyle("-fx-font-size: 25;" + commonProperties);
        player2.setStyle("-fx-font-size: 25;" + commonProperties);
        p1Score.setStyle("-fx-font-size: 70;" + commonProperties);
        p2Score.setStyle("-fx-font-size: 70;" + commonProperties);
        quitButton.setStyle(buttonStyle);
    }

    public Label getHeading() {
        return heading;
    }

    Button getQuitButton() {
        return quitButton;
    }

    Label getPlayer1() {
        return player1;
    }

    Label getPlayer2() {
        return player2;
    }

    Label getP1Score() {
        return p1Score;
    }

    Label getP2Score() {
        return p2Score;
    }

    Label getWinnerLabel() {
        return winnerLabel;
    }
}
