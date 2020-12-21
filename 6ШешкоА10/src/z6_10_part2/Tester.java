package z6_10_part2;

import java.io.Serializable;

public class Tester extends Worker implements Serializable {
	private static final long serialVersionUID = 1L;

	public Tester(String name, int age, int work_experience) {
		super(Worker.Position.TESTER, name, age, work_experience);
	}
}
