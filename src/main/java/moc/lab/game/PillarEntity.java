package moc.lab.game;

import java.util.Random;

import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.GraphicsContext;
import moc.lab.game.Entity.Point;
import moc.lab.game.Entity.Size;

public class PillarEntity extends Entity {
	//private static final int CONFIG_OPEN_SPACE_HEIGHT = 70;
	private static final int CONFIG_OPEN_SPACE_HEIGHT = 90;
	private static final int CONFIG_RECTANGLES_WIDTH = 40;
	private int mOpenSpaceY = 0;
	private int mHeightBottom;
	private int mDisplayHeight;
	public PillarEntity( BackgroundEntity be ) {
		mHeightBottom = be.getSize().height;
		mDisplayHeight = Display.getDefaultDisplay().getHeight();
		Random rand = new Random();
		//int maxHeight = mDisplayHeight - 60 - mHeightBottom;
		int maxHeight = mDisplayHeight - ( CONFIG_OPEN_SPACE_HEIGHT ) - ( mHeightBottom * 2 );
		int minHeight = 60;
	    int randomNum = rand.nextInt((maxHeight - minHeight) + 1) + minHeight;
	    
		mOpenSpaceY = randomNum;
		
		mOrigins.x = Display.getDefaultDisplay().getWidth();
		mOrigins.y = 0;
		
		mSize.width = CONFIG_RECTANGLES_WIDTH;
	}
	
	@Override
	public void think(GraphicsContext gc) {
		if( isDead( ) == false ) {
			gc.setColor( Colors.GREEN );
			gc.fillRect(mOrigins.x, 0, mSize.width, mOpenSpaceY );
			gc.fillRect(mOrigins.x, mOpenSpaceY + CONFIG_OPEN_SPACE_HEIGHT, mSize.width, 
				mDisplayHeight - CONFIG_OPEN_SPACE_HEIGHT - mOpenSpaceY - mHeightBottom );
			
			mOrigins.x -= 2;
		}
	}
	
	
	// Pillar.touchWith( player )
	@Override
	public boolean touchWith(Entity other) {
		int x = mOrigins.x, x2 = other.mOrigins.x;
		int y = mOrigins.y, y2 = other.mOrigins.y;
		int w = mSize.width, w2 = other.mSize.width;
		int h = mOpenSpaceY, h2 = other.mSize.height;
		
		boolean A = pointInsideRectangle(x, 0, other.mOrigins, other.mSize);
		boolean B = pointInsideRectangle(x, h, other.mOrigins, other.mSize);
		boolean C = pointInsideRectangle(x + w, 0, other.mOrigins, other.mSize);
		boolean D = pointInsideRectangle(x + w, h, other.mOrigins, other.mSize);
		
		mOrigins.y = 0;
		boolean A2 = pointInsideRectangle(x2, y2, mOrigins, mSize);
		boolean B2 = pointInsideRectangle(x2, y2 + h2, mOrigins, mSize);
		boolean C2 = pointInsideRectangle(x2 + w2, y2, mOrigins, mSize);
		boolean D2 = pointInsideRectangle(x2 + w2, y2 + h2, mOrigins, mSize);
		
		y = mOpenSpaceY + CONFIG_OPEN_SPACE_HEIGHT;
		h = mDisplayHeight - CONFIG_OPEN_SPACE_HEIGHT - mOpenSpaceY - mHeightBottom;
		
		boolean A3 = pointInsideRectangle(x, y, other.mOrigins, other.mSize);
		boolean B3 = pointInsideRectangle(x, y + h, other.mOrigins, other.mSize);
		boolean C3 = pointInsideRectangle(x + w, y, other.mOrigins, other.mSize);
		boolean D3 = pointInsideRectangle(x + w, y + h, other.mOrigins, other.mSize);
		
		boolean A4 = pointInsideRectangle(x2, y2, mOrigins, mSize);
		boolean B4 = pointInsideRectangle(x2, y2 + h2, mOrigins, mSize);
		boolean C4 = pointInsideRectangle(x2 + w2, y2, mOrigins, mSize);
		boolean D4 = pointInsideRectangle(x2 + w2, y2 + h2, mOrigins, mSize);
		
		return ( A || B || C || D || A2 || B2 || C2 || D2 || A3 || B3 || C3 || D3 || A4 || B4 || C4 || D4 );
	}
	
	public boolean isDead( ) {
		if( mOrigins.x < 0 - mSize.width ) {
			return true;
		}
		
		return false;
	}

	
}
