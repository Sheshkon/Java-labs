package z5_13;

import java.util.Arrays;
import java.util.Random;

public class Test {
	
	public static float roundDown(float d) {
	    return (float) ((float) Math.floor(d * 1e4) / 1e4);
	}
	
	
	public static void main(String[] args) {
		try {

			final int MAX = 5;
			Exponential e = new Exponential(15, 0.5f);
			System.out.println(e.toString());
			System.out.println("Sum = " + e.sum(2));
			System.out.println("b10 = " + e.getBj(10));
			
			System.out.println();
			Exponential[] arr = new Exponential[15];
			Random rnd = new Random();
			rnd.setSeed(System.currentTimeMillis());
			for (int i = 0; i < 15; i++) {
				arr[i] = new Exponential(roundDown(rnd.nextFloat()* MAX), roundDown(rnd.nextFloat() * MAX));
			}
			for (Exponential ex : arr) {
				System.out.println(ex);
				System.out.println("b7 = " + ex.getBj(7) + "\n" +"Sum = " + ex.sum(5) + "\n");
			}
			System.out.println();

			System.out.println(Exponential.getSortByName(0));
			Arrays.sort(arr, Exponential.getComparator(0));

			for (Exponential ex : arr) {
				System.out.println(ex);
			}
			System.out.println();

			System.out.println(Exponential.getSortByName(1));
			Arrays.sort(arr, Exponential.getComparator(1));

			for (Exponential ex : arr) {
				System.out.println(ex);
			}
			System.out.println();
			
			Exponential e1 = new Exponential("1.3 2");
			
			System.out.println(e1);
			System.out.println("b4 = " + e1.getBj(4));
			System.out.println("Sum(13) = " + e1.sum(13));

			// samples of exception
			//Exponential.getComparator(3);
			//Exponential e2 = new Exponential("1.3;2;89");
			//Exponential e3 = new Exponential("string");
			//Exponential e4 = new Exponential("");
			//Exponential e5 = new Exponential(15,0);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

	}
}
