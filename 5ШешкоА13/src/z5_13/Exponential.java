package z5_13;

import java.util.*;

public class Exponential extends Series implements Comparable<Exponential>, Iterable<Object>, Iterator<Object> {
	float  b1, q;
	
	Exponential() {
		q = 1;
		b1 = 0;
	}

	Exponential(float b1, float q) throws ArgException {
		if (q == 0) {
			throw new ArgException("q = 0");
		}
		this.q = q;
		this.b1 = b1;
	}

	public Exponential(String str) throws ArgException, NumberFormatException {
		if(str == null || str.length() == 0) {
			throw new ArgException("empty str");
		}
		StringTokenizer st = new StringTokenizer(str," ,;:|-+");
		if(st.countTokens() != 2) {
			throw new ArgException("incorrect str");
		}
		this.b1 = Float.valueOf(st.nextToken());
		this.q = Float.valueOf(st.nextToken());
		
	}

	@Override
	public float getBj(int j) {
		return (float) (this.b1 * Math.pow(this.q, j - 1));
	}

	@Override
	public float getBj(int j, float q, float b1) {

		return (float) (b1 * Math.pow(q, j - 1));
	}

	@Override
	public float sum(int n) {
		if (this.q == 1) {
			return n * this.b1;
		}
		return (float) ((this.b1 * (1 - Math.pow(this.q, n))) / (1 - q));
	}

	@Override
	public float sum(int n, float q, float b1) {
		if (q == 1) {
			return n * this.b1;
		}
		return (float) ((b1 * (1 - Math.pow(q, n))) / (1 - q));
	}

	public int compareTo(Exponential entry) {
		float result = this.b1 - entry.b1;

		if (result != 0) {
			return (int) (result / Math.abs(result));
		}

		result = this.q - entry.q;

		if (result != 0) {
			return (int) (result / Math.abs(result));
		}
		return 0;
	}

	public static String getSortByName(int sortBy) throws ArgException {

		if (sortBy == 0) {
			return "Sort by b1";
		} else if (sortBy == 1) {
			return "Sort by q";
		} else {
			throw new ArgException("only two class fields");
		}
	}

	public static Comparator<Exponential> getComparator(int sortBy) throws ArgException {
		if (sortBy != 0 && sortBy != 1) {
			throw new ArgException("only two class field");
		}
		return new Comparator<Exponential>() {
			public int compare(Exponential e1, Exponential e2) {
				if (sortBy == 0) {
					return Float.compare(e1.b1, e2.b1);
				}
				return Float.compare(e1.q, e2.q);
			}
		};
	}

	public Iterator<Object> iterator() {
		reset();
		return this;
	}

	private int iterator_idx = 0;

	public void reset() {
		iterator_idx = 0;
	}

	public boolean hasNext() {
		return iterator_idx > 1 ? false : true;
	}

	public void remove() {
		//
	}

	public Object next() {
		if (iterator_idx == 0) {
			iterator_idx++;
			return new Float(q);
		} else if (iterator_idx == 1) {
			iterator_idx++;
			return new Float(b1);
		} else {
			reset();
			return null;
		}
	}

	public String toString() {
		String result = new String();
		result += "b1 = " + Float.toString(this.b1) + "\tq = " + Float.toString(this.q) + "\t";
		return result;
	}

}
