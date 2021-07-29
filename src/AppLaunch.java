import pingPong.view.mainMenu.MainMenuPresenter;
import pingPong.view.mainMenu.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLaunch extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MainMenuView view = new MainMenuView();
        new MainMenuPresenter(view);
        stage.setTitle("Ping Pong");
        stage.setWidth(1050.0);
        stage.setHeight(640.0);
        stage.setResizable(false);
        stage.setScene(new Scene(view));
        stage.show();
    }
}
