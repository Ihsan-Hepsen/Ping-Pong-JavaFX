package pingPong.view.gameStatusScreen;

import javafx.application.Platform;
import pingPong.model.Game;

public class GameStatusPresenter {

    private final Game model;
    private final GameStatusView view;

    public GameStatusPresenter(Game model, GameStatusView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getQuitButton().setOnAction(clicked -> Platform.exit());
    }

    private void updateView() {
        view.getPlayer1().setText(model.getPlayer1().getName());
        view.getPlayer2().setText(model.getPlayer2().getName());
        view.getP1Score().setText("" + model.getPlayer1().getScore());
        view.getP2Score().setText("" + model.getPlayer2().getScore());
    }

}
