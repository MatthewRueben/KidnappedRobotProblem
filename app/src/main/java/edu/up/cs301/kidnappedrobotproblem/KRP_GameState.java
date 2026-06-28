package edu.up.cs301.kidnappedrobotproblem;

import edu.up.cs301.game.infoMsg.GameState;

public class KRP_GameState extends GameState {


    private int whoseTurnID;
    private final Map map;
    private final Robot robot;

    private String history;

    public KRP_GameState() {
        this.whoseTurnID = 0;
        this.map = new Map();

        Map.Location startingLocation = this.map.new Location(0, 0);
        Map.Direction startingHeading = Map.Direction.UP;
        Map.Pose startingPose = this.map.new Pose(startingLocation, startingHeading);
        this.robot = new Robot(startingPose);

        this.history = "";
    }

    /* Copy constructor. */
    public KRP_GameState(KRP_GameState other) {
        this.whoseTurnID = other.whoseTurnID;
        this.map = new Map(other.map);

        Map.Pose otherRobotPose = this.map.new Pose(other.robot.getPose());
        this.robot = new Robot(otherRobotPose);
        this.history = other.history;
    }

    /* Getters */
    public int getWhoseTurnID() { return this.whoseTurnID; }
    public Map getMap() { return this.map; }
    public Robot getRobot() { return this.robot; }
    public String getHistory() { return this.history; }

    /* Setters. */
    public void setWhoseTurnID(int newWhoseTurnID) { this.whoseTurnID = newWhoseTurnID; }
    public void moveRobot(MoveAction.Movement movement)  { this.robot.move(movement); }
    public void appendToHistory(String newHistory) {
        this.history += newHistory;
        this.history += "\n";
    }
}