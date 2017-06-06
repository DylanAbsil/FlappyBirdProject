package moc.lab;

import ej.style.font.FontProfile;
import ej.style.font.loader.AbstractFontLoader;

public class FontLoader extends AbstractFontLoader {

	@Override
	protected int getFontHeight(FontProfile fontProfile) {
		// TODO Auto-generated method stub
		switch( fontProfile.getSize( ) ) {
			case LENGTH: {
				return fontProfile.getSizeValue( );
			}
			case LARGE: {
				return 50;
			}
			case MEDIUM:
			default: {
				return 30;
			}
		}
	}

}
