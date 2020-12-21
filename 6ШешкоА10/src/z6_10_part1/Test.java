package z6_10_part1;

public class Test {
	public static Worker[] createGroup() {
		Worker[] people = new Worker[5];
		people[0] = new Tester("Kevin",27,5);
		people[1] = new Designer("Kate", 21, 1);
		people[2] = new Manager("Alex", 45, 15);
		people[3] = new Programmer("Viktor", 34, 12);
		people[4] = new Analyst("Jordan", 19,1);
		return people;
	}

	public static void main(String[] args) {
		
		
		try {
			Connector con = new Connector("group.dat");	
			con.write( createGroup());
			Worker[] group = con.read();
			System.out.println( "The group: ");
			for ( Worker n : group ) {
				System.out.println( n );
			}
		}
		catch ( Exception e ) {
			System.err.println(e);
		}

	}


	

}
