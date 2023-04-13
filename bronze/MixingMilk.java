import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MixingMilk {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new  FileReader (new  File("mixmilk.in")));
		Bucket[] buckets = new Bucket[3];
		for(int i = 0; i < 3; i++) {
			String[] temp = br.readLine().split(" ");
			buckets[i] = new Bucket(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
		}
		
		for(int i = 1; i <= 100; i++) {
			Bucket moveFrom = buckets[0];
			Bucket moveTo = buckets[1];
			if(i%3 == 2) {	//moving 2 -> 3
				moveFrom = buckets[1];
				moveTo = buckets[2];
			}else if(i%3 == 0) {
				moveFrom = buckets[2];
				moveTo = buckets[0];
			}
			int milkMoved = Math.min(moveFrom.getCurrent(), moveTo.getCapacity() - moveTo.getCurrent());
			moveFrom.setCurrent(moveFrom.getCurrent() - milkMoved);
			moveTo.setCurrent(moveTo.getCurrent() + milkMoved);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
		
		for(int i = 0; i < 3; i++) {
			System.out.println(buckets[i].getCurrent());
			pw.println("" + buckets[i].getCurrent());
		}
		
		pw.close();
		
	}
	
}

class Bucket {
	int capacity;
	int current;
	public Bucket(int cap, int curr) {
		capacity = cap;
		current = curr;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getCurrent() {
		return current;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
}
