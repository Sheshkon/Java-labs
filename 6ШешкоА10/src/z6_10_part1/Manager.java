package z6_10_part1;

import java.io.Serializable;

public class Manager extends Worker implements Serializable {
	private static final long serialVersionUID = 1L;

	public Manager(String name, int age, int work_experience) {
		super(Worker.Position.MANEGER, name, age, work_experience);
	}

	public Manager() {};
}
