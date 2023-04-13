import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClockwiseFence {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("clockwisefence.in"));
		int fences = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < fences; i++) {
			String[] directions = br.readLine().split("");
			int overallAngle = 0;
			for(int j = 0; j < directions.length; j++) {
				String firstDirection = directions[j];
				String secondDirection = "";
				if(j == directions.length - 1) {
					secondDirection = directions[0];
				}else {
					secondDirection = directions[j + 1];
				}
				int first = returnAngle(firstDirection);
				int second = returnAngle(secondDirection);
				if((first + 90)%360 == second) {
					overallAngle -= 90;
				}
				else if(first == (second + 90)%360) {
					overallAngle += 90;
				}
				
			}
			if(overallAngle == 360) {
				System.out.println("CW");
			}
			else System.out.println("CCW");
		}
		
	}
	
	public static int returnAngle(String direction) {
		if(direction.equals("N")) return 0;
		else if(direction.equals("E")) return 270;
		else if(direction.equals("W")) return 90;
		else return 180;
	}

}
