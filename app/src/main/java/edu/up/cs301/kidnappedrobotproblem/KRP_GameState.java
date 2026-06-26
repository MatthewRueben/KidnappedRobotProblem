package edu.up.cs301.kidnappedrobotproblem;

import edu.up.cs301.game.infoMsg.GameState;

public class KRP_GameState extends GameState {


    private int whoseTurnID;
    private final Robot robot;

    private String history;

    public KRP_GameState() {
        this.whoseTurnID = 0;
        this.robot = new Robot();
        this.history = "";
    }

    /* Copy constructor. */
    public KRP_GameState(KRP_GameState other) {
        this.whoseTurnID = other.whoseTurnID;
        this.robot = new Robot(other.robot);
        this.history = other.history;
    }

    /* Getters */
    public int getWhoseTurnID() { return this.whoseTurnID; }
    public Robot getRobot() { return this.robot; }
    public String getHistory() { return this.history; }

    /* Setters. */
    public void setWhoseTurnID(int newWhoseTurnID) { this.whoseTurnID = newWhoseTurnID; }

    public void moveRobotForward() { this.robot.moveForward(); }
    public void turnRobotLeft() throws Exception { this.robot.turnLeft(); }
    public void turnRobotRight() throws Exception { this.robot.turnRight(); }


    public void appendToHistory(String newHistory) {
        this.history += newHistory;
        this.history += "\n";
    }
}
