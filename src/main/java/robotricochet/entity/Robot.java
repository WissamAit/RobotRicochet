package robotricochet.entity;

/**
 * Robot
 */
public class Robot {

    private static final boolean debug = true; // debug value to test program

    private Color color;
    private Position position;

    public Robot(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    public Color getColor() {
        return this.color;
    }
}