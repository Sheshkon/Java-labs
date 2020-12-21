package z6_10_part2;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class Test {
	public static Worker[] createGroup() {
		Worker[] people = new Worker[6];
		people[0] = new Analyst(AppLocale.getString( AppLocale.Kevin),27,5);
		people[1] = new Designer(AppLocale.getString( AppLocale.Kate), 21, 1);
		people[2] = new Manager(AppLocale.getString( AppLocale.Alex), 45, 15);
		people[3] = new Programmer(AppLocale.getString( AppLocale.Viktor), 34, 12);
		people[4] = new Tester(AppLocale.getString( AppLocale.Jordan), 19,1);
		people[5] = new Analyst(AppLocale.getString( AppLocale.David), 24, 3);
		return people;
	}

	static Locale createLocale( String[] args )	{
		if ( args.length == 2 ) {
			return new Locale( args[0], args[1] );
		} else if( args.length == 4 ) {
			return new Locale( args[2], args[3] );
		}
		return null;
	}
	
	static void setupConsole(String[] args) {
		if ( args.length >= 2 ) {
			if ( args[0].compareTo("-encoding")== 0 ) {
				try {
					System.setOut( new PrintStream( System.out, true, args[1] ));
				} catch ( UnsupportedEncodingException ex ) {
					System.err.println( "Unsupported encoding: " + args[1] );
					System.exit(1);
				}				
			}
		}
	}

	public static void main(String[] args) {
		try {
			setupConsole( args );
			Locale loc = createLocale( args );
			if ( loc == null ) {
				System.err.println( 
						"Invalid argument(s)\n" +
				        "Syntax: [-encoding ENCODING_ID] language country\n" +
						"Example: -encoding Cp855 be BY" );
				System.exit(1);
			}
			AppLocale.set( loc );
			Connector con = new Connector("group.dat");	
			con.write( createGroup());
			Worker[] group = con.read();
			System.out.println( 
					AppLocale.getString( AppLocale.the_group) + ":" );
			for ( Worker n : group ) {
				System.out.println( n );
			}
		}
		catch ( Exception e ) {
			System.err.println(e);
		}
	}
}
