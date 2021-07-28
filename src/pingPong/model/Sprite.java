package pingPong.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {

    private final Vector position;
    private final Vector velocity;
    private double bounce; // degrees
    private final Rectangle boundary;
    private Image image;

    public Sprite() {
        position = new Vector();
        velocity = new Vector();
        bounce = 0;
        boundary = new Rectangle();
    }

    public Sprite(String imageFileName) {
        this();
        setImage(imageFileName);
    }

    public void setImage(String imageFileName) {
        this.image = new Image(imageFileName);
        this.boundary.setSize(image.getWidth(), image.getHeight());
    }


    public Rectangle getBoundary() {
        this.boundary.setPosition(position.getX(), position.getY());
        return this.boundary;
    }

    public boolean overlaps(Sprite other) {
        return getBoundary().didCollide(other.getBoundary());
    }

    public void update(double deltaTime) {
        position.add(velocity.getX() * deltaTime, velocity.getY() * deltaTime);
    }

    public void render(GraphicsContext context) {
        context.save();
        context.translate(position.getX(), position.getY());
        context.rotate(bounce);
        context.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        context.drawImage(image, 0, 0);
        context.restore();
    }


    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public double getBounce() {
        return bounce;
    }

    public void setBounce(double bounce) {
        this.bounce = bounce;
    }

    public Image getImage() {
        return image;
    }
}
