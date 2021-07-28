package pingPong.view.playScreen;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;

public class PlayScreenView extends BorderPane {

    private Canvas canvas;

    public PlayScreenView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        canvas = new Canvas(1050.0, 640.0);
    }

    private void layoutNodes() {
        setCenter(canvas);
    }


    GraphicsContext getGraphics() {
        return canvas.getGraphicsContext2D();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
