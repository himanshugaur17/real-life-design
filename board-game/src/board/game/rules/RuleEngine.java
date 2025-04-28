package board.game.rules;

import board.game.models.Board;
import board.game.models.Game;
import board.game.models.Piece;
import board.game.models.Player;
import board.game.models.Position;

public interface RuleEngine<P extends Position> {
    boolean isMoveValid(Piece piece, Player currPlayer, Board<P> board, Position to);

    void makeMoveAndUpdateBoard(Piece piece, Board<P> board, Position to);

    Player isGameWon(Board<P> board);

    boolean hasGameEnded(Board<P> board);

    Player getNextPlayerTurn(Game<P> game);
}
