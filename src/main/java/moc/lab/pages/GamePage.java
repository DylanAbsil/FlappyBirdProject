package moc.lab.pages;

import ej.widget.container.Grid;
import ej.widget.navigation.page.Page;
import moc.lab.FlappyBird;
import moc.lab.MainActivity;
import moc.lab.FlappyBird.IReturnMainMenu;

public class GamePage extends Page implements IReturnMainMenu {
	Grid container;
	
	public GamePage( ) {
		container = new Grid(true, 1);
		FlappyBird fb = new FlappyBird( this );
		container.add( fb );
		this.setWidget( container );
	}

	@Override
	public void returnMainMenu() {
		MainActivity.getNavigator().show( new MainPage() );
	}
}

