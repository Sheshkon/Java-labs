package z6_10_part2;

import java.util.Locale;
import java.util.ResourceBundle;

public class AppLocale {
	private static final String strMsg = "Msg";
	private static Locale loc = Locale.getDefault();
	private static ResourceBundle res = 
			ResourceBundle.getBundle( AppLocale.strMsg, AppLocale.loc );
	
	static Locale get() {
		return AppLocale.loc;
	}
	
	static void set( Locale loc ) {
		AppLocale.loc = loc;
		res = ResourceBundle.getBundle( AppLocale.strMsg, AppLocale.loc );
	}
	
	static ResourceBundle getBundle() {
		return AppLocale.res;
	}
	
	static String getString( String key ) {
		return AppLocale.res.getString(key);
	}
	
	// Resource keys:
	
	public static final String position="position";
	public static final String creation="creation";
	public static final String name="name";
	public static final String age="age";
	public static final String work_experience="work_experience";
	public static final String the_group="the_group";
	public static final String Kate="Kate";
	public static final String Kevin="Kevin";
	public static final String Alex="Alex";
	public static final String Viktor="Viktor";
	public static final String Jordan="Jordan";
	public static final String David="David";
	
	
}
