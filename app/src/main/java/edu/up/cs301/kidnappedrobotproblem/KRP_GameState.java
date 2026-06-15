package edu.up.cs301.kidnappedrobotproblem;

import edu.up.cs301.game.infoMsg.GameState;

public class KRP_GameState extends GameState {

    private int whoseTurnID;
    private float botPoseX;
    private String history;

    public KRP_GameState() {
        this.whoseTurnID = 0;
        this.botPoseX = 100f;
        this.history = "";
    }

    /* Copy constructor. */
    public KRP_GameState(KRP_GameState other) {
        this.whoseTurnID = other.whoseTurnID;
        this.botPoseX = other.botPoseX;
        this.history = other.history;
    }

    /* Getters */
    public int getWhoseTurnID() { return this.whoseTurnID; }
    public float getBotPoseX() { return this.botPoseX; }
    public String getHistory() { return this.history; }

    /* Setters. */
    public void setWhoseTurnID(int newWhoseTurnID) { this.whoseTurnID = newWhoseTurnID; }
    public void setBotPoseX(float newRobotPosition) { this.botPoseX = newRobotPosition; }

    public void appendToHistory(String newHistory) {
        this.history += newHistory;
        this.history += "\n";
    }
}
