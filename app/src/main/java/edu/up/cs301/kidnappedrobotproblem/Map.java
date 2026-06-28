package edu.up.cs301.kidnappedrobotproblem;

import android.util.Log;

public class Map {
    public class Cell {

    }

    public Map () {

    }

    public Map (Map other) {

    }

    public class Location {
        public int col;
        public int row;

        public Location(int col, int row) {
            this.col = col;
            this.row = row;
        }

        public Location(Location other) {
            this.col = other.col;
            this.row = other.row;
        }

        // TODO: add bounds checking by looking at the enclosing Map.
    }

    public enum Direction {
        UP      ( 0,-1, 270f),
        DOWN    ( 0, 1, 90f),
        LEFT    (-1, 0, 180f),
        RIGHT   ( 1, 0, 0f);

        public final int dCol;
        public final int dRow;
        public final float angle;
        Direction(int dCol, int dRow, float angle) {
            this.dCol = dCol;
            this.dRow = dRow;
            this.angle = angle;
        }

        public Direction getLeftTurnResult() {
            switch (this) {
                case UP:    return LEFT;
                case LEFT:  return DOWN;
                case DOWN:  return RIGHT;
                case RIGHT: return UP;
                default:    return null; // E.g., if (this == null).
            }
        }

        public Direction getRightTurnResult() {
            switch (this) {
                case UP:    return RIGHT;
                case RIGHT: return DOWN;
                case DOWN:  return LEFT;
                case LEFT:  return UP;
                default:    return null; // E.g., if (this == null).
            }
        }
    }

    public class Pose {
        public Location location;
        public Direction heading;

        public Pose(Location location, Direction heading) {
            this.location = location;
            this.heading = heading;
        }

        public Pose(Pose other) {
            this.location = new Location(other.location);
            this.heading = other.heading; // Because it's a constant.
        }

        public void move(MoveAction.Movement movement) {
            switch (movement) {
                case GO_FORWARD:
                    this.location.col += this.heading.dCol;
                    this.location.row += this.heading.dRow;
                    break;
                case TURN_LEFT:
                    Direction headingTurnedLeft = this.heading.getLeftTurnResult();
                    this.heading = headingTurnedLeft;
                    break;
                case TURN_RIGHT:
                    Direction headingTurnedRight = this.heading.getRightTurnResult();
                    this.heading = headingTurnedRight;
                    break;
            }
        }
    }
}
