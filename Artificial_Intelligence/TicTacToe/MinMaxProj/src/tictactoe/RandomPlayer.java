package tictactoe;

import java.util.ArrayList;
import java.util.Random;

/**
 * A random player module.
 *
 * @author wong
 *
 */
public class RandomPlayer implements Player {

    /**
     * Random number generator
     */
    private final Random rand;

    /**
     * Constructor
     */
    public RandomPlayer() {
        this.rand = new Random();
    }

    /**
     * Returns the next move to do.
     *
     * @param state The current board state
     * @return The next move.
     */
    @Override
    public Position getNextMove(TicTacToeBoard state) throws Exception {
        return getRandomMove(state);
    }

    /**
     * Returns a random move
     *
     * @param state The current board state
     * @return A random move
     */
    private Position getRandomMove(TicTacToeBoard state) {
        ArrayList<Position> availableMoves = new ArrayList<>();
        for (int row = 0; row < TicTacToeBoard.SIZE; row++) {
            for (int col = 0; col < TicTacToeBoard.SIZE; col++) {
                if (state.getState(row, col).equals(TicTacToeBoard.BLANK)) {
                    availableMoves.add(new Position(row, col));
                }
            }
        }
        return (Position) availableMoves.get(rand.nextInt(availableMoves.size()));
    }

    /**
     * Gets the player type
     *
     * @return The player type
     */
    @Override
    public int getPlayerType() {
        return Player.RANDOM_PLAYER;
    }

}
