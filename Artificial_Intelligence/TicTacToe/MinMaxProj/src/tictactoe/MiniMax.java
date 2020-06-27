/*
 *  Copyright 2019.
 *  Alejadnro Maruri.
 *  ale_maruri14@hotmail.com
 *  eamaruri@estud.usfq.edu.ec
 */
package tictactoe;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents minimax algorithm for AI adversary games.
 * Position works as an action
 */
public class MiniMax implements Player { 
    @Override
    public Position getNextMove(TicTacToeBoard state) throws Exception { 
       return minimaxDecision(state);
    }

    @Override
    public int getPlayerType() {
       return Player.MINIMAX_PLAYER;
    }  
    
    
    /* To compute the best move for the current state
       Returns a position like an action
    */
    private Position minimaxDecision(TicTacToeBoard state) {
        Utility result;
        
        if (state.getTurn() == TicTacToeBoard.PLAYER_X){
            result = maxValue(state, 0);
            System.out.printf("Best Position for %s's: %d,%d minimax\n",
               TicTacToeBoard.X, result.position.row, result.position.col);
        }
        else {
            result = minValue(state, 0);
            System.out.printf("Best Position for %s's: %d,%d minimax\n", 
                 TicTacToeBoard.O, result.position.row, result.position.col);       
        }
        
        return result.position;
    }
    
    
    /* Method to compute best move for player X (maximizing player)
       Recursively calls minTurn()
       Returns an utility object that contains a Position with a score
    */
    private Utility maxValue(TicTacToeBoard state, int depth) {
        if (isTerminalState(state)) 
            return new Utility(score(state, depth), null);
        
        Utility max = new Utility(Integer.MIN_VALUE, new Position());
        
        // Collection of possible actions given a state
        for(Position pos : getAvailablePositions(state)){
            if (state.getState(pos.row, pos.col).equals(TicTacToeBoard.BLANK)) {
                try {
                    state.setState(pos.row, pos.col, TicTacToeBoard.X);
                } catch (Exception ex) {
                    System.out.println("Error in max Turn: " + ex.toString());
                }
                    
                Utility currentUtility = minValue(state, depth + 1);
                   
                if (currentUtility.value > max.value) {
                    max.value = currentUtility.value;
                    max.position.row = pos.row;
                    max.position.col = pos.col;
                }
                    
                state.cleanSquare(pos.row, pos.col);
             }
        }
        
        return max;
    }
    
    
    /* Method to compute best move for the minimizing player
       Recursively calls maxTurn()
       Returns an utility object that contains a Position with a score
    */
    private Utility minValue(TicTacToeBoard state, int depth) {
        if (isTerminalState(state))
            return new Utility(score(state, depth), null);
        
        Utility min = new Utility(Integer.MAX_VALUE, new Position());
        
        // Collection of possible actions given a state
        for(Position pos : getAvailablePositions(state)){
            try {
                        state.setState(pos.row, pos.col, TicTacToeBoard.O);
                    } catch (Exception ex) {
                        System.out.println("Error in minTurn: " + ex.toString());
                    }
                    
                    Utility currentUtility = maxValue(state, depth + 1);
                    
                    if (currentUtility.value < min.value) {
                        min.value = currentUtility.value;
                        min.position.row = pos.row;
                        min.position.col = pos.col;
                    }
                    
                    state.cleanSquare(pos.row, pos.col);
        }
        
        return min;
    }
    
    
    /*Returns true if a player has won or if is Game over; otherwise returns false*/
    private boolean isTerminalState(TicTacToeBoard state) {
        try {
            return hasPlayerWon(state, TicTacToeBoard.PLAYER_X) 
                    || hasPlayerWon(state, TicTacToeBoard.PLAYER_O) || state.isGameOver();
        } catch (Exception ex) {
            System.out.println("Error in \"isTerminalState:\" " + ex.toString());
        }
        return false;
    }
    
    
    /*Returns true if a given player has won*/
    private boolean hasPlayerWon(TicTacToeBoard state, int player) {
        try {
            return state.isWin(player);
        } catch (Exception ex) {
            System.out.println("Error getting player won: " + ex.toString());
        }
        return false;
    }
    
    
    /*Retruns a score given a TicTacToe state and a depth*/
    private int score(TicTacToeBoard state, int depth) {
        if (hasPlayerWon(state, TicTacToeBoard.PLAYER_X)) return 10 - depth;
        else if (hasPlayerWon(state, TicTacToeBoard.PLAYER_O)) return depth - 10;
        
        return 0;
    }
    
    
    /*Prints a given state as a matrix im prompt*/
    private void printState(TicTacToeBoard state) {
        System.out.println("\nState of TicTacToe game");
        for (int row = 0; row < TicTacToeBoard.SIZE; row++) {
            for (int col = 0; col < TicTacToeBoard.SIZE; col++) {
                System.out.print(state.getState(row, col) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    /*Returns a collection with the available Positions works like a collection of States*/
    private Collection<Position> getAvailablePositions(TicTacToeBoard state){
        ArrayList<Position> availableMoves = new ArrayList<>();
        for (int row = 0; row < TicTacToeBoard.SIZE; row++) {
            for (int col = 0; col < TicTacToeBoard.SIZE; col++) {
                if (state.getState(row, col).equals(TicTacToeBoard.BLANK)) {
                    availableMoves.add(new Position(row, col));
                }
            }
        }
        return availableMoves;
    }
    
    
    
    /* A class that represents a Utility using a pair Score-Position (could be a Map)
    */
    private class Utility{
        int value;
        Position position;

        /*Constructor of a Score and Position pair*/
        public Utility(int value, Position position) {
            this.value = value;
            this.position = position;
        }
    }
}