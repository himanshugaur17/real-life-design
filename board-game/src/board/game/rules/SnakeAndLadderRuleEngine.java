package board.game.rules;

import java.util.Map;

import board.game.models.Board;
import board.game.models.Dice;
import board.game.models.Game;
import board.game.models.OneDimensionalPos;
import board.game.models.Piece;
import board.game.models.Player;
import board.game.models.Position;

public class SnakeAndLadderRuleEngine implements RuleEngine<OneDimensionalPos> {
    private final Map<Position, Position> snakeAndLadderTargetMap;
    private final Dice dice;
    private Player winner = null;
    private int lastNum = -1;
    private Player lastPlayer = null;

    public SnakeAndLadderRuleEngine(Map<Position, Position> snakeAndLadderTargetMap, Dice dice) {
        this.snakeAndLadderTargetMap = snakeAndLadderTargetMap;
        this.dice = dice;
    }

    @Override
    public boolean isMoveValid(Piece piece, Player currPlayer, Board<OneDimensionalPos> board, Position to) {
        return true;
    }

    @Override
    public void makeMoveAndUpdateBoard(Piece piece, Board<OneDimensionalPos> board, Position to) {
        int num = dice.roll();
        lastNum = num;

        OneDimensionalPos pos = (OneDimensionalPos) piece.getCurrPosition();
        Position newPos = new OneDimensionalPos(pos.getNumber() + num);
        while (snakeAndLadderTargetMap.containsKey(newPos)) {
            newPos = ((OneDimensionalPos) (snakeAndLadderTargetMap.get(newPos)));
        }
        if (((OneDimensionalPos) newPos).getNumber() == 100)
            winner = piece.getOwner();

        piece.setCurrPosition(newPos);
    }

    @Override
    public Player isGameWon(Board<OneDimensionalPos> board) {
        return winner;
    }

    @Override
    public boolean hasGameEnded(Board<OneDimensionalPos> board) {
        return winner != null;
    }

    @Override
    public Player getNextPlayerTurn(Game<OneDimensionalPos> game) {
        return lastNum == 6 ? lastPlayer : game.getNextPlayer();
    }

}
