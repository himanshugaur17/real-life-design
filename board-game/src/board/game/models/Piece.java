package board.game.models;

public abstract class Piece {
    protected Player owner;
    protected Position currPosition;

    public Piece(Player owner, Position currPosition) {
        this.owner = owner;
        this.currPosition = currPosition;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Position getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(Position currPosition) {
        this.currPosition = currPosition;
    }

}
