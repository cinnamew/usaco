import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Angry {
	
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("angry.in")));
		int hayNum = Integer.parseInt(br.readLine());
		HayBale[] hays = new HayBale[hayNum];
		for(int i = 0; i < hayNum; i++) {
			hays[i] = new HayBale(Integer.parseInt(br.readLine()));
		}
		
		Arrays.sort(hays);
		int maxExplosions = 0;
		int radius = 1;
		
		for(int i = 0; i < hayNum; i++) {
			int tempExplosions = 1;
			radius = 1;
			int lastHayBale = hays[i].getPlace();
			int nextIndex = i - 1;
			while(nextIndex >= 0 && hays[nextIndex].getPlace() >= lastHayBale - radius) {
				while(nextIndex >= 0 && hays[nextIndex].getPlace() >= lastHayBale - radius) nextIndex--;
				if(nextIndex >= 0) {
					lastHayBale = hays[nextIndex + 1].getPlace();
					radius++;
				}
			}
			tempExplosions += i - nextIndex - 1;
			radius = 1;
			lastHayBale = hays[i].getPlace();
			nextIndex = i + 1;
			while(nextIndex < hays.length && hays[nextIndex].getPlace() <= lastHayBale + radius) {
				while(nextIndex < hays.length && hays[nextIndex].getPlace() <= lastHayBale + radius) nextIndex++;
				if(nextIndex < hays.length) {
					lastHayBale = hays[nextIndex - 1].getPlace();
					radius++;
				}
			}
			tempExplosions += nextIndex - i - 1;
			if(tempExplosions > maxExplosions) maxExplosions = tempExplosions;
			//System.out.println(tempExplosions);
		}
		
		System.out.println(maxExplosions);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		pw.write("" + maxExplosions);
		pw.close();
		
	}
	

}

class HayBale implements Comparable {
	int place;
	boolean exploded;
	public HayBale(int place) {
		this.place = place;
		exploded = false;
	}
	
	public int getPlace() {
		return place;
	}

	public boolean isExploded() {
		return exploded;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}

	@Override
	public int compareTo(Object o) {
		HayBale otherHay = (HayBale)o;
		
		if(place > otherHay.getPlace()) {
			return 1;
		}else if(place == otherHay.getPlace()) return 0;
		return -1;
	}
	
}