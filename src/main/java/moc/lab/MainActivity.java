package moc.lab;

import ej.microui.MicroUI;
import ej.mwt.Desktop;
import ej.mwt.Panel;
import ej.wadapps.app.Activity;
import ej.widget.navigation.navigator.HistorizedNavigator;
import moc.lab.pages.MainPage;

public class MainActivity implements Activity {
	private static HistorizedNavigator mNavigator = null;
	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRestart() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onStart() {
		MicroUI.start();
		Desktop desk = new Desktop();
		Panel pnl = new Panel( );
		mNavigator = new HistorizedNavigator();
		mNavigator.show( new MainPage( ) );
		pnl.setWidget( mNavigator );
		pnl.show( desk, true );
		desk.show( );
		
		/**TODO
		 * --> Faire la page de Game Over avec le score actuel
		 */
	}
	
	public static HistorizedNavigator getNavigator( ) {
		return mNavigator;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

}
