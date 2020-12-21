package z6_10_part2;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public abstract class Worker implements Serializable  {
	public final Date creationDate = new Date();
	public String getCreationDate() {
	
		DateFormat dateFormatter = DateFormat.getDateTimeInstance(
				DateFormat.FULL, DateFormat.FULL, AppLocale.get());
		
	    String dateOut = dateFormatter.format(creationDate);
		return dateOut;
	}

	private static final long serialVersionUID = 1L;

	public enum Position {ANALYST,DESIGNER,MANEGER,PROGRAMMER,TESTER}
	private Position position;
	private String name;
	private int age;
	private int work_experience;
	
	public Worker(Position position, String name, int age, int work_experience) {
		this.position = position;
		this.name = name;
		this.age = age;
		this.work_experience = work_experience;
	}

	public Position getposition() {
		return position;
	}

	public void setposition(Position position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getwork_experience() {
		return work_experience;
	}

	public void setwork_experience(int work_experience) {
		this.work_experience = work_experience;
	}

	@Override
	public String toString() {
		return new String( AppLocale.getString(AppLocale.position) + ": "  + position  + " " +
				           AppLocale.getString(AppLocale.name) + ": "  + name  + " " +
				           AppLocale.getString(AppLocale.age) + ": "  + age  + " " +
				           AppLocale.getString(AppLocale.work_experience) + ": "  + work_experience + " " +
				           AppLocale.getString(AppLocale.creation) + ": "  + getCreationDate() );
	}
	

	
	
	

	
	
}
