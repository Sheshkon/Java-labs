import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

public class JaxbWorker {
	static List<Airline> list = new ArrayList<Airline>();
	static final String fileName = "airline.xml";
	public static void main(String[] args) throws ParseException {
		Airline airline;
		airline = new Airline("New York",478147,Airline.Type.Boeing,"13:24",Airline.DayOfWeek.FRIDAY);
		list.add(airline);
		airline = new Airline("Moscow",578147,Airline.Type.Helicopter,"17:30",Airline.DayOfWeek.SATURDAY);
		list.add(airline);
		airline = new Airline("Hong Kong",748545,Airline.Type.Jet,"11:30",Airline.DayOfWeek.TUESDAY);
		list.add(airline);
		airline = new Airline("Turkey",548847,Airline.Type.Rotorcraft,"17:30",Airline.DayOfWeek.SATURDAY);
		list.add(airline);
		airline = new Airline("Brazil",145785,Airline.Type.Boeing,"00:00",Airline.DayOfWeek.MONDAY);
		list.add(airline);
		
		Airlines Airlines = new Airlines();
		Airlines.setAirline(list);
		 
		convertObjectToXml(Airlines,fileName);
	
		Airlines unmarshAirLine = fromXmlToObject(fileName);
		if(unmarshAirLine != null) {
			for(Airline el: unmarshAirLine.getAirline()) {
				System.out.println(el);
			}
		}
	
	}
	
	private static Airlines fromXmlToObject(String filePath) {
	    try {
	      JAXBContext jaxbContext = JAXBContext.newInstance(Airlines.class);
	      Unmarshaller un = jaxbContext.createUnmarshaller();
	      
	      return (Airlines)un.unmarshal(new File(filePath));
	    }catch(JAXBException e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
	  
	  private static void convertObjectToXml(Airlines list, String filePath)  {
	    try {
	      JAXBContext context = JAXBContext.newInstance(Airlines.class);
	      context.generateSchema(new SchemaOutputResolver() {
              @Override
              public Result createOutput(String namespaceUri, String suggestedFileName) {
                  return new StreamResult("xsd\\airline.xsd");
              }
          });
	      Marshaller marshaller = context.createMarshaller();
	      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	      marshaller.marshal(list,  new File(filePath));
	      marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "airline.xsd");
	      marshaller.marshal(list,  new File("xsd\\airline.xml"));
	    }catch(JAXBException | IOException e) {
	      e.printStackTrace();
	    }
	  }
}
