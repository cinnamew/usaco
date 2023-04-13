import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Socdist2 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("socdist2.in"));
		int cowNum = Integer.parseInt(br.readLine());
		
		SocDistCows[] cowLine = new SocDistCows[cowNum];
		
		for(int i = 0; i < cowNum; i++) {
			String[] temp = br.readLine().split(" ");
			cowLine[i] = new SocDistCows(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
		}
		
		Arrays.sort(cowLine);
		
		int infectDist = Integer.MAX_VALUE;
		
		for(int i = 0; i < cowNum - 1; i++) {
			if(cowLine[i + 1].getPosition() - cowLine[i].getPosition() < infectDist && ((cowLine[i].getInfected() && !cowLine[i + 1].getInfected()) || (cowLine[i + 1].getInfected() && !cowLine[i].getInfected()))) {
				infectDist = cowLine[i + 1].getPosition() - cowLine[i].getPosition();
			}
		}
		
		infectDist--;
		
		int patientZeroes = 0;
		boolean lastCowHealthy = true;
		
		for(int i = 0; i < cowNum - 1; i++) {
			if(!cowLine[i].getInfected()) {
				lastCowHealthy = true;
				if(i + 1 == cowNum - 1) {
					if(cowLine[i + 1].getInfected()) {
						patientZeroes++;
					}
				}
			}
			if(cowLine[i].getInfected()) {
				if(cowLine[i + 1].getInfected()) {
					if(cowLine[i + 1].getPosition() - cowLine[i].getPosition() > infectDist) {
						patientZeroes++;
					}
				}
				if(lastCowHealthy) patientZeroes++;
				lastCowHealthy = false;
			}
		}
		
		System.out.println(patientZeroes);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist2.out")));
		pw.write("" + patientZeroes);
		pw.flush();
		
	}

}

class SocDistCows implements Comparable {
	int position;
	boolean infected;
	
	public SocDistCows(int pos, int infect) {
		position = pos;
		if(infect == 1) infected = true;
		else infected = false;
	}
	
	public int getPosition() {
		return position;
	}
	
	public boolean getInfected() {
		return infected;
	}

	@Override
	public int compareTo(Object otherCow) {
		if(position < ((SocDistCows)otherCow).getPosition()) {
			return -1;
		}else if(position == ((SocDistCows)otherCow).getPosition()) {
			return 0;
		}
		return 1;
	}
}