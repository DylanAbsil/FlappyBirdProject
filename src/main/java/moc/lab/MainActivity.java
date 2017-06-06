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
		 * --> Faire le test des collisions entre : 
		 * Le joueur & le sol
		 * Le joueur & les pilliers
		 * --> Ajouter un système de point : 
		 * Détecter quand un joueur dépasse en X un pillier N grâce à :
		 *		Variable A = Origine X du joueur
		 *		Variable B = Origine X + taille WIDTH du pillier
		 *		Il faut que A > B pour que Point ++
		 * --> Afficer le nombre de points dans le jeux : 
		 * Créer une nouvelle font
		 * La mettre en haut de l'écran au milieu en GRAS
		 * --> Faire la page de Game Over avec le score actuel
		 * --> Faire une page Scores regroupant tous les scores
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
