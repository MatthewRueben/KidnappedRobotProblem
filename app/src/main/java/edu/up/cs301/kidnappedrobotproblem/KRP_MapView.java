package edu.up.cs301.kidnappedrobotproblem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class KRP_MapView extends SurfaceView {

    private KRP_GameState gameState;

    Paint robotEdgePaint = new Paint();

    public KRP_MapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false); // So onDraw() gets called.

        this.robotEdgePaint.setColor(Color.GRAY);
        this.robotEdgePaint.setStyle(Paint.Style.STROKE);
        this.robotEdgePaint.setStrokeWidth(5f);

        setBackgroundColor(Color.WHITE);
    }

    protected void receiveGameState(KRP_GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        if (this.gameState != null) {
            float botPoseX = this.gameState.getBotPoseX();
            float botPoseY = this.gameState.getBotPoseY();
            KRP_GameState.Direction botPoseHeading = this.gameState.getBotPoseHeading();

            canvas.drawCircle(botPoseX, botPoseY, 30f, this.robotEdgePaint);

            float arcSweepDegrees = 40f;
            canvas.drawArc(botPoseX - 20f, botPoseY - 20f, botPoseX + 20f, botPoseY + 20f, botPoseHeading.angle - (arcSweepDegrees / 2), arcSweepDegrees, false, this.robotEdgePaint);
        }

        Log.d("KRP", "Drew.");

    }
}
