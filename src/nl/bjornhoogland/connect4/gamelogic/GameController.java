package nl.bjornhoogland.connect4.gamelogic;

import nl.bjornhoogland.connect4.PlayBoard;
import android.app.Activity;
import android.util.Log;

public class GameController {
	private PlayBoard gui;
	private Map map = new Map();
	private int[] yellowScore = new int[131];
	private int[] redScore = new int[131];
	
	/** Constructor */
	public GameController(PlayBoard gui) {
		this.gui = gui;
		//Log.d("GameController", "map: " + map.toString());
	}
	
	public int dropPiece(Position pos, int column) {
		int y = 7;
		while(pos.getPiece(Position.getSquare(column,y)) != Piece.EMPTY && --y >= 0)
			;
		
		return y;
	}
	
	public boolean updateScore(Position pos, int sq){
		for (int i = 0; i < map.getIndices(sq).length; i++){
			int winIndex = map.getIndices(sq)[i];
        	if(winIndex != 0){
        		if(!pos.yellowMove){
        			yellowScore[winIndex]++;
        			redScore[winIndex] = 0;
        		} else {
        			yellowScore[winIndex] = 0;
        			redScore[winIndex]++;
        		}
        		if(yellowScore[winIndex] == 4){
        			Log.d("Connect4", "Yellow wins");
        			return true;
        		}
        		if(redScore[winIndex] == 4){
        			Log.d("Connect4", "Red wins");
        			return true;
        		}
        	}
        }
		return false;
	}
}
