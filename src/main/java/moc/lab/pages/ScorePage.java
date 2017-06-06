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
import moc.lab.MainActivity;

public class ScorePage extends Page {
	Split container;
	public static Label mLabel;
	public ScorePage( ) {
		/*container = new Split( false, 0.8f );
		Grid grid = new Grid( true, 1 );
		mLabel = new Label( );
		Button buttonExit = new Button( "EXIT" );
		grid.add( mSlider );
		grid.add( mLabel );
		InOut.init( );
		InOut.attachSliderToPotentiometer();
		
		buttonExit.addOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick() {
				InOut.detachSliderToPotentiometer();
				if( MainActivity.getNavigator().canGoBackward() ) {
					MainActivity.getNavigator().back( );
				} else {
					ExitHandler exitHandler = ServiceLoaderFactory.getServiceLoader().getService( ExitHandler.class );
					
					if( exitHandler != null ) {
						exitHandler.exit( );
					}
				}
			}
		});
		
		container.setFirst( grid );
		container.setLast( buttonExit );
		this.setWidget( container );*/
	}
}

