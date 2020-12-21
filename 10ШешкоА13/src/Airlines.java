import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Airlines {
	private List<Airline> list;

	public Airlines() {
		super();
	}
	
	public Airlines(List<Airline> list) {
		super();
		this.list = list;
	}

	@XmlElement
	public List<Airline> getAirline() {
		return list;
	}
	
	public void setAirline(List<Airline> list) {
		this.list = list;
	}
	
	
}
