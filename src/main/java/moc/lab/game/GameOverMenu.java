package moc.lab.game;

import java.io.IOException;

import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;

public class GameOverMenu extends Entity {
	private static final int PICTURE_HEIGHT = 52;
	private static final int PICTURE_WIDTH = 200;
	private static final int SPACE_BETWEEN = 13;
	private int mTotalStateNumber;
	private Image mImage;
	private int mTransparency = 0;
	
	// une image = 200x52
		// espace entre les deux = 200x13
		
		/* 
		 * Start Y image1 = y
		 * End Y image1 = y + 52
		 * 
		 * Start Y image2 = y + 52 + 13
		 * End Y image2 = y + 52 + 13 + 52
		 */
	
	public GameOverMenu( int width, int height, int numberOfStates ) {
		mTotalStateNumber = numberOfStates;
		mSize.width = PICTURE_WIDTH;
		mSize.height = PICTURE_HEIGHT * 2 + SPACE_BETWEEN;
		
		mOrigins.x = ( Display.getDefaultDisplay().getWidth() / 2 ) - ( mSize.width / 2 );
		mOrigins.y = ( Display.getDefaultDisplay().getHeight() / 2 ) - ( mSize.height / 2 );
		
		try {
			mImage = Image.createImage( "/images/game_over.png" );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void think(GraphicsContext gc) {
		gc.setColor( Colors.BLACK );
		/*mOrigins.x = ( Display.getDefaultDisplay().getWidth() / 2 ) - ( mSize.width / 2 );
		mOrigins.y = ( Display.getDefaultDisplay().getHeight() / 2 ) - ( mSize.height / 2 );*/
		
		mTransparency += ( GraphicsContext.OPAQUE / mTotalStateNumber );
		
		if( mTransparency >= GraphicsContext.OPAQUE ) {
			mTransparency = GraphicsContext.OPAQUE;
		}
		
		//gc.fillRect( mOrigins.x, mOrigins.y, mSize.width, mSize.height );
		
		gc.drawImage(mImage, mOrigins.x, mOrigins.y, 0, mTransparency);
	}

	public boolean animationEnded() {
		return mTransparency == GraphicsContext.OPAQUE;
	}



	public int clicked(int x, int y) {
		if( 		mOrigins.x <= x && x <= ( mOrigins.x + mSize.width ) 
		&& 			mOrigins.y <= y && y <= ( mOrigins.y + PICTURE_HEIGHT ) ) {
			return 1;
		} else if( 	mOrigins.x <= x && x <= ( mOrigins.x + mSize.width ) 
		&& 			( mOrigins.y + PICTURE_HEIGHT + SPACE_BETWEEN ) <= y 
		&& 			y <= ( mOrigins.y + ( PICTURE_HEIGHT * 2 ) + SPACE_BETWEEN ) ) {
			return 2;
		}
		
		return 0;
	}

}
