package nl.bjornhoogland.connect4.gamelogic;

import java.util.ArrayList;

import android.util.Log;

public class Map {
	private ArrayList<int[]> indices;
	private int sizeY = 8;
	private int sizeX = 8;
	private int numToConnect = 4;
	
	public Map(){
		indices = new ArrayList<int[]>();
		
		//int[] a = new int[numToConnect*4 + 1];
		//a[0] = -1;
		for(int i = 0; i < sizeX * sizeY;i++)
			indices.add(new int[numToConnect*4 + 1]);
		
		int winIndex = 1;
		
		/* Fill in the horizontal win positions */
	    for (int i=0; i<sizeY; i++){
	        for (int j=0; j<sizeX-numToConnect+1; j++) {
	            for (int k=0; k<numToConnect; k++) {
	                addIndex(getSquare(j+k,i), winIndex);
	            }
	            winIndex++;
	        }
	    }
	    
	    /* Fill in the vertical win positions */
	    for (int i=0; i<sizeX; i++){
	        for (int j=0; j<sizeY-numToConnect+1; j++) {
	            for (int k=0; k<numToConnect; k++) {
	                addIndex(getSquare(i,j+k), winIndex);
	            }
	            winIndex++;
	        }
	    }

	    /* Fill in the forward diagonal win positions */
	    for (int i=0; i<sizeY-numToConnect+1; i++)
	        for (int j=0; j<sizeX-numToConnect+1; j++) {
	            for (int k=0; k<numToConnect; k++) {
	                addIndex(getSquare(j+k,i+k), winIndex);
	            }
	            winIndex++;
	        }

	    /* Fill in the backward diagonal win positions */
	    for (int i=0; i<sizeY-numToConnect+1; i++)
	        for (int j=sizeX-1; j>=numToConnect-1; j--) {
	            for (int k=0; k<numToConnect; k++) {
	                addIndex(getSquare(j-k,i+k), winIndex);
	            }
	            winIndex++;
	        }
	}
	
	/** Return index in indices[] vector corresponding to (x,y). */
    public final static int getSquare(int x, int y) {
    	return y * 8 + x;
    }
    /** Return x position (file) corresponding to a square. */
    public final static int getX(int square) {
        return square & 7;
    }
    /** Return y position (rank) corresponding to a square. */
    public final static int getY(int square) {
        return square >> 3;
    }
    
    /** Return indices occupying a square. */
    public final int[] getIndices(int square) {
        return indices.get(square);
    }
    /** Add an index value to a square. */
    public final void addIndex(int square, int index) {
    	// Update map
    	int teller = 0;
        for (int x = 0; x < indices.get(square).length; x++){
        	if(indices.get(square)[x] != 0){
        		teller++;
        	}
        }
    	indices.get(square)[teller] = index;
    }
    
    /** Useful for debugging. */
    public final String toString() {
        return TextIO.asciiMap(this);
    }

}
