package z6_10_part2;

import java.io.Serializable;

public class Analyst extends Worker implements Serializable{
	private static final long serialVersionUID = 1L;

	public Analyst(String name, int age, int work_experience) {
		super(Worker.Position.ANALYST, name, age, work_experience);
	}

}
