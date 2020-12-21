package z7_9;
import java.io.Serializable;
import java.util.Scanner;

public class CarPark implements Serializable {
	private static final long serialVersionUID = 1L;
	String number;
    String name;
    String start_time;
    String end_time;
    String tariff;
	
    public static CarPark read( Scanner fin ) {
    	CarPark book = new CarPark();
        book.number = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        book.name = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        book.start_time = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        book.end_time = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        book.tariff = fin.nextLine();
        if ( ! fin.hasNextLine()) return null;
        return book;
    }
	
    public CarPark() {
    }

    public String toString() {
        return new String (
            number + "|" +
            name + "|" +
            start_time + "|" +
            end_time + "|" +
            tariff + "|" 	
        );
    }
}
