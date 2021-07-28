package pingPong.view.mainMenu;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pingPong.model.Game;
import pingPong.model.Player;
import pingPong.view.playScreen.PlayScreenPresenter;
import pingPong.view.playScreen.PlayScreenView;

public class MainMenuPresenter {

    private final MainMenuView view;

    public MainMenuPresenter(MainMenuView view) {
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getPlayButton().setOnAction(click -> switchToGameScreen());
    }

    private void updateView() {

    }

    private void switchToGameScreen() {
        PlayScreenView playView = new PlayScreenView();
        view.getScene().setRoot(playView);
        Stage stage = (Stage) playView.getScene().getWindow();
//        stage.setScene(new Scene(playView));
        new PlayScreenPresenter(new Game(new Player(view.getPlayer1NameField().getText()),
                new Player(view.getPlayer2NameField().getText()), view.getRoundComboBox().getValue()), playView);
//        playView.getScene().setOnKeyPressed(kp -> System.out.println(kp.getCode().toString()));
    }
}
