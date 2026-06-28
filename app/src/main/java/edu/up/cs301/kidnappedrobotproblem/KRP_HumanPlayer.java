package edu.up.cs301.kidnappedrobotproblem;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * A GUI for a human to play KRP.
 *
 * @author Matthew Rueben
 */
public class KRP_HumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */

    // These variables will reference widgets that will be modified during play
    private TextView    movementAndSensorReadoutView    = null;
    private Button      turnLeftButton                  = null;
    private Button      forwardButton                   = null;
    private Button      turnRightButton                 = null;

    private MapView mapView                         = null;

    // the android activity that we are running
    private GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public KRP_HumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if (info instanceof KRP_GameState) {
            KRP_GameState copyOfGameState; // Already deep copied in KRP_LocalGame.sendUpdatedStateTo(GamePlayer).
            copyOfGameState = (KRP_GameState) info;

            String history = copyOfGameState.getHistory();
            this.movementAndSensorReadoutView.setText(history);

            this.mapView.receiveGameState(copyOfGameState);
            this.mapView.invalidate();
        }
        else { /* E.g., NotYourTurnInfo */
            this.flash(Color.RED, 1000);
            return;
        }

    }//receiveInfo

    /**
     * this method gets called when the user clicks a button. It
     * creates a new KRP_...Action and sends it to the game.
     *
     * @param clickedView
     * 		the View (e.g., Button) that was clicked
     */
    public void onClick(View clickedView) {
        int idOfClickedView = clickedView.getId();
        if (idOfClickedView == R.id.turnLeftButton) {
            GameAction action;
            action = new MoveAction(this, MoveAction.Movement.TURN_LEFT);
            this.game.sendAction(action);
        }
        else if (idOfClickedView == R.id.forwardButton) {
            GameAction action;
            action = new MoveAction(this, MoveAction.Movement.GO_FORWARD);
            this.game.sendAction(action);
        }
        else if (idOfClickedView == R.id.turnRightButton) {
            GameAction action;
            action = new MoveAction(this, MoveAction.Movement.TURN_RIGHT);
            this.game.sendAction(action);
        }
    }// onClick

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.krp_layout);

        //Initialize the widget reference member variables
        this.movementAndSensorReadoutView   = (TextView)activity.findViewById(R.id.movementAndSensorReadout);
        this.turnLeftButton                 = (Button)activity.findViewById(R.id.turnLeftButton);
        this.forwardButton                  = (Button)activity.findViewById(R.id.forwardButton);
        this.turnRightButton                = (Button)activity.findViewById(R.id.turnRightButton);
        this.mapView                        = (MapView)activity.findViewById(R.id.mapView);

        //Listen for button presses
        turnLeftButton.setOnClickListener(this);
        forwardButton.setOnClickListener(this);
        turnRightButton.setOnClickListener(this);

    }//setAsGui

}
