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
 *
 * @author EAMT
 */

public class AlphaBeta implements Player { 
    @Override
    public Position getNextMove(TicTacToeBoard state) throws Exception {
        return AlphaBetaSearch(state);
    }
    
    @Override
    public int getPlayerType() {
        return ALPHABETA_PLAYER;
    }
    
     // To compute the best move for the current state
    private Position AlphaBetaSearch(TicTacToeBoard state) {
        Utility result;
        
        if (state.getTurn() == TicTacToeBoard.PLAYER_X){
            result = maxValue(state, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            System.out.printf("Best Position for %s's: %d,%d by alfa-beta\n",
               TicTacToeBoard.X, result.position.row, result.position.col);
        }
        else {
            result = minValue(state, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            System.out.printf("Best Position for %s's: %d,%d by alfa-beta\n", 
                 TicTacToeBoard.O, result.position.row, result.position.col);       
        }
        
        return result.position;
    }
    
    
    /* Method to compute best move for player X (maximizing player)
       Recursively calls minTurn()
       Returns a ScorePosition object as utility
    */
    private Utility maxValue(TicTacToeBoard state, int depth, int alpha, int beta) {
        if (isTerminalState(state)) 
            return new Utility(score(state, depth), null);
        
        Utility max = new Utility(Integer.MIN_VALUE, new Position());
        
        for(Position pos : getAvailablePositions(state)){
            if (state.getState(pos.row, pos.col).equals(TicTacToeBoard.BLANK)) {
                try {
                    state.setState(pos.row, pos.col, TicTacToeBoard.X);
                } catch (Exception ex) {
                    System.out.println("Error in max Turn: " + ex.toString());
                }
                    
                Utility currentUtility = minValue(state, depth + 1, alpha, beta);
                   
                if (currentUtility.value > max.value) {
                    max.value = currentUtility.value;
                    max.position.row = pos.row;
                    max.position.col = pos.col;
                }
            
                state.cleanSquare(pos.row, pos.col);
            
                if(max.value > alpha) alpha = max.value;
                if(beta <= alpha) break;
                    
                state.cleanSquare(pos.row, pos.col);
             }
        }
        
        return max;
    }
    
    
    /* Method to compute best move for the minimizing player
       Recursively calls maxTurn()
       Returns a ScorePosition objects as utility
    */
    private Utility minValue(TicTacToeBoard state, int depth, int alpha, int beta) {
        if (isTerminalState(state))
            return new Utility(score(state, depth), null);
        
        Utility min = new Utility(Integer.MAX_VALUE, new Position());
        
        for(Position pos : getAvailablePositions(state)){
            try {
                state.setState(pos.row, pos.col, TicTacToeBoard.O);
            } catch (Exception ex) {
                System.out.println("Error in minTurn: " + ex.toString());
            }
                    
            Utility currentUtility = maxValue(state, depth + 1, alpha, beta);
                    
            if (currentUtility.value < min.value) {
                min.value = currentUtility.value;
                min.position.row = pos.row;
                min.position.col = pos.col;
            }
            state.cleanSquare(pos.row, pos.col);
            
            if(min.value < beta) beta = min.value;
            if(beta <= alpha) break;
                    
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
    
    
    /*Returns a collection with the available Positions, 
    works like the possible states*/
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
    
    
    
    /* A class that represents a pair Score Position (could be a Map)
       This works like a utility value
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
