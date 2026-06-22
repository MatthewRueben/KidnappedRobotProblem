package edu.up.cs301.kidnappedrobotproblem;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

public class MoveAction extends GameAction {

    public enum Choice {
        GO_FORWARD,
        TURN_LEFT,
        TURN_RIGHT;
    }

    private final Choice choice;

    public MoveAction(GamePlayer player, Choice choice) {
        super(player);

        this.choice = choice;
    }

    public Choice getChoice() { return this.choice; }

}
