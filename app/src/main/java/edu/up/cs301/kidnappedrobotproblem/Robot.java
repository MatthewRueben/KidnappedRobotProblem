package edu.up.cs301.kidnappedrobotproblem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Robot implements Drawable {


    public enum Direction {
        UP      ( 0,-1, 270f),
        DOWN    ( 0, 1, 90f),
        LEFT    (-1, 0, 180f),
        RIGHT   ( 1, 0, 0f);

        public final int dRow;
        public final int dCol;
        public final float angle;
        Direction(int dRow, int dCol, float angle) {
            this.dRow = dRow;
            this.dCol = dCol;
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

    private int poseX;
    private int poseY;
    private Direction poseHeading;

    public Robot() {
        this.poseX = 0;
        this.poseY = 0;
        this.poseHeading = Direction.UP;
    }

    public Robot(Robot other) {
        this.poseX = other.poseX;
        this.poseY = other.poseY;
        this.poseHeading = other.poseHeading;
    }


    /* Getters */
    public float getPoseX() { return this.poseX; }
    public float getPoseY() { return this.poseY; }
    public Direction getPoseHeading() { return this.poseHeading; }

    /* Setters */
    public void moveForward() {
        this.poseX += this.poseHeading.dRow;
        this.poseY += this.poseHeading.dCol;
    }

    public void turnLeft() {
        Direction headingTurnedLeft = this.poseHeading.getLeftTurnResult();
        this.poseHeading = headingTurnedLeft;
    }

    public void turnRight() {
        Direction headingTurnedRight = this.poseHeading.getRightTurnResult();
        this.poseHeading = headingTurnedRight;
    }


    @Override
    public void drawIn(Canvas canvas, RectF bounds) {
        Paint edgePaint = new Paint();
        edgePaint.setColor(R.color.robotEdge);
        edgePaint.setStyle(Paint.Style.STROKE);
        edgePaint.setStrokeWidth(5f);

        float centerX = (bounds.left + bounds.right) / 2;
        float centerY = (bounds.top + bounds.bottom) / 2;
        canvas.drawCircle(centerX, centerY, 30f, edgePaint);

        float arcSweepDegrees = 40f;
        canvas.drawArc(centerX - 20f, centerY - 20f, centerX + 20f, centerY + 20f, this.poseHeading.angle - (arcSweepDegrees / 2), arcSweepDegrees, false, edgePaint);
    }


}
