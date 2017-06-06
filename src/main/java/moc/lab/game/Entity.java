package moc.lab.game;

import ej.microui.display.GraphicsContext;
import moc.lab.game.Entity.Point;
import moc.lab.game.Entity.Size;

public abstract class Entity {
	protected Point mOrigins;
	protected Size mSize;
	
	public Entity( ) {
		this( 0, 0, 0, 0 );
	}
	
	public Entity( int x, int y, int width, int height ) {
		mOrigins = new Point( );
		mOrigins.x = x;
		mOrigins.y = y;
		
		mSize = new Size( );
		mSize.width = width;
		mSize.height = height;
	}
	
	public Point getOrigins( ) {
		return mOrigins;
	}
	
	public void setOrigins( Point point ) {
		this.mOrigins = point;
	}
	
	public Size getSize( ) {
		return mSize;
	}
	
	public void setSize( Size size ) {
		this.mSize = size;
	}
	
	public boolean touchWith(Entity other) {
		int x = mOrigins.x, x2 = other.mOrigins.x;
		int y = mOrigins.y, y2 = other.mOrigins.y;
		int w = mSize.width, w2 = other.mSize.width;
		int h = mSize.height, h2 = other.mSize.height;
		
		boolean A = pointInsideRectangle(x, y, other.mOrigins, other.mSize);
		boolean B = pointInsideRectangle(x, y + h, other.mOrigins, other.mSize);
		boolean C = pointInsideRectangle(x + w, y, other.mOrigins, other.mSize);
		boolean D = pointInsideRectangle(x + w, y + h, other.mOrigins, other.mSize);
		
		boolean A2 = pointInsideRectangle(x2, y2, mOrigins, mSize);
		boolean B2 = pointInsideRectangle(x2, y2 + h2, mOrigins, mSize);
		boolean C2 = pointInsideRectangle(x2 + w2, y2, mOrigins, mSize);
		boolean D2 = pointInsideRectangle(x2 + w2, y2 + h2, mOrigins, mSize);
		
		return ( A || B || C || D || A2 || B2 || C2 || D2 );
	}
	
	public boolean pointInsideRectangle( int x, int y, Point rectOrigin, Size rectSize ) {
		return (( x >= rectOrigin.x && x <= rectOrigin.x + rectSize.width ) && 
				( y >= rectOrigin.y && y <= rectOrigin.y + rectSize.height ));
	}
	
	public abstract void think(GraphicsContext gc);
	
	public class Point {
		public int x;
		public int y;
	}
	
	public class Size {
		public int width;
		public int height;
	}
}
