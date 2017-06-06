package moc.lab.game;

import java.io.IOException;

import ej.microui.display.Display;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import moc.lab.FlappyBird;

public class Player extends Entity {
	private static final long JUMP_ANIMATION_TIME = 40l;
	private static final long REBOUNCE_ANIMATION_TIME = 100l;
	private static final long REBOUNCE_NUMBER = 2;
	private Image mImage;
	private boolean mJumping = false;
	private long mJumpAnimationTime = 0;
	private long mAfterJumpTime = 0;
	private int mHeightMax = 0;
	private int mBottomDeathHeight;
	private boolean mInDeathState = false;
	private int mRebounceNumber = 0;
	private boolean mRebounceState = false;
	private boolean mDead = false;
	private long mRebounceTime;
	private BackgroundEntity mBe;
	private boolean mDeathTouchedGround;
	
	public Player( BackgroundEntity be ) {
		mBe = be;
		mBottomDeathHeight = be.getSize().height;
		mInDeathState = false;
		mDeathTouchedGround = false;
		mDead = false;
		mHeightMax = Display.getDefaultDisplay().getHeight();
		try {
			mImage = Image.createImage( "/images/flappy_bird.png" );
			
			mSize.width = mImage.getWidth();
			mSize.height = mImage.getHeight();
			mOrigins.x = 50;
			mOrigins.y = Display.getDefaultDisplay().getHeight() / 2;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void think(GraphicsContext gc) {
		if( mImage != null ) {
			if( mInDeathState == false ) {
				if( mJumping == true ) {
					if( mJumpAnimationTime < JUMP_ANIMATION_TIME ) {
						mJumpAnimationTime += FlappyBird.GAME_TICK;
					} else {
						mJumping = false;
						mAfterJumpTime = 0l;
					}
					
					mOrigins.y -= 8;
					
					if( mOrigins.y <= 0 ) {
						mOrigins.y = 0;
					}
				} else {
					if( mAfterJumpTime < 300l ) {
						mOrigins.y += ( mAfterJumpTime / 100 );
						mAfterJumpTime += FlappyBird.GAME_TICK;
						
						if( mOrigins.y == 0 ) {
							
						}
					} else {
						mOrigins.y += 3;
					}
					
					if( mOrigins.y >= ( mHeightMax - mSize.height - mBottomDeathHeight ) ) {
						mOrigins.y = mHeightMax - mSize.height - mBottomDeathHeight;
					}
				}
			} else {
				if( mDeathTouchedGround == false && mBe.touchWith( this ) == false ) {
					mOrigins.y += 3;
				} else {
					mDeathTouchedGround = true;
					if( mRebounceNumber < REBOUNCE_NUMBER ) {
						if( mRebounceTime < REBOUNCE_ANIMATION_TIME ) {
							mRebounceTime += FlappyBird.GAME_TICK;
							if( mRebounceState == true ) {
								mOrigins.y -= 3;
							}
							else {
								mOrigins.y += 3;
								
								if( mOrigins.y >= ( mHeightMax - mSize.height - mBottomDeathHeight ) ) {
									mOrigins.y = mHeightMax - mSize.height - mBottomDeathHeight;
								}
							}
						} else {
							if( mRebounceState == false ) {
								mRebounceNumber ++;
							}
							
							mRebounceTime = 0l;
							mRebounceState = !mRebounceState;
						}
					} else {
						mDead = true;
					}
				}
			}
			
			gc.drawImage( mImage, mOrigins.x, mOrigins.y, 0);
		}
	}
	
	public void jump( ) {
		mJumping = true;
		mJumpAnimationTime = 0;
	}

	public void kill() {
		mInDeathState = true;
		mRebounceNumber = 0;
		mRebounceState = true;
		mRebounceTime = 0l;
		mDeathTouchedGround = false;
	}

	public boolean isDying() {
		return mInDeathState;
	}

	public boolean isDeathAnimationFinished() {
		return mDead;
	}
}
