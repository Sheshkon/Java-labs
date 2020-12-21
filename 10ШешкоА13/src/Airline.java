import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"destination","time","flight_number",
                      "type", "day"})

public class Airline {
	static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	@XmlEnum
	enum Type {
		Jet,
		Boeing,
		Helicopter,
		Rotorcraft
	}
	@XmlEnum
	enum DayOfWeek {
		SUNDAY,
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY
	}
	private String destination;
	private int flight_number;
	private Type type;
	private String time;
	private DayOfWeek day;
	
	public Airline() {
	}

	public Airline(String destination, int flight_number, Type type, String time, DayOfWeek day) {
		super();
		this.destination = destination;
		this.flight_number = flight_number;
		this.type = type;
		this.time = time;
		this.day = day;
	}
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	@XmlAttribute(name = "ID")
	public int getFlight_number() {
		return flight_number;
	}
	
	public void setFlight_number(int flight_number) {
		this.flight_number = flight_number;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) throws ParseException {
		validateTime(time);
		this.time = time;
	}

	public DayOfWeek getDay() {
		return day;
	}
	
	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "flight_number=" + flight_number + ",destination=" + destination + ", type=" + type
				+ ", time=" + time+ ", day=" + day;
	}
	
	
	boolean validateTime(String time) throws ParseException {
		try {
			Date _time = sdf.parse(time);
		}catch(ParseException e) {
			System.err.println("Invalid format of time");
			System.exit(1);
		}
		return true;
	}
	
}
