import java.util.*;
public class Laba_3 {

	public static void main(String[] args) {
		Scanner in =  new Scanner(System.in);
		System.out.print("Enter n: ");

		int n = in.nextInt();
		in.close();
		if ( n <= 1 ) {
			 System.err.println(
			 "Invalid n value (require: n > 1");
			 System.exit( 1 );
		}
		
		Random rnd = new Random();
		rnd.setSeed(System.currentTimeMillis() );
		int[][] a = new int[n][n];
		System.out.println("Source values: ");
		 for (int i = 0; i < n; i++) {
			 for (int j = 0; j < n; j++) {
				 int temp = rnd.nextInt();
				 a[i][j] = temp % (n + 1);
				 
				 System.out.printf("%5d",a[i][j]);
			 }
		System.out.println();
		 }
		 
		 int[][] tmp = new int[n][n];
		for(int i = 0;i <n;i++)
			tmp[i] = Arrays.copyOf(a[i], n);
		 
		 for (int i = 0; i < n; i++) {
			 for (int j = 0; j < n; j++) {
				 if(i ==  0) {
					 if(j == 0 ) 
						 a[i][j] = Math.round((float)(tmp[i+1][j] + tmp[i][j+1] + tmp[i+1][j+1]) / 3);
					 else if(j == n - 1)
						 a[i][j] = Math.round((float)(tmp[i+1][j] + tmp[i][j-1] + tmp[i+1][j-1]) / 3);
					 else a[i][j] = Math.round((float)(tmp[i][j-1] + tmp[i][j+1] + tmp[i+1][j-1] + tmp[i+1][j] + tmp[i+1][j+1]) / 5);
				 }
				 
				 else if(i == n-1) {
					 if(j == 0)
						 a[i][j] = Math.round((float)(tmp[i-1][j] + tmp[i-1][j+1] + tmp[i][j+1]) / 3);
					 else if(j == n-1)
						 a[i][j] = Math.round((float)(tmp[i][j-1] + tmp[i-1][j] + tmp[i-1][j-1]) / 3);
					 else a[i][j] = Math.round((float)(tmp[i][j-1] + tmp[i][j+1] + tmp[i-1][j-1] + tmp[i-1][j] + tmp[i-1][j+1]) / 5);
				 }
				 
				 else if(j == 0) {
					 a[i][j] = Math.round((float)(tmp[i+1][j] + tmp[i-1][j] + tmp[i][j+1] + tmp[i-1][j+1] + tmp[i+1][j+1]) / 5);
				 }
				 
				 else if(j == n-1) {
					 a[i][j] = Math.round((float)(tmp[i+1][j] + tmp[i-1][j] + tmp[i][j-1] + tmp[i-1][j-1] + tmp[i+1][j-1]) / 5); 
				 }
				 
				 else {
					 a[i][j] = Math.round((float)(tmp[i-1][j-1] + tmp[i-1][j] + tmp[i-1][j+1] + tmp[i][j-1] + tmp[i][j+1] + tmp[i+1][j-1] + tmp[i+1][j] + tmp[i+1][j+1]) / 8);
				 }
				 
			 } 
		 }
		 
		 System.out.println("\nResult:");
			 for (int i = 0; i < n; i++) {
				 for (int j = 0; j < n; j++) {
					 
					 System.out.printf("%5d",a[i][j]);
				 }
			System.out.println();
			 }
	}
	
}