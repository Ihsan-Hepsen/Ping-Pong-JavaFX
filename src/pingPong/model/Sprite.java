package pingPong.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {

    private final Vector position;
    private final Vector velocity;
    private double rotation; // degrees
    private final Rectangle boundary;
    private Image image;

    public Sprite() {
        position = new Vector();
        velocity = new Vector();
        rotation = 0;
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
        context.rotate(rotation);
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

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public Image getImage() {
        return image;
    }
}
