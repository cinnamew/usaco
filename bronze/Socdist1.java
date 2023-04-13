import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Socdist1 {

	public static void main(String[] args)throws IOException {
		/*14
		10001001000010*/
		BufferedReader br = new BufferedReader(new FileReader("socdist1.in"));
		int stallNum = Integer.parseInt(br.readLine());
		String stallsString = br.readLine();
		int[] stalls = new int[stallNum];
		for(int i = 0; i < stallNum; i++) {
			stalls[i] = Integer.parseInt(stallsString.charAt(i) + "");
		}
		
		int maxNum = 0;
		int secondMaxNum = 0;
		int previousCowIndex = 0;
		int firstCowIndex = 0;
		int lastCowIndex = 0;
		int minGapLength = Integer.MAX_VALUE;
		boolean noCows = false;
		boolean oneCow = false;
		
		//find the first cow index
		for(int j = 0; j < stallNum; j++) {
			if(stalls[j] == 1) {
				previousCowIndex = j;
				firstCowIndex = j;
				//maxNum = j;
				break;
			}
			if(j == stallNum - 1) {
				noCows = true;
			}
		}
		
		for(int i = previousCowIndex + 1; i < stallNum; i++) {
			if(stalls[i] == 1) {
				int dist = i - previousCowIndex;
				previousCowIndex = i;
				lastCowIndex = i;
				if(dist >= maxNum) {
					secondMaxNum = maxNum;
					maxNum = dist;
				}
				if(dist <= minGapLength) {
					minGapLength = dist;
				}
			}
		}
		
		if(previousCowIndex == firstCowIndex) {
			oneCow = true;
		}
		
		//System.out.println(maxNum + " " + secondMaxNum);
		//System.out.println(firstCowIndex);
		//System.out.println(minGapLength);
		//System.out.println(maxNum);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist1.out")));
		if(noCows == true) {
			System.out.println(stallNum-1);
			pw.write("" + (stallNum-1));
		}else if(oneCow == true) {
			//1 cow in front, 1 cow behind
			int answer1 = Math.min(stallNum - firstCowIndex - 1, firstCowIndex);
			
			//2 cows in larger gap
			int answer2 = Math.max(stallNum - firstCowIndex - 1, firstCowIndex)/2;
			
			int finalAnswer = Math.max(answer1, answer2);
			System.out.println(finalAnswer);
			pw.write("" + finalAnswer);
		}else {
			int answer1 = minGapLength;
			//2 cows in gap of first cow
			if((firstCowIndex)/2 < answer1) {
				answer1 = (firstCowIndex)/2;
			}
			//System.out.println(answer1);
			
			int answer2 = minGapLength;
			//1 cow in leftmost gap, 1 cow in rightmost gap
			int testing = Math.min((firstCowIndex), (stallNum - lastCowIndex - 1));
			if(testing < minGapLength) answer2 = testing;
			//System.out.println(answer2);
			
			//1 cow in leftmost gap, 1 cow in largest gap
			int answer3 = minGapLength;
			testing = Math.min((firstCowIndex), (maxNum)/2);
			if(testing < minGapLength) answer3 = testing;
			//System.out.println(answer3);
			
			//2 cows in largest gap
			int answer4 = minGapLength;
			testing = (maxNum)/3;
			if(testing < minGapLength) answer4 = testing;
			//System.out.println(answer4);
			
			//1 cow in largest gap, 1 cow in second largest
			int answer5 = minGapLength;
			testing = (secondMaxNum)/2;
			answer5 = Math.min(minGapLength, testing);
			//System.out.println(answer5);
			
			//1 cow in largest gap, 1 cow in rightmost gap
			int answer6 = minGapLength;
			testing = Math.min((maxNum)/2, (stallNum - lastCowIndex - 1));
			answer6 = Math.min(answer6, testing);
			//System.out.println(answer6);
			
			//2 cows in rightmost gap
			int answer7 = minGapLength;
			if((stallNum - lastCowIndex - 1)/2 < answer7) answer7 = (stallNum - lastCowIndex - 1)/2;
			//System.out.println(answer7);
			
			int finalAnswer = Math.max(answer1, answer2);
			finalAnswer = Math.max(finalAnswer, answer3);
			finalAnswer = Math.max(finalAnswer, answer4);
			finalAnswer = Math.max(finalAnswer, answer5);
			finalAnswer = Math.max(finalAnswer, answer6);
			finalAnswer = Math.max(finalAnswer, answer7);
			
			System.out.println(finalAnswer);
			pw.write("" + finalAnswer);
		}
		pw.flush();
	}
	
	public static int cowDist(int gapLength, int numCows) {
		if(numCows == 1) {
			return (gapLength - 1)/2;
		}
		return (gapLength - 2)/3;
	}

}
