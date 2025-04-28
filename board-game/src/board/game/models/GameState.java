package board.game.models;

import java.util.Map;

public record GameState(Map<String, String> pieceToPlayer) {
}
