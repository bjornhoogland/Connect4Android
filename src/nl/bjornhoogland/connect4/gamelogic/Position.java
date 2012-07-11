package nl.bjornhoogland.connect4.gamelogic;

public class Position {
	private int[] squares;
	
	public boolean yellowMove;
	
	/** Initialize board to empty position. */
	public Position() {
		squares = new int[64];
		for (int i = 0; i < 64; i++)
			squares[i] = Piece.EMPTY;
		yellowMove = true;
	}
	
	public Position(Position other) {
		squares = new int[64];
		System.arraycopy(other.squares, 0, squares, 0, 64);
		yellowMove = other.yellowMove;
	}
	
	public final void setYellowMove(boolean yellowMove) {
		if (yellowMove != this.yellowMove) {
			this.yellowMove = yellowMove;
		}
	}
	/** Return index in squares[] vector corresponding to (x,y). */
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
    
    /** Return piece occupying a square. */
    public final int getPiece(int square) {
        return squares[square];
    }
    /** Set a square to a piece value. */
    public final void setPiece(int square, int piece) {
        // Update board
        squares[square] = piece;
    }
    
    /**
     * Count number of pieces of a certain type.
     */
    public final int nPieces(int pType) {
        int ret = 0;
        for (int sq = 0; sq < 64; sq++) {
            if (squares[sq] == pType)
                ret++;
        }
        return ret;
    }

    /** Apply a move to the current position. */
    public final void makeMove(int move) {
        boolean wtm = yellowMove;

        int p = Piece.RED;
        if (wtm)
        	p = Piece.YELLOW;

        // Perform move
        setPiece(move, p);
        setYellowMove(!wtm);
    }

    public final void unMakeMove(int move) {
        setYellowMove(!yellowMove);
        setPiece(move, Piece.EMPTY);
    }
    
    /** Useful for debugging. */
    public final String toString() {
        return TextIO.asciiBoard(this);
    }
}
