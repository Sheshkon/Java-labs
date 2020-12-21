package z6_10_part1;

import java.io.Serializable;

public class Programmer extends Worker implements Serializable  {
	private static final long serialVersionUID = 1L;

	public Programmer(String name, int age, int work_experience) {
		super(Worker.Position.PROGRAMMER, name, age, work_experience);
	}
	
	public Programmer() {};

	
}
