package nl.bjornhoogland.connect4;

import nl.bjornhoogland.connect4.gamelogic.GameController;
import nl.bjornhoogland.connect4.gamelogic.Piece;
import nl.bjornhoogland.connect4.gamelogic.Position;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class PlayBoard extends View{
	
	private static final String TAG = "Connect4";
	
	public Position pos;
	private static GameController ctrl = null;
	
	private int sqSize;		// size of this view
	private float width;	// width of one tile
	private float height;	// height of one tile
	private int selX;		// X index of selection
	private final Rect selRect = new Rect();
	private boolean redWins = false;
	private boolean yellowWins = false;

	public PlayBoard(Context context, AttributeSet attrs) {
		super(context, attrs);
		pos = new Position();
		ctrl = new GameController(this);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		sqSize = Math.min(w, h);
		width = sqSize / 8f;
		height = sqSize / 8f;
		getRect(selX, selRect);
		Log.d(TAG, "onSizeChanged: width " + width + ", height" + height);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	private void getRect(int x, Rect rect) {
		rect.set((int) (x * width), 0, (int) (x * width + width), sqSize);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// Draw the background...
		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.board_backgound));
		canvas.drawRect(0, 0, sqSize, sqSize, background);
		
		// Draw the board...
		// Define colors for the grid lines
		Paint dark = new Paint();
		dark.setColor(getResources().getColor(R.color.board_dark));
		
		Paint hilite = new Paint();
		hilite.setColor(getResources().getColor(R.color.board_hilite));

		Paint light = new Paint();
		light.setColor(getResources().getColor(R.color.board_light));
		
		// Draw the major grid lines
		for(int i = 1; i < 8; i++) {
			canvas.drawLine(i * width, 0, i * width, sqSize, dark);
			canvas.drawLine(i * width + 1, 0, i * width + 1, sqSize, hilite);
		}
		
		// Draw the chips...
		Paint red = new Paint(Paint.ANTI_ALIAS_FLAG);
		red.setColor(getResources().getColor(R.color.red));
		Paint yellow = new Paint(Paint.ANTI_ALIAS_FLAG);
		yellow.setColor(getResources().getColor(R.color.yellow));
		Paint empty = new Paint(Paint.ANTI_ALIAS_FLAG);
		empty.setColor(getResources().getColor(R.color.blue));
		Path yellowChips = new Path();
		Path redChips = new Path();
		Path emptyChips = new Path();
		
		float radius = height * 0.4f;
		float x = width / 2 + 1;
		float y = height / 2 + 1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int sq = Position.getSquare(i, j);
				int p = pos.getPiece(sq);
				if (p == Piece.YELLOW) {
					yellowChips.addCircle(i * width + x, j * height + y, radius, Direction.CW);
				} else if (p == Piece.RED) {
					redChips.addCircle(i * width + x, j * height + y, radius, Direction.CW);
				} else {
					emptyChips.addCircle(i * width + x, j * height + y, radius, Direction.CW);
				}
			}
		}
		canvas.drawPath(yellowChips, yellow);
		canvas.drawPath(redChips, red);
		canvas.drawPath(emptyChips, empty);
		
		// Draw the selection...
		Log.d(TAG, "selRect=" + selRect);
		Paint selected = new Paint();
		selected.setColor(getResources().getColor(R.color.board_selected));
		canvas.drawRect(selRect, selected);
		if(redWins){
			red.setTextSize(44);
			canvas.drawText("Rood wint!", 20, 40, red);
		}
		if(yellowWins){
			yellow.setTextSize(44);
			canvas.drawText("Geel wint!", 20, 40, yellow);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(TAG, "onKeyDown: keycode=" + keyCode + ", event=" + event);
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			select(selX - 1);
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			select(selX + 1);
			break;
		case KeyEvent.KEYCODE_ENTER:
		case KeyEvent.KEYCODE_DPAD_CENTER:
			select(selX);
			setSelectedColumn();
			break;
		default:
			return super.onKeyDown(keyCode, event);
		}
		return true;
	}
	
	private void select(int x) {
		invalidate(selRect);
		selX = Math.min(Math.max(x,  0), 7);
		getRect(selX, selRect);
		invalidate(selRect);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return super.onTouchEvent(event);
		
		select((int) (event.getX() / width));
		setSelectedColumn();
		Log.d(TAG, "onTouchEvent: x " + selX);
		return true;
	}
	
	public void setSelectedColumn() {
		int y = ctrl.dropPiece(pos, selX);
		if (y >= 0) {
			int sq = Position.getSquare(selX, y);
			pos.makeMove(sq);
			if(ctrl.updateScore(pos, sq)){
				if(pos.yellowMove){
					redWins = true;
				} else {
					yellowWins = true;
				}
				invalidate();
			}
			//Log.d(TAG, "yellowMove: " + pos.toString());
		}
	}
	
	/**
     * Set the board to a given state.
     * @param pos
     */
    final public void setPosition(Position pos) {
        this.pos = new Position(pos);
        invalidate();
    }

}
