package z6_10_part1;

import java.io.Serializable;

public class Designer extends Worker implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Designer(String name, int age, int work_experience) {
		super(Worker.Position.DESIGNER, name, age, work_experience);
	}
	
	public Designer() {};
}
