package z6_10_part1;

import java.io.Serializable;

public abstract class Worker implements Serializable  {

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
	public Worker() {}

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
		return new String("Worker: "  + position  +
						  " Name: " + name +
						  " Age: " + age + 
						  " Work experience: " + work_experience);
	}

	
	

	
	
}
