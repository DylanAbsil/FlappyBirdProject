package moc.lab.game;

import java.io.IOException;

import ej.microui.display.Display;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;

public class BackgroundEntity extends Entity {
	private Image mImage;

	public BackgroundEntity( ) {
		try {
			mImage = Image.createImage( "/images/background_bottom.png" );
			
			mSize.width = mImage.getWidth();
			mSize.height = mImage.getHeight();
			mOrigins.x = 0;
			mOrigins.y = Display.getDefaultDisplay().getHeight() - mSize.height;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void think(GraphicsContext gc) {
		if( mImage != null ) {
			gc.drawImage( mImage, mOrigins.x, mOrigins.y, 0);
			
			mOrigins.x -= 2;
			
			if( Math.abs( mOrigins.x ) >= mSize.width / 2 ) {
				mOrigins.x = 0;
			}
		}
	}
}
