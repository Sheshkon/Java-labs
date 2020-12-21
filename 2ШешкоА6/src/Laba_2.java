import java.util.*;

public class Laba_2 {

	public static void main(String[] args) {
		 Scanner in = new Scanner( System.in );
	        while ( in.hasNextLine() ) {
	            String str = in.nextLine();
	            StringTokenizer s = new StringTokenizer(str," ");
	            while(s.hasMoreTokens()) {
	            	String word = s.nextToken();
	            	if(word.length() <= 2) {
	            		System.out.print(word + ' ');
	            		 continue; }
	            	
	            	if( word.substring(
	            			word.length() - "ing".length() ,word.length()).equals("ing"))
	            		System.out.print(word.substring(0,word.length() - 3) + "ed ");
	            	else 
	            		System.out.print(word + ' ');
	            }
	            System.out.print("\n");
	            
	        }
	        in.close();
	        System.exit(0);

	}

}
