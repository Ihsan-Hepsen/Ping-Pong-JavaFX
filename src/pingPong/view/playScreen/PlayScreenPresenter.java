package pingPong.view.playScreen;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import pingPong.model.Game;
import pingPong.model.Sprite;
import pingPong.view.gameStatusScreen.GameStatusPresenter;
import pingPong.view.gameStatusScreen.GameStatusView;
import pingPong.view.mainMenu.MainMenuPresenter;
import pingPong.view.mainMenu.MainMenuView;

import java.util.ArrayList;
import java.util.Random;

public class PlayScreenPresenter {

    private final Game model;
    private final PlayScreenView view;
    private AnimationTimer gameLoop;
    private Sprite background;
    private Sprite cubuk1;
    private Sprite cubuk2;
    private Sprite ball;
    private final GraphicsContext context;
    private final ArrayList<String> keyList = new ArrayList<>();
    private final int[] initialBallAngles = {30, 210, -30, -210};
    private final Random random = new Random();
    private int rounds = 0;


    public PlayScreenPresenter(Game model, PlayScreenView view) {
        this.model = model;
        this.view = view;
        this.context = view.getGraphics();
        initTableComponents();
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getScene().setOnKeyPressed(kp -> keyList.add(kp.getCode().toString()));
        view.getScene().setOnKeyReleased(kp -> keyList.clear());
    }

    private void updateView() {
        enableGameFlow();
    }

    private void initTableComponents() {
        background = new Sprite("/background.png");
        background.getPosition().set(525, 320);

        cubuk1 = new Sprite("/stick.png");
        cubuk1.getPosition().set(20, 300);
        cubuk1.getVelocity().set(50, 0);

        cubuk2 = new Sprite("/stick.png");
        cubuk2.getPosition().set(1015, 300);
        cubuk2.getVelocity().set(0, 50);

        ball = new Sprite("/ball_2.png");
        ball.getPosition().set(525.5, 300);
        ball.getVelocity().setLength(230);
        ball.getVelocity().setAngle(initialBallAngles[random.nextInt(4)]);

    }

    private void enableGameFlow() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long nanoTime) {
                ball.update(1 / 60.0);
                cubuk1.update(1 / 60.0);
                cubuk2.update(1 / 60.0);

                bounceOnWall();
                bounceOnCubuk();
                manageCubukMovement();

                if (score()) {
                    ++rounds;
                    if (rounds == model.getRounds()) {
                        gameLoop.stop();
                        gameOver();
                    } else {
                        displayGameStatus();
                    }
                }

                ball.getVelocity().setLength(ball.getVelocity().getLength() + .45);
                background.render(context);
                ball.render(context);
                cubuk1.render(context);
                cubuk2.render(context);
            }
        };
        gameLoop.start();
    }


    private void manageCubukMovement() {
        if (keyList.contains("DOWN") && cubuk2.getPosition().getY() <= 533) {
            cubuk2.getVelocity().setLength(140.0);
            cubuk2.getVelocity().setAngle(90);
        } else if (keyList.contains("UP") && cubuk2.getPosition().getY() >= 68.2) {
            cubuk2.getVelocity().setLength(140.0);
            cubuk2.getVelocity().setAngle(270);
        } else {
            cubuk2.getVelocity().setLength(0);
        }

        if (keyList.contains("S") && cubuk1.getPosition().getY() <= 533) {
            cubuk1.getVelocity().setLength(140.0);
            cubuk1.getVelocity().setAngle(90);
        } else if (keyList.contains("W") && cubuk1.getPosition().getY() >= 68.2) {
            cubuk1.getVelocity().setLength(140.0);
            cubuk1.getVelocity().setAngle(270);
        } else {
            cubuk1.getVelocity().setLength(0);
        }
    }

    private void bounceOnWall() {
        if (ball.getPosition().getY() >= 600 || ball.getPosition().getY() <= 1.1) {
            ball.getVelocity().set(ball.getVelocity().getX(), -ball.getVelocity().getY());
        }
    }

    private void bounceOnCubuk() {
        if (ball.overlaps(cubuk1) || ball.overlaps(cubuk2)) {
            ball.getVelocity().set(-ball.getVelocity().getX(), ball.getVelocity().getY());
        }
    }

    private boolean score() {
        if (ball.getPosition().getX() >= 1035) {
            model.updateScore(1, model.getPlayer1().getScore() + 1);
            return true;
        } else if (ball.getPosition().getX() <= 0) {
            model.updateScore(2, model.getPlayer2().getScore() + 1);
            return true;
        }
        return false;
    }

    private void displayGameStatus() {
        GameStatusView statusView = new GameStatusView();
        new GameStatusPresenter(model, statusView);
        Stage gameStatusStage = new Stage();
        gameStatusStage.setResizable(false);
        gameStatusStage.setTitle("Game Status");
        gameStatusStage.initModality(Modality.APPLICATION_MODAL);
        gameStatusStage.setScene(new Scene(statusView));
        gameStatusStage.setWidth(400.0);
        gameStatusStage.setHeight(450.0);

        gameLoop.stop();
        PauseTransition delay = new PauseTransition(Duration.seconds(3.75));
        delay.setOnFinished(f -> {
            gameStatusStage.close();
            initTableComponents();
            gameLoop.start();
        });
        gameStatusStage.show();
        delay.play();
    }

    private void gameOver() {
        GameStatusView statusView = new GameStatusView();
        new GameStatusPresenter(model, statusView);
        statusView.getHeading().setText("Game Over!");
        Stage gameStatusStage = new Stage();
        gameStatusStage.setResizable(false);
        gameStatusStage.setTitle("Game Over!");
        gameStatusStage.initModality(Modality.APPLICATION_MODAL);
        gameStatusStage.setScene(new Scene(statusView));
        gameStatusStage.setWidth(400.0);
        gameStatusStage.setHeight(450.0);

        PauseTransition delay = new PauseTransition(Duration.seconds(3.75));
        delay.setOnFinished(f -> {
            MainMenuView mainView = new MainMenuView();
            new MainMenuPresenter(mainView);
            gameStatusStage.close();
            view.getScene().setRoot(mainView);
            mainView.getScene().getWindow();
        });
        gameStatusStage.show();
        delay.play();
    }
}
