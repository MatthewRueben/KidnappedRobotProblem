package edu.up.cs301.kidnappedrobotproblem;

import android.util.Log;

public class Map {
    public enum Cell {
        CHARGER (true, true),
        EMPTY   (true, false),
        BLOCKED (false, false);

        private final boolean canEnter;
        private final boolean isCharger;
        Cell(boolean canEnter, boolean isCharger) {
            this.canEnter = canEnter;
            this.isCharger = isCharger;
        }
    }

    private final Cell[][] map;

    public Map() {
        int numCols = 3;
        int numRows = 1;
        this.map = new Cell[numCols][numRows];
        this.map[0][0] = Cell.EMPTY;
        this.map[1][0] = Cell.CHARGER;
        this.map[2][0] = Cell.BLOCKED;
//        for (int colIndex = 0; colIndex < numCols; colIndex++) {
//            for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
//                this.map[colIndex][rowIndex] = Cell.EMPTY;
//            }
//        }
    }

    public Map(Map other) {
        int numCols = other.map.length;
        int numRows = other.map[0].length;
        this.map = new Cell[numCols][numRows];
        for (int colIndex = 0; colIndex < numCols; colIndex++) {
            for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
                this.map[colIndex][rowIndex] = other.map[colIndex][rowIndex];
            }
        }
    }

    /* Getters */
    private Cell getCellAt(Location location) {
        Cell theCell = this.map[location.colIndex][location.rowIndex];
        return theCell;
    }
    public boolean canEnterAt(Location location) {
        Cell theCell = this.getCellAt(location);
        boolean canEnter = theCell.canEnter;
        return canEnter;
    }
    public boolean isChargerAt(Location location) {
        Cell theCell = this.getCellAt(location);
        boolean isCharger = theCell.isCharger;
        return isCharger;
    }

    /* Immutable class. */
    public final class Location {
        private final int colIndex;
        private final int rowIndex;

        public Location(int colIndex, int rowIndex) throws OutsideOfMapBoundsException {
            boolean indicesAreOnMap = this.checkIfIndicesAreOnMap(colIndex, rowIndex);
            if (!indicesAreOnMap) {
                throw new OutsideOfMapBoundsException();
            }

            this.colIndex = colIndex;
            this.rowIndex = rowIndex;
        }

        private boolean checkIfIndicesAreOnMap(int colIndex, int rowIndex) {
            if (    colIndex >= 0 &&
                    rowIndex >= 0 &&
                    colIndex < Map.this.map.length &&
                    rowIndex < Map.this.map[colIndex].length) {
                return true;
            }
            else {
                return false;
            }
        }

        /* Assumes the other Location has valid indices. */
        public Location(Location other) {
            this.colIndex = other.colIndex;
            this.rowIndex = other.rowIndex;
        }

        public int getColIndex() { return this.colIndex; }
        public int getRowIndex() { return this.rowIndex; }
    }

    public enum Direction {
        UP      ( 0,-1, 270f),
        DOWN    ( 0, 1, 90f),
        LEFT    (-1, 0, 180f),
        RIGHT   ( 1, 0, 0f);

        public final int dColIndex;
        public final int dRowIndex;
        public final float angle;
        Direction(int dColIndex, int dRowIndex, float angle) {
            this.dColIndex = dColIndex;
            this.dRowIndex = dRowIndex;
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
            this.heading = other.heading; // Deep copy not needed because it's a constant.
        }

        public void move(MoveAction.Movement movement) throws UnenterableCellException, OutsideOfMapBoundsException {
            switch (movement) {
                case GO_FORWARD:
                    int newColIndex = this.location.getColIndex() + this.heading.dColIndex;
                    int newRowIndex = this.location.getRowIndex() + this.heading.dRowIndex;
                    Location newLocation = new Location(newColIndex, newRowIndex);

                    if (!Map.this.canEnterAt(newLocation)) {
                        throw new UnenterableCellException();
                    }

                    this.location = newLocation;
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
