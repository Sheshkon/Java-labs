//1 / sqrt(1 + x)
//(-1;1)
import java.util.*;
public class Laba_1 {

public static void main(String[] args) {
	
	Scanner in = new Scanner(System.in);
	System.out.println("Enter x:");
	double x = in.nextDouble();
    if ( x >= 1 || x <= -1 ) {
        System.err.println("Invalid x: " + x );
        System.exit(1);
    }
    
    System.out.println("Enter k:");
    int k = in.nextInt();
    if ( k <= 1 ) {
        System.err.println("Invalid k: " + k );
        System.exit(1);
    }
    
    double Eps = 1 / Math.pow( 10, k + 1 );
    double result = 0;
    double step = 1;
    int n = 1;
    while( Math.abs( step ) >= Eps ) {
        result += step;
        step = (-step  * x * (2 * n - 1) )/( 2 * n );
        n++;
}
    String fmt = "%."+ k + "f\n";
    System.out.println("Taylor series:");
    System.out.printf( fmt, result );
    System.out.println("Math:");
    System.out.printf( fmt, 1 / Math.sqrt(1 + x));
    System.exit(0);
}
}

