package z11_9;

import java.io.*;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class CarPark implements Serializable {
	private static final long serialVersionUID = 1L;
	String number;
	String name;
	String start_time;
	String end_time;
	String tariff;

	public void setNumber(String number) {
				this.number = number;
				return;
	}

	public void setName(String name) {
			this.name = name;
			return;
	}

	public void setStart_time(String start_time) {
			this.start_time = start_time;
			return;

	}

	public void setEnd_time(String end_time) {
			this.end_time = end_time;
			return;

	}

	public void setTariff(String tariff) {
			this.tariff = tariff;
			return;

	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getStart_time() {
		return start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public String getTariff() {
		return tariff;
	}

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
		parseDate(str).getTime();
		return true;
	}
	public static GregorianCalendar parseDate(String str){
		StringTokenizer st = new StringTokenizer(str, ":. ");
		GregorianCalendar date = new GregorianCalendar();
		date.setLenient(false);
		int day = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken());
		int year = Integer.parseInt(st.nextToken());
		int hour = Integer.parseInt(st.nextToken());
		int minute = Integer.parseInt(st.nextToken());
		date.set(year, month, day, hour, minute);
		return date;
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

	public CarPark() {
	}

	public String toString() {
		return new String("Number: " +number + " Name :"+ name + " Start Time: " + start_time + " End Time: " + end_time + " Tariff: " + tariff);
	}

}
