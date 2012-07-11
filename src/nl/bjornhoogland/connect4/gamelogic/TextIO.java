package nl.bjornhoogland.connect4.gamelogic;

public class TextIO {
	/**
     * Create an ascii representation of a position.
     */
    public static final String asciiBoard(Position pos) {
        StringBuilder ret = new StringBuilder(400);
        String nl = String.format("%n");
        ret.append("    +----+----+----+----+----+----+----+----+"); ret.append(nl);
        for (int y = 7; y >= 0; y--) {
            ret.append("    |");
            for (int x = 0; x < 8; x++) {
                ret.append(' ');
                int p = pos.getPiece(Position.getSquare(x, y));
                if (p == Piece.EMPTY) {
                    ret.append(".. |");
                } else {
                    ret.append(Piece.isYellow(p) ? ' ' : '*');
                    String pieceName = pieceToChar(p);
                    if (pieceName.length() == 0)
                        pieceName = "P";
                    ret.append(pieceName);
                    ret.append(" |");
                }
            }
            ret.append(nl);
            ret.append("    +----+----+----+----+----+----+----+----+");
            ret.append(nl);
        }
        return ret.toString();
    }
    
    private final static String pieceToChar(int p) {
        switch (p) {
            case Piece.YELLOW:  return "Y";
            case Piece.RED:   return "R";
        }
        return "";
    }
    
    /**
     * Create an ascii representation of a map.
     */
    public static final String asciiMap(Map map) {
        StringBuilder ret = new StringBuilder(400);
        String nl = String.format("%n");
        ret.append("    +----+----+----+----+----+----+----+----+"); ret.append(nl);
        for (int y = 7; y >= 0; y--) {
            ret.append("    |");
            for (int x = 0; x < 8; x++) {
                ret.append(' ');
                int[] m = map.getIndices(Map.getSquare(x, y));
                for(int i = 0; i < m.length;i++){
                	ret.append(m[i] + ",");
                }
                ret.append(" |");
            }
            ret.append(nl);
            ret.append("    +----+----+----+----+----+----+----+----+");
            ret.append(nl);
        }
        return ret.toString();
    }
}
