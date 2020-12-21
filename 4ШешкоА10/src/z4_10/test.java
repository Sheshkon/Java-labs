package z4_10;

import java.util.Random;
import java.util.Scanner;

public class test {
	static final int NUM = 55;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter k (length of array): ");
		int k = in.nextInt();
		if(k <= 1) {
			System.err.print("Invalid value of k");
			System.exit(1);
		}
		System.out.println("(1) From keyboard" + "\n" + "(2) Random" + "\n" + "(Other) Exit");
		int choosed = in.nextInt();
		Fraction[] array = new Fraction[k];
		switch (choosed) {
		case 1:
			for (int i = 0; i < k; i++) {
				System.out.print("Enter numerator: ");
				int numerator = in.nextInt();
				System.out.print("Enter denominator: ");
				int denominator = in.nextInt();
				System.out.println();
				array[i] = new Fraction(numerator,denominator);
			}
			for (int i = 0; i < k; i++) {
				System.out.print("\t");
				array[i].printFraction();
				System.out.println();
			}
			
			break;
		case 2:
			Random rnd = new Random();
			rnd.setSeed(System.currentTimeMillis());
			for (int i = 0; i < k; i++) {
				array[i] = new Fraction(rnd.nextInt() % (NUM + 1), rnd.nextInt() % (NUM + 1));
				System.out.print("\t");
				array[i].printFraction();
				System.out.println();
			}
			break;
		default:
			System.out.println("Exit...");
			System.exit(0);
			break;
		}
		in.close();
		
		
		Fraction a = new Fraction(1, 2);
		Fraction b = new Fraction(4, -3);
		
		System.out.println("Examples of methods ");
		
		if(a.compare(b) == -1) {
			a.printFraction();
			System.out.print(" < ");
			b.printFraction();
		} else if(a.compare(b) == 1) {
			a.printFraction();
			System.out.print(" > ");
			b.printFraction();
		} else {
			a.printFraction();
			System.out.print(" = ");
			b.printFraction();
		}
		System.out.println();
		
		a.printFraction();
		System.out.print(" * ");
		b.printFraction();
		a.multiply(b);
		System.out.print(" = ");
		a.printFraction();
		System.out.println();
		
		a.printFraction();
		System.out.print(" + ");
		b.printFraction();
		a.add(b);
		System.out.print(" = ");
		a.printFraction();
		System.out.println();
		
		a.printFraction();
		System.out.print(" / ");
		b.printFraction();
		a.divide(b);
		System.out.print(" = ");
		a.printFraction();
		System.out.println();
		
		a.printFraction();
		System.out.print(" - ");
		b.printFraction();
		a.substract(b);
		System.out.print(" = ");
		a.printFraction();
		System.out.println();
	}

}
