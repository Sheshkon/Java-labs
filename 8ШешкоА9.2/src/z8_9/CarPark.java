package z8_9;

import java.io.*;
import java.util.*;

public class CarPark implements Serializable {
	private static final long serialVersionUID = 1L;
	String number;
	static final String P_number = "Number";
	String name;
	static final String P_name = "Name";
	String start_time;
	static final String P_start_time = "Start Time";
	String end_time;
	static final String P_end_time = "End Time";
	String tariff;
	static final String P_tariff = "Tariff";

	// validation methods:
	static Boolean validNumber(String str) {
		int i = 0, ndig = 0, nletter = 0;
		for (; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (Character.isDigit(ch)) {
				ndig++;
				continue;
			}
			if (Character.isLetter(ch)) {
				nletter++;
				continue;
			}
			if (ch == ' ' || ch == '-') {
				continue;
			}
			if (ch != '_') {
				return false;
			}
		}
		return (ndig == 5 && nletter == 2);
	}

	static Boolean validDate(String str) {
		StringTokenizer st = new StringTokenizer(str, ":. ");
		if (st.countTokens() != 5) {
			return false;
		}
		GregorianCalendar date = new GregorianCalendar();
		date.setLenient(false);
		int day = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken());
		int year = Integer.parseInt(st.nextToken());
		int hour = Integer.parseInt(st.nextToken());
		int minute = Integer.parseInt(st.nextToken());
		date.set(year, month, day, hour, minute);
		date.getTime();
		return true;
	}


	static Boolean validEndTime(String begin, String end) {
		StringTokenizer st_begin = new StringTokenizer(begin, ":. ");
		StringTokenizer st_end = new StringTokenizer(end, ":. ");
		GregorianCalendar first = new GregorianCalendar();
		GregorianCalendar second = new GregorianCalendar();
		first.setLenient(false);
		second.setLenient(false);
		
		int day = Integer.parseInt(st_end.nextToken());
		int month = Integer.parseInt(st_end.nextToken());
		int year = Integer.parseInt(st_end.nextToken());
		int hour = Integer.parseInt(st_end.nextToken());
		int minute = Integer.parseInt(st_end.nextToken());
		second.set(year, month, day, hour, minute);
		second.getTime();
		
		 day = Integer.parseInt(st_begin.nextToken());
		 month = Integer.parseInt(st_begin.nextToken());
		 year = Integer.parseInt(st_begin.nextToken());
		 hour = Integer.parseInt(st_begin.nextToken());
		 minute = Integer.parseInt(st_begin.nextToken());
		first.set(year, month, day, hour, minute);
		

		return first.before(second);
	}

	public static Boolean nextRead(Scanner fin, PrintStream out) {
		return nextRead(P_number, fin, out);
	}

	static Boolean nextRead(final String prompt, Scanner fin, PrintStream out) {
		out.print(prompt);
		out.print(": ");
		return fin.hasNextLine();
	}

	public static CarPark read(Scanner fin, PrintStream out) throws IOException {
		CarPark park = new CarPark();
		park.number = fin.nextLine();
		if (CarPark.validNumber(park.number) == false) {
			throw new IOException("");
		}
		if (!nextRead(P_name, fin, out))
			return null;
		park.name = fin.nextLine();
		if (!nextRead(P_start_time, fin, out))
			return null;
		park.start_time = fin.nextLine();
		if (CarPark.validDate(park.start_time) == false) {
			throw new IOException("Invalid start time value");
		}
		if (!nextRead(P_end_time, fin, out))
			return null;
		park.end_time = fin.nextLine();
		if (CarPark.validEndTime(park.start_time,park.end_time) == false) {
			throw new IOException("Invalid end time value");
		}
		if (!nextRead(P_tariff, fin, out))
			return null;
		park.tariff = fin.nextLine();

		return park;
	}

	public CarPark() {
	}

	public static final String areaDel = "\n";

	public String toString() {
		return new String(number + areaDel + name + areaDel + start_time + areaDel + end_time + areaDel + tariff);
	}
}
