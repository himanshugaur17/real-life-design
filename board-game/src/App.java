import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.game.models.BasicDiceImpl;
import board.game.models.Board;
import board.game.models.Game;
import board.game.models.OneDimensionalPos;
import board.game.models.Piece;
import board.game.models.Player;
import board.game.models.PlayerToken;
import board.game.models.SnakeAndLadderBoard;
import board.game.rules.RuleEngine;
import board.game.rules.SnakeAndLadderRuleEngine;

public class App {
    public static void main(String[] args) throws Exception {
        List<Player> players = List.of(new Player("1", "himanshu"), new Player("2", "vaus"));
        Map<Player, Piece> playerTokenMap = new HashMap<>();
        playerTokenMap.put(players.get(0), new PlayerToken(players.get(0), new OneDimensionalPos(0)));
        playerTokenMap.put(players.get(1), new PlayerToken(players.get(1), new OneDimensionalPos(0)));
        Board<OneDimensionalPos> board = new SnakeAndLadderBoard();

        RuleEngine<OneDimensionalPos> ruleEngine = new SnakeAndLadderRuleEngine(new HashMap<>(), new BasicDiceImpl());
        Game<OneDimensionalPos> game = new Game<>(ruleEngine, board, players);
        game.startGame();
        while (!game.hasGameEnded()) {
            Player player = game.getNextPlayer();
            game.makeMove(player, playerTokenMap.get(player), null);
        }
    }
}
