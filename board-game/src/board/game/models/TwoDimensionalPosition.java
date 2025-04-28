package board.game.models;

public class TwoDimensionalPosition implements Position {
    private final int x, y;

    public TwoDimensionalPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
