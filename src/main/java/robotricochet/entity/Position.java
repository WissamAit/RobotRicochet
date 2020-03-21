package robotricochet.entity;

/**
 * Position
 */
public class Position {


    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }

    public boolean isTheSameAs(Position position) {
        return (this.getX() == position.getX() && this.getY() == position.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return x * 1000000000 + y;
    }
}