package board.game.models;

import java.util.List;

import board.game.rules.RuleEngine;

public class Game<P extends Position> {
    private final RuleEngine<P> ruleEngine;
    private final Board<P> board;
    private final List<Player> players;
    int playerIndex = 0;

    public Game(RuleEngine<P> ruleEngine, Board<P> board, List<Player> players) {
        this.ruleEngine = ruleEngine;
        this.board = board;
        this.players = players;
    }

    public void startGame() {
        board.initialize();
    }

    public GameState makeMove(Player player, Piece piece, Position to) {
        boolean isMoveValid = ruleEngine.isMoveValid(piece, player, board, to);
        if (!isMoveValid)
            throw new RuntimeException("invalid move");
        ruleEngine.makeMoveAndUpdateBoard(piece, board, to);
        playerIndex = (playerIndex + 1) % players.size();
        return board.getGameState();
    }

    public boolean hasGameEnded() {
        return ruleEngine.hasGameEnded(board);
    }

    public Player getWinner() {
        boolean gameOver = hasGameEnded();
        if (gameOver)
            return ruleEngine.isGameWon(board);
        else
            throw new RuntimeException("game is still in progress");
    }

    public Player getNextPlayer() {
        return players.get(playerIndex);
    }
}
