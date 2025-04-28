package board.game.models;

public interface Board<P extends Position> {
    Piece getPiece(P position);

    void initialize();

    void setPiece(Piece piece, P position);

    GameState getGameState();
}
