import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpeedingTicket {

	public static void main(String[] args)throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new  FileReader (new  File("speeding.in")));
		String[] temp = br.readLine().split(" ");
		int numRoads = Integer.parseInt(temp[0]);
		int numSpeeds = Integer.parseInt(temp[1]);
		Speed[] roads = new Speed[numRoads];
		Speed[] speeds = new Speed[numSpeeds];
		for(int i = 0; i < numRoads; i++) {
			String[] temp2 = br.readLine().split(" ");
			int pos = Integer.parseInt(temp2[0]);
			if(i != 0) pos += roads[i - 1].getPos();
			roads[i] = new Speed(pos, Integer.parseInt(temp2[1]));
		}
		for(int i = 0; i < numSpeeds; i++) {
			String[] temp2 = br.readLine().split(" ");
			int pos = Integer.parseInt(temp2[0]);
			if(i != 0) pos += speeds[i - 1].getPos();
			speeds[i] = new Speed(pos, Integer.parseInt(temp2[1]));
		}
		
		ArrayList<SpeedButBetter> combined = new ArrayList<>();
		for(int i = 0; i < numRoads; i++) {
			Speed lim = roads[i];
			int bes = findSpeed(speeds, roads[i].getPos());
			combined.add(new SpeedButBetter(lim.getPos(), bes, lim.getSpeed()));
		}
		for(int i = 0; i < numSpeeds; i++) {
			Speed bes = speeds[i];
			int lim = findSpeed(roads, speeds[i].getPos());
			combined.add(new SpeedButBetter(bes.getPos(), bes.getSpeed(), lim));
		}
		
		Collections.sort(combined);
		//System.out.println(Math.max(0, combined.get(combined.size() - 1).getBessieSpeed() - combined.get(combined.size() - 1).getSpeedLimit()));
		
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));
		pw.write("" + Math.max(0, combined.get(combined.size() - 1).getBessieSpeed() - combined.get(combined.size() - 1).getSpeedLimit()));
		pw.close();
	}
	
	public static int findSpeed(Speed[] findFrom, int targetPos) {
		int low = 0;
		int high = findFrom.length - 1;
		if(targetPos <= findFrom[0].getPos()) return findFrom[0].getSpeed();
		while(low != high - 1) { //correct?
			int mid = (low + high)/2;
			if(findFrom[mid].getPos() > targetPos) {
				high = mid;
			}else if(findFrom[mid].getPos() < targetPos) {
				low = mid;
			}else {
				return findFrom[mid].getSpeed();
			}
		}
		return findFrom[high].getSpeed();
	}

}

class SpeedButBetter implements Comparable {
	int pos;
	int bessieSpeed;
	int speedLimit;
	int diff;
	
	public SpeedButBetter(int pos, int bessieSpeed, int speedLimit) {
		this.pos = pos;
		this.bessieSpeed = bessieSpeed;
		this.speedLimit = speedLimit;
		diff = bessieSpeed - speedLimit;
	}
	public int getPos() {
		return pos;
	}
	public int getBessieSpeed() {
		return bessieSpeed;
	}
	public int getSpeedLimit() {
		return speedLimit;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public void setBessieSpeed(int bessieSpeed) {
		this.bessieSpeed = bessieSpeed;
	}
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
	}
	
	@Override
	public int compareTo(Object o) {
		SpeedButBetter other = (SpeedButBetter)o;
		int diff = bessieSpeed - speedLimit;
		int otherDiff = other.getBessieSpeed() - other.getSpeedLimit();
		if(diff < otherDiff) return -1;
		else if(diff > otherDiff) return 1;
		return 0;
	}
	
}

class Speed {
	int pos;
	int speed;
	public Speed(int p, int s) {
		pos = p;
		speed = s;
	}
	public int getPos() {
		return pos;
	}
	public int getSpeed() {
		return speed;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}