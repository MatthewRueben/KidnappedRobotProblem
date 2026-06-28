package edu.up.cs301.kidnappedrobotproblem;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

public class MoveAction extends GameAction {

    public enum Movement {
        GO_FORWARD,
        TURN_LEFT,
        TURN_RIGHT;
    }

    private final Movement movement;

    public MoveAction(GamePlayer player, Movement movement) {
        super(player);

        this.movement = movement;
    }

    public Movement getMovement() { return this.movement; }

}
