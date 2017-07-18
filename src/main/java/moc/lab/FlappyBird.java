package moc.lab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ej.bon.Timer;
import ej.bon.TimerTask;
import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.event.Event;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;
import ej.mwt.Widget;
import ej.style.Element;
import ej.style.State;
import moc.lab.game.BackgroundEntity;
import moc.lab.game.GameOverMenu;
import moc.lab.game.PillarEntity;
import moc.lab.game.Player;
import moc.lab.utils.FileManager;

public class FlappyBird extends Widget implements EventHandler, ej.style.Element {
	public interface IReturnMainMenu {
		void returnMainMenu( );
	}
	
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
	private Font mCustomScoreFont;
	private int mPoints;
	private int mCenterX;
	private GameOverMenu gameOverMenu;
	private IReturnMainMenu mMainMenuReturn;
	
	public FlappyBird( IReturnMainMenu returnMainMenu ) {
		super( );
		mMainMenuReturn = returnMainMenu;
		gameReset();
		setEventHandler(this);
		mCenterX = Display.getDefaultDisplay().getWidth() / 2;
		
		try {
			mImageStaticBackground = Image.createImage( "/images/background.png" );
			mImageStaticBackgroundGameOver = Image.createImage( "/images/background_game_over.png" );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			mCustomScoreFont = Font.getFont(85, 25, Font.STYLE_BOLD);
			if(mCustomScoreFont == Font.getDefaultFont()) {
				System.out.println("Unable to find custom font! Using default font instead");
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public void gameReset( ) {
		mPoints = 0;
		bShowGameOver = false;
		mBm = new BackgroundEntity();
		mPlayer = new Player( mBm );
		mPillars = new ArrayList<PillarEntity>( );
		gameOverMenu = new GameOverMenu( 350, 185, 100 );
		mPoints = 0;
		
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
			else if( /*Pointer.isClicked(event)*/ Pointer.isReleased(event)) {
				if( gameOverMenu.animationEnded() == true ) {
					Pointer p = (Pointer)Event.getGenerator(event);
					int buttonClicked = gameOverMenu.clicked( p.getX( ), p.getY() );
					
					if( buttonClicked == 1 ) {
						gameReset();
						repaint( );
					} else if( buttonClicked == 2 ) {
						if( mMainMenuReturn != null ) {
							mMainMenuReturn.returnMainMenu();
						}
					}
				} else {
					startGame();
				}
			}
		}
		
		return false;
	}

	@Override
	public void render(GraphicsContext gc) {
		if( mImageStaticBackground != null ) {
			if( bShowGameOver == false ) {
				gc.drawImage( mImageStaticBackground, 0, 0, 0);
			} else {
				gc.drawImage( mImageStaticBackgroundGameOver, 0, 0, 0);
			}
		}
		
		if( bShowGameOver == false ) {
			mBm.think( gc );

			for( int i = 0; i < mPillars.size( ); i ++ ) {
				mCurrentEntity = mPillars.get( i );
				if( mCurrentEntity != null ) {
					mCurrentEntity.think( gc );
					
					if( mCurrentEntity.checkPillardPassedPlayer(mPlayer)) {
						if( mCurrentEntity.shouldCountScore() ) {
							mPoints ++;
							mCurrentEntity.setScoreCounted( true );
						}
					}
				}
			}
		}
		
		gc.setColor(Colors.BLACK);
		gc.setFont(mCustomScoreFont);
		gc.drawString( String.valueOf( this.mPoints ), mCenterX, 10, GraphicsContext.HCENTER);
		mPlayer.think( gc );
		
		if( bShowGameOver == true ) {
			gameOverMenu.think( gc );
		}
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
					ms += GAME_TICK;
					
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
		}, 0, GAME_TICK);
	}
	
	public void endGame( boolean bKillPlayerAnimation ) {
		if( bKillPlayerAnimation == false && gameOverMenu.animationEnded( ) == true ) {
			if( mGameTimer != null ) {
				mGameTimer.cancel();
				mGameTimer = null;
			}
			
			FileManager.writeScore(mPoints);
		} else {
			mPlayer.kill( );
			bShowGameOver = true;
		}
	}

	@Override
	public void validate(int widthHint, int heightHint) {
		setPreferredSize(Display.getDefaultDisplay().getWidth(), Display.getDefaultDisplay().getHeight());
	}

	@Override
	public boolean hasClassSelector(String classSelector) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInState(State state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAttribute(String attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getParentElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element[] getChildrenElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getChild(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildrenCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}