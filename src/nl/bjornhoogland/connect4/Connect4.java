package nl.bjornhoogland.connect4;

import nl.bjornhoogland.connect4.gamelogic.GameController;
import nl.bjornhoogland.connect4.gamelogic.Position;
import android.app.Activity;
import android.os.Bundle;

public class Connect4 extends Activity {
	PlayBoard pb;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        pb = (PlayBoard)findViewById(R.id.playboard);
        
        pb.setFocusable(true);
    }
}