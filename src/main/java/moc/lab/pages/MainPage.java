/*package moc.lab.pages;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.exit.ExitHandler;
import ej.widget.composed.Button;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;

public class MainPage extends Page {
	
	public static final String CLASS_BUTTON_1 = "button1";
	public static final String CLASS_BUTTON_2 = "button2";
	public static final String CLASS_LABEL_BUTTON_1 = "labelButton1";
	public static final String CLASS_LABEL_BUTTON_2 = "labelButton2";
	
	Split mySplit;
	
	public MainPage( ) {
		mySplit = new Split( false, 0.75f );
		
		Button button1 = new Button( "CLICK ME" );
		Button button2 = new Button( "EXIT" );
		
		button1.setClassSelectors( CLASS_BUTTON_1 );
		button1.getLabel().setClassSelectors( CLASS_LABEL_BUTTON_1 );
		button2.setClassSelectors( CLASS_BUTTON_2 );
		button2.getLabel().setClassSelectors( CLASS_LABEL_BUTTON_2);
		
		mySplit.setFirst( button1 );
		mySplit.setLast( button2 );
		button1.addOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick() {
				System.out.println( "Click ME" );
			}
		});
		
		button2.addOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick() {
				System.out.println( "Exit clicked !" );
				ExitHandler exitHandler = ServiceLoaderFactory.getServiceLoader().getService( ExitHandler.class );
				
				if( exitHandler != null ) {
					exitHandler.exit( );
				}
			}
		});
		
		this.setWidget( mySplit );
	}
	
}*/

package moc.lab.pages;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.exit.ExitHandler;
import ej.hal.gpio.GPIO;
import ej.hal.gpio.GPIO.Mode;
import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.style.Stylesheet;
import ej.style.border.SimpleRectangularBorder;
import ej.style.selector.ClassSelector;
import ej.style.selector.TypeSelector;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.widget.basic.Label;
import ej.widget.basic.drawing.Slider;
import ej.widget.composed.Button;
import ej.widget.container.Grid;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.listener.OnValueChangeListener;
import ej.widget.navigation.page.Page;
import moc.lab.FlappyBird;
import moc.lab.MainActivity;

public class MainPage extends Page {
	Split container;
		
	public MainPage( ) {
		initStyle( );
		
		container = new Split( false, 0.8f );
		Grid grid = new Grid( true, 3 );
		Button button = new Button( "Play Game" );
		Button buttonScore = new Button( "Scores" );
		Button buttonExit = new Button( "EXIT" );
		grid.add( button );
		grid.add( buttonScore );
		grid.add( buttonExit );
		Label label = new Label( "FLAPPY BIRD" );
		label.setClassSelectors( "labelMain" );
		button.getLabel().setClassSelectors("labelButton");
		buttonScore.getLabel().setClassSelectors("labelButton");
		buttonExit.getLabel().setClassSelectors("labelButton");
		
		button.addOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick() {
				MainActivity.getNavigator().show( new GamePage() );
			}
		});
		
		buttonScore.addOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick() {
				MainActivity.getNavigator().show( new ScorePage() );
			}
		});
		
		buttonExit.addOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick() {
				ExitHandler exitHandler = ServiceLoaderFactory.getServiceLoader().getService( ExitHandler.class );
				
				if( exitHandler != null ) {
					exitHandler.exit( );
				}
			}
		});
		
		container.setFirst( label );
		container.setLast( grid );
		this.setWidget( container );
	}
	
	void initStyle( ) {
		Stylesheet sts = StyleHelper.getStylesheet();
		EditableStyle esLabel = new EditableStyle();
		
		esLabel.setBorder( new SimpleRectangularBorder( 3 ) );
		esLabel.setAlignment( GraphicsContext.HCENTER | GraphicsContext.VCENTER );
		EditableStyle esLabelMain = new EditableStyle( esLabel );
		esLabelMain.setBackgroundColor( Colors.RED );
		sts.addRule( new ClassSelector( "labelButton" ), esLabel);
		sts.addRule( new ClassSelector( "labelMain"), esLabelMain);
	}
}

