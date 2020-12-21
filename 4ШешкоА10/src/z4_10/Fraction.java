package z4_10;

class Fraction {
	static final int LIMIT = 100;
	private int numerator, denominator = 1;

	public Fraction() {
		 this.denominator = 1;
	}

	public Fraction(int numerator, int denominator) {
		
		if(denominator == 0) {
			System.out.println("Division by zero");
			throw new RuntimeException();
		}
		this.numerator = numerator;
		this.denominator = denominator;
		this.reduce();
	}

	public Fraction(int numerator) {
		this.numerator = numerator;
		assert(Math.abs(this.numerator) < LIMIT);
		this.denominator = 1;
	}

	private int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	private void reduce() {
		int b = gcd(this.numerator, this.denominator);
		this.numerator /= b;
		this.denominator /= b;
		if (this.denominator < 0) {
			this.numerator *= (-1);
			this.denominator *= (-1);
		}
		assert(Math.abs(this.numerator) < LIMIT && Math.abs(this.denominator)  < LIMIT);
	}

	public Fraction add(Fraction a) {
		this.numerator = a.numerator * this.denominator + a.denominator * this.numerator;
		this.denominator = a.denominator * this.denominator;
		this.reduce();
		return this;
	}

	public Fraction add(int a) {
		Fraction tmp = new Fraction(a);
		this.add(tmp);
		this.reduce();
		return this;
	}

	public Fraction multiply(Fraction a) {
		this.numerator = this.numerator * a.numerator;
		this.denominator = this.denominator * a.denominator;
		this.reduce();
		return this;
	}

	public Fraction multiply(int a) {
		this.numerator = this.numerator * a;
		this.reduce();
		return this;
	}

	public Fraction substract(Fraction a) {
		this.numerator = -a.numerator * this.denominator + a.denominator * this.numerator;
		this.denominator = a.denominator * this.denominator;
		this.reduce();
		return this;
	}

	public Fraction substract(int a) {
		Fraction tmp = new Fraction(a);
		this.substract(tmp);
		this.reduce();
		return this;
	}

	public Fraction divide(Fraction a) {
		this.denominator = a.numerator * this.denominator;
		this.numerator = a.denominator * this.numerator;
		this.reduce();
		return this;
	}

	public Fraction divide(int a) {
		if(a == 0) {
			System.out.println("Division by zero");
			throw new RuntimeException();
		}
		this.denominator = a * this.denominator;
		this.reduce();
		return this;
	}

	public int compare(Fraction a) {
		int this_n = this.numerator * a.denominator;
		int a_n = a.numerator * this.denominator;
		if (this_n < a_n) {
			return -1;
		} else if (this_n > a_n) {
			return 1;
		}
		return 0;
	}

	public int compare(int a) {
		Fraction tmp = new Fraction(a);
		return this.compare(tmp);
	}

	public void printFraction() {
		if (this.numerator == 0) {
			System.out.print(this.numerator);
		} else if (this.denominator == 1) {
			System.out.print(this.numerator);
		} else {
			System.out.print(this.numerator + "/" + this.denominator);
		}
	}

}
