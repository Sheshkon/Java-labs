package z6_10_part1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Connector {
	private String filename;

	public Connector( String filename ) {
		this.filename = filename;
	}

	public void write( Worker[] band) throws IOException {
		FileOutputStream fos = new FileOutputStream (filename);
		try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {
			oos.writeInt( band.length );
			for ( int i = 0; i < band.length; i++) {
				oos.writeObject( band[i] );		
			}
			oos.flush();
		}
	}

	public Worker[] read() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
			int length = oin.readInt();
			Worker[] result = new Worker[length];
			for ( int i = 0; i < length; i++ ) {
				result[i] = (Worker) oin.readObject();
			}
			return result;	
		}
	}

	
}
