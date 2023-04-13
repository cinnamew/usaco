import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Speeding {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("speeding.in")));
		String[] temp1 = br.readLine().split(" ");
		int numRoadSegments = Integer.parseInt(temp1[0]);
		int numCarSegments = Integer.parseInt(temp1[1]);
		
		int[] roadSegments = new int[100];
		int[] carSegments = new int[100];
		
		int previousSegLengths = 0;
		for(int i = 0; i < numRoadSegments; i++) {
			String[] temp2 = br.readLine().split(" ");
			int length = Integer.parseInt(temp2[0]);
			int speed = Integer.parseInt(temp2[1]);
			for(int j = previousSegLengths; j < previousSegLengths + length; j++) {
				roadSegments[j] = speed;
			}
			previousSegLengths += length;
		}
		previousSegLengths = 0;
		for(int i = 0; i < numCarSegments; i++) {
			String[] temp2 = br.readLine().split(" ");
			int length = Integer.parseInt(temp2[0]);
			int speed = Integer.parseInt(temp2[1]);
			for(int j = previousSegLengths; j < previousSegLengths + length; j++) {
				carSegments[j] = speed;
			}
			previousSegLengths += length;
		}
		
		int maxSpeed = 0;
		for(int i = 0; i < 100; i++) {
			int speed = carSegments[i] - roadSegments[i];
			if(speed > maxSpeed) maxSpeed = speed;
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));
		pw.write("" + maxSpeed);
		pw.close();
		System.out.println(maxSpeed);
	}

}
