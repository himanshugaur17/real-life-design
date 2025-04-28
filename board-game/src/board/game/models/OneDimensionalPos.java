package board.game.models;

public class OneDimensionalPos implements Position {
    private int number;

    public OneDimensionalPos(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}
