package board.game.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SnakeAndLadderBoard implements Board<OneDimensionalPos> {

    private Map<Position, Piece> positionMap;
    List<Position> oneDPositions = new ArrayList<>();

    @Override
    public void initialize() {
        for (int i = 0; i <= 100; i++) {
            oneDPositions.add(new OneDimensionalPos(i));
        }
    }

    @Override
    public GameState getGameState() {
        return new GameState(positionMap.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(), entry -> entry.getValue().owner.name())));
    }

    @Override
    public Piece getPiece(OneDimensionalPos position) {
        return positionMap.get(position);
    }

    @Override
    public void setPiece(Piece piece, OneDimensionalPos position) {
        positionMap.put(position, piece);
    }

}
