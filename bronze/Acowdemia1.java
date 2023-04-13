import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Acowdemia1 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int numPapers = Integer.parseInt(temp[0]);
		int numCites = Integer.parseInt(temp[1]);
		
		int[] papers = new int[numPapers];
		String[] temp2 = br.readLine().split(" ");
		for(int i = 0; i < numPapers; i++) {
			papers[i] = Integer.parseInt(temp2[i]);
		}
		
		Arrays.sort(papers);
		int lowerBound = 0;
		int upperBound = numPapers - 1;
		
		int hIndex = 0;
		
		while(upperBound >= lowerBound) {
            int middleIndex = (lowerBound + upperBound)/2;
            int middle = papers[middleIndex];
            if(middle == papers.length - middleIndex) {
                hIndex = middle;
                break;
            }
            if(middle > papers.length - middleIndex) {
                upperBound = middleIndex - 1;
            }else {
                lowerBound = middleIndex + 1;
            }
        }
		
		if(hIndex != papers.length - (lowerBound + upperBound)/2) {
			hIndex = papers.length - upperBound - 1;
		}
		
		
		/*while(numCites > 0) {
			
		}*/
		
		int testing = hIndex + 1;
		int finalIndex = numPapers - 1;
		//System.out.println(finalIndex);
		boolean cited = false;
		for(int i = finalIndex; i >= 0; i--) {
			if(numCites == 0) {
					finalIndex = i;
					break;
				}
			if(papers[i] + 1 == testing) {
				papers[i]++;
				numCites--;
				cited = true;
				finalIndex = i - 1;
			}
		}
		//System.out.println(finalIndex);
		if(papers.length - finalIndex > testing && cited) {
			System.out.println(testing);
		}else {
			System.out.println(hIndex);
		}
		
		/*for(int i = (lowerBound + upperBound)/2; i >= 0; i--) {
			if(papers[i] == hIndex - 1 && numCites > 0) {
				System.out.println(hIndex + 1);
				return;
			}else if(papers[i] < hIndex - 1) {
				System.out.println(hIndex);
				return;
			}
		}
		System.out.println(hIndex);*/
		
		/*if(papers.length != 0 && papers[(lowerBound + upperBound)/2 - 1] == hIndex - 1 && numCites > 0) {
			System.out.println(hIndex + 1);
		}else {
			System.out.println(hIndex);
		}*/
		
		
	}

}
