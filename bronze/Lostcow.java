import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Lostcow {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("lostcow.in")));
		String[] temp = br.readLine().split(" ");
		int john = Integer.parseInt(temp[0]);
		int bessie = Integer.parseInt(temp[1]);
		int johnOrig = john;
		
		int endNum = 1;
		int distanceTraveled = 0;
		
		while(true) {
			
			for(int i = 0; i < endNum; i++) {
				if(john == bessie) {
					break;
				}
				distanceTraveled++;
				john++;
				
				if(john == bessie) {
					break;
				}
				if(i == endNum - 1) {
					distanceTraveled += endNum;
					endNum += endNum;
					john = johnOrig;
					break;
				}
			}
			
			
			for(int i = endNum; i > 0; i--) {
				if(john == bessie) {
					break;
				}
				distanceTraveled++;
				john--;
				
				if(john == bessie) {
					break;
				}
				if(i == 1) {
					distanceTraveled += endNum;
					endNum += endNum;
					john = johnOrig;
					break;
				}
			}
			
			if(john == bessie) {
				break;
			}
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lostcow.out")));
		pw.write("" + distanceTraveled);
		pw.close();
		//System.out.println(distanceTraveled);
		
		/*for(int i = 0; i < endNum; i++) {
			if(john == bessie) {
				break;
			}
			distanceTraveled++;
			
		}*/
	}

}
