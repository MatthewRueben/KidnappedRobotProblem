package edu.up.cs301.kidnappedrobotproblem;

import edu.up.cs301.game.infoMsg.GameState;

public class KRP_GameState extends GameState {

    public enum Direction {
        UP      ( 0,-1, 270f),
        DOWN    ( 0, 1, 90f),
        LEFT    (-1, 0, 180f),
        RIGHT   ( 1, 0, 0f);

        public final int x;
        public final int y;
        public final float angle;
        Direction(int x, int y, float angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
        }

        public Direction getLeftTurnResult() throws Exception {
            switch (this) {
                case UP:    return LEFT;
                case LEFT:  return DOWN;
                case DOWN:  return RIGHT;
                case RIGHT: return UP;
            }

            throw new Exception("Direction was null or something.");
        }

        public Direction getRightTurnResult() throws Exception {
            switch (this) {
                case UP:    return RIGHT;
                case RIGHT:  return DOWN;
                case DOWN:  return LEFT;
                case LEFT: return UP;
            }

            throw new Exception("Direction was null or something.");
        }
    }

    private int whoseTurnID;
    private float botPoseX;
    private float botPoseY;
    private Direction botPoseHeading;
    private String history;

    public KRP_GameState() {
        this.whoseTurnID = 0;
        this.botPoseX = 300f;
        this.botPoseY = 500f;
        this.botPoseHeading = Direction.UP;
        this.history = "";
    }

    /* Copy constructor. */
    public KRP_GameState(KRP_GameState other) {
        this.whoseTurnID = other.whoseTurnID;
        this.botPoseX = other.botPoseX;
        this.botPoseY = other.botPoseY;
        this.botPoseHeading = other.botPoseHeading;
        this.history = other.history;
    }

    /* Getters */
    public int getWhoseTurnID() { return this.whoseTurnID; }
    public float getBotPoseX() { return this.botPoseX; }
    public float getBotPoseY() { return this.botPoseY; }
    public Direction getBotPoseHeading() { return this.botPoseHeading; }
    public String getHistory() { return this.history; }

    /* Setters. */
    public void setWhoseTurnID(int newWhoseTurnID) { this.whoseTurnID = newWhoseTurnID; }

    public void moveBotForward(float stepSize) {
        this.botPoseX += this.botPoseHeading.x * stepSize;
        this.botPoseY += this.botPoseHeading.y * stepSize;
    }

    public void turnBotLeft() throws Exception {
        Direction headingTurnedLeft = this.botPoseHeading.getLeftTurnResult();
        this.botPoseHeading = headingTurnedLeft;
    }

    public void turnBotRight() throws Exception {
        Direction headingTurnedRight = this.botPoseHeading.getRightTurnResult();
        this.botPoseHeading = headingTurnedRight;
    }

    public void appendToHistory(String newHistory) {
        this.history += newHistory;
        this.history += "\n";
    }
}
