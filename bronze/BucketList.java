import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class BucketList {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("blist.in"));
		int numCows = Integer.parseInt(br.readLine());
		blistCow[] cows = new blistCow[numCows];
		for(int i = 1; i <= numCows ; i++) {
			String[] temp = br.readLine().split(" ");
			cows[i - 1] = new blistCow(i, Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
		}
		
		Arrays.sort(cows);
		int maxBuckets = 0;
		int currentBuckets = 0;
		ArrayList<blistCow> milkingCows = new ArrayList<blistCow>();
		
		for(int i = 0; i < numCows; i++) {
			currentBuckets += cows[i].getBucketsNeeded();
			for(int j = milkingCows.size() - 1; j >= 0; j--) {
				if(milkingCows.get(j).getEndTime() <= cows[i].getStartTime()) {
					currentBuckets -= milkingCows.get(j).getBucketsNeeded();
					milkingCows.remove(j);
				}
			}
			if(currentBuckets > maxBuckets) maxBuckets = currentBuckets;
			
			milkingCows.add(cows[i]);
		}
		
		System.out.println(maxBuckets);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));
		pw.write("" + maxBuckets);
		pw.flush();
	}
	

}

class blistCow implements Comparable {
	int cowNumber;
	int startTime;
	int endTime;
	int bucketsNeeded;
	
	public blistCow(int cowNumber, int startTime, int endTime, int bucketsNeeded) {
		this.cowNumber = cowNumber;
		this.startTime = startTime;
		this.endTime = endTime;
		this.bucketsNeeded = bucketsNeeded;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
	
	public int getBucketsNeeded() {
		return bucketsNeeded;
	}

	@Override
	public int compareTo(Object otherCow) {
		int otherStart = ((blistCow)otherCow).getStartTime();
		if(startTime < otherStart) return -1;
		else if(startTime == otherStart) return 0;
		else return 1;
	}
	
	
}