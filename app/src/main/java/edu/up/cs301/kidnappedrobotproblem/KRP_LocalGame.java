package edu.up.cs301.kidnappedrobotproblem;

import android.util.Log;

import java.util.Random;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Controls the play of the game.
 *
 * @author Matthew Rueben
 */
public class KRP_LocalGame extends LocalGame {

    private KRP_GameState gameState;
    private Random random = new Random();

    /**
     * This ctor creates a new game state
     */
    public KRP_LocalGame() {
        try {
            this.gameState = new KRP_GameState();
        } catch (OutsideOfMapBoundsException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == this.gameState.getWhoseTurnID();
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof MoveAction) {
            MoveAction krpMoveAction = (MoveAction) action;

            MoveAction.Movement movement = krpMoveAction.getMovement();

            boolean isValidMove;
            String historyEntry;
            try {
                this.gameState.moveRobot(movement);
                isValidMove = true;
                historyEntry = movement.toString();
            } catch (UnenterableCellException e) {
                isValidMove = true; // Because the history is part of the KRP_GameState, I want it to be sent to the other players.
                                    // This player will lose their turn.
                historyEntry = "Cannot go there! It's an obstacle.";
            } catch (OutsideOfMapBoundsException e) {
                isValidMove = true; // Because the history is part of the KRP_GameState, I want it to be sent to the other players.
                                    // This player will lose their turn.
                historyEntry = "Cannot go there! It's off the map.";
            }
            this.gameState.appendToHistory(historyEntry);

            return isValidMove;
        }
        else {
            return false;
        }

    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        KRP_GameState copyOfKRPGameState = new KRP_GameState(this.gameState);
        p.sendInfo(copyOfKRPGameState);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        return null;
    }

}
