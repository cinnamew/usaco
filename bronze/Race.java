import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Race {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("race.in"));
		String[] line1 = br.readLine().split(" ");
		int raceLength = Integer.parseInt(line1[0]);
		int xValues = Integer.parseInt(line1[1]);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("race.out")));
		for(int i = 0; i < xValues; i++) {
			int finalSpeed = Integer.parseInt(br.readLine());
			int answer = binarySearch(1, 1000000, finalSpeed, raceLength);
			System.out.println(answer);
			pw.write("" + answer + "\n");
		}
		pw.flush();
	}
	
	public static int binarySearch(int minTime, int maxTime, int finalSpeed, int raceLength) {
		if(minTime >= maxTime) {
			return minTime;
		}
		int middleTime = minTime + (maxTime - minTime)/2;
		boolean works = checker(middleTime, finalSpeed, raceLength);
		if(works) {
			return binarySearch(minTime, middleTime, finalSpeed, raceLength);
		}
		return binarySearch(middleTime + 1, maxTime, finalSpeed, raceLength);
	}
	
	public static boolean checker(long time, int finalSpeed, int raceLength) {
		long highestSpeed = (time + finalSpeed)/2;
		long distance;
		if(time < finalSpeed) {
			distance = time*(time+1)/2;
		}else {
			if((time + finalSpeed)%2 == 0) {
				distance = (highestSpeed*(highestSpeed+1)/2 + ((highestSpeed-1)*highestSpeed)/2 - ((finalSpeed - 1)*finalSpeed)/2);
			}else {
				distance = (highestSpeed*(highestSpeed+1) - ((finalSpeed - 1)*finalSpeed)/2);
			}
		}
		if(distance >= raceLength) {
			return true;
		}
		return false;
	}

}