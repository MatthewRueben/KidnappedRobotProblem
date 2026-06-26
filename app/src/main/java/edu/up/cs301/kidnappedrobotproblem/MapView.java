package edu.up.cs301.kidnappedrobotproblem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MapView extends SurfaceView {

    private KRP_GameState gameState;


    public MapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false); // So onDraw() gets called.

        setBackgroundColor(Color.WHITE);
    }

    protected void receiveGameState(KRP_GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        if (this.gameState != null) {
            float mapLeft = 200f;
            float mapTop = 300f;
            float mapCellSize = 100f;

            Robot theRobot = this.gameState.getRobot();
            float robotLeft = mapLeft + theRobot.getPoseX() * mapCellSize;
            float robotTop = mapTop + theRobot.getPoseY() * mapCellSize;
            float robotRight = robotLeft + mapCellSize;
            float robotBottom = robotTop + mapCellSize;
            RectF robotBounds = new RectF(robotLeft, robotTop, robotRight, robotBottom);
            this.gameState.getRobot().drawIn(canvas, robotBounds);

        }

        Log.d("KRP", "Drew.");

    }
}
