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

import java.util.ArrayList;
import java.util.Collections;

import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.style.Stylesheet;
import ej.style.border.ComplexRectangularBorder;
import ej.style.border.SimpleRectangularBorder;
import ej.style.selector.ClassSelector;
import ej.style.selector.TypeSelector;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.widget.basic.Label;
import ej.widget.composed.Button;
import ej.widget.container.List;
import ej.widget.container.Scroll;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import moc.lab.MainActivity;
import moc.lab.utils.FileManager;

public class ScorePage extends Page {
	Split container;
	Scroll scroll;
	private List list;
	private ArrayList<String> listScore;
	
	public ScorePage( ) {
		
		container = new Split( false, 0.8f );
		scroll = new Scroll(false, true);

		list = new List( false );
		listScore = FileManager.readScores();
		Collections.sort(listScore);
		Collections.reverse(listScore);
		
		for( int i=0; i < listScore.size(); i++){
			Label lbl = new Label( String.valueOf( listScore.get(i) ));
			lbl.setClassSelectors("ITEM_LIST");
			list.add(lbl);
		}
		
		Button buttonExit = new Button( "BACK" );
		buttonExit.getLabel().setClassSelectors( "EXIT_SCORE" );
		buttonExit.addOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick() {
				MainActivity.getNavigator().show(new MainPage());
			}
		});
		
		scroll.setWidget(list);
		container.setFirst( scroll );
		container.setLast( buttonExit );
		this.setWidget( container );
		
		initStyle();
	}
	
	void initStyle( ) {
		Stylesheet sts = StyleHelper.getStylesheet();
		EditableStyle esScroll = new EditableStyle( );
		ComplexRectangularBorder crb = new ComplexRectangularBorder();
		crb.setBottom(2);
		esScroll.setAlignment(GraphicsContext.HCENTER|GraphicsContext.VCENTER);
		EditableStyle esButtonExit = new EditableStyle( );
		esButtonExit.setBorder(crb);
		esButtonExit.setBackgroundColor( Colors.RED );
		esButtonExit.setAlignment(GraphicsContext.HCENTER|GraphicsContext.VCENTER);
		sts.addRule( new ClassSelector( "ITEM_LIST" ), esScroll );
		sts.addRule( new ClassSelector( "EXIT_SCORE" ), esButtonExit );
	}
}

