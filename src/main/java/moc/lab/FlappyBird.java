package moc.lab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ej.bon.Timer;
import ej.bon.TimerTask;

import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.event.Event;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;
import moc.lab.game.BackgroundEntity;
import moc.lab.game.PillarEntity;
import moc.lab.game.Player;

public class FlappyBird extends Displayable implements EventHandler {
	public static final int GAME_TICK = 10;
	private BackgroundEntity mBm;
	private Player mPlayer;
	private Image mImageStaticBackground;
	private Image mImageStaticBackgroundGameOver;
	private int ms = 0;
	private List<PillarEntity> mPillars;
	private PillarEntity mCurrentEntity = null;
	private Timer mGameTimer;
	private boolean bShowGameOver;
	private int mPoints;
	
	public FlappyBird(Display display) {
		super(Display.getDefaultDisplay());
		bShowGameOver = false;
		mBm = new BackgroundEntity();
		mPlayer = new Player( mBm );
		mPillars = new ArrayList<PillarEntity>( );
		mPoints = 0;
		
		try {
			mImageStaticBackground = Image.createImage( "/images/background.png" );
			mImageStaticBackgroundGameOver = Image.createImage( "/images/background_game_over.png" );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void gameReset( ) {
		mPoints = 0;
		bShowGameOver = false;
		mBm = new BackgroundEntity();
		mPlayer = new Player( mBm );
		mPillars = new ArrayList<PillarEntity>( );
		
		if( mGameTimer != null ) {
			mGameTimer.cancel( );
			mGameTimer = null;
		}
		
		System.gc();
	}
	
	private static boolean bSwiping = false;

	@Override
	public boolean handleEvent(int event) {
		if( mPlayer != null && Event.getType(event)== Event.POINTER ) {
			if( mGameTimer != null ) {
				if( !bSwiping && Pointer.isDragged(event)) {
					bSwiping = true;
				} else if( bSwiping && Pointer.isReleased(event)) {
					mPlayer.jump();
					bSwiping = false;
				}
			} 
			else if( Pointer.isClicked(event)) {
				startGame();
			}
		}
		
		return false;
	}

	@Override
	public void paint(GraphicsContext gc) {
		if( mImageStaticBackground != null ) {
			if( bShowGameOver == false ) {
				gc.drawImage( mImageStaticBackground, 0, 0, 0);
			} else {
				gc.drawImage( mImageStaticBackgroundGameOver, 0, 0, 0);
			}
		}
		
		mBm.think( gc );
		
		
		for( int i = 0; i < mPillars.size( ); i ++ ) {
			mCurrentEntity = mPillars.get( i );
			if( mCurrentEntity != null ) {
				mCurrentEntity.think( gc );
			}
		}
		
		mPlayer.think( gc );
	}
	
	public void startGame( ) {
		mGameTimer = new Timer();
		mGameTimer.schedule(new TimerTask() {
			private PillarEntity mCurrentPillar;
			private int i;
			@Override
			public void run() {
				repaint();
				
				if( mPlayer.isDying( ) ) {
					if( mPlayer.isDeathAnimationFinished( ) == true ) {
						endGame( false );
					}
				} else {
					ms += 10;
					
					if( ms >= 1000 ) {
						ms = 0;
						mPillars.add( new PillarEntity(mBm) );
					}
					
					for( i = 0; i < mPillars.size( ); i ++ ) {
						mCurrentPillar = mPillars.get( i );
						
						if( mCurrentPillar == null ) {
							continue;
						}
						if( mCurrentPillar.isDead( ) ) {
							mPillars.remove( i );
						} else {
							if( mCurrentPillar.touchWith(mPlayer)) {
								endGame( true );
							}
						}
					}
	
					if( mPlayer.touchWith( mBm ) ) {
						endGame( true );
					}
				}
			}
		}, 0, 10);
	}
	
	public void endGame( boolean bKillPlayerAnimation ) {
		if( bKillPlayerAnimation == false ) {
			if( mGameTimer != null ) {
				mGameTimer.cancel();
				mGameTimer = null;
			}
		} else {
			mPlayer.kill( );
			bShowGameOver = true;
		}
	}

	@Override
	public EventHandler getController() {
		// TODO Auto-generated method stub
		return this;
	}

}