package edu.up.cs301.kidnappedrobotproblem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Robot implements Drawable {

    private final Map.Pose pose;

    public Robot(Map.Pose startingPose) {
        this.pose = startingPose;
    }

    /* Getters */
    public Map.Pose getPose() { return this.pose; }

    /* Setters */
    public void move(MoveAction.Movement movement) throws UnenterableCellException, OutsideOfMapBoundsException {
        this.pose.move(movement);
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
        canvas.drawArc(centerX - 20f, centerY - 20f, centerX + 20f, centerY + 20f, this.pose.heading.angle - (arcSweepDegrees / 2), arcSweepDegrees, false, edgePaint);
    }


}
