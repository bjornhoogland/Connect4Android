package nl.bjornhoogland.connect4;

import android.app.Activity;
import android.os.Bundle;

public class Game extends Activity{
	PlayBoard pb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		pb = (PlayBoard)findViewById(R.id.playboard);
        
        pb.setFocusable(true);
	}
}
