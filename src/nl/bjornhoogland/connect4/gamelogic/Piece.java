package nl.bjornhoogland.connect4.gamelogic;

public class Piece {
	public static final int EMPTY = 0;
	
	public static final int YELLOW = 1;
	
	public static final int RED = 2;
	
	public static final int nPieceTypes = 3;
	
	/**
     * Return true if p is a yellow piece, false otherwise.
     * Note that if p is EMPTY, an unspecified value is returned.
     */
    public static boolean isYellow(int pType) {
        return pType < RED;
    }
    public static int makeYellow(int pType) {
        return pType < RED ? pType : pType - (RED - YELLOW);
    }
    public static int makeRed(int pType) {
        return ((pType >= YELLOW) && (pType <= YELLOW)) ? pType + (RED - YELLOW) : pType;
    }
    public static int swapColor(int pType) {
        if (pType == EMPTY)
            return EMPTY;
        return isYellow(pType) ? pType + (RED - YELLOW) : pType - (RED - YELLOW);
    }
}
