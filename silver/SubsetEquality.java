import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class SubsetEquality {


	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        char[] t = br.readLine().toCharArray();
        ArrayList<Integer>[] sArr = new ArrayList[18];
        ArrayList<Integer>[] tArr = new ArrayList[18];

        for(int i = 0; i < 18; i++) {
            sArr[i] = new ArrayList<Integer>();
            tArr[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < s.length; i++) {
            char c = s[i];
            sArr[(int)c - 97].add(i);
        }        
        for(int i = 0; i < t.length; i++) {
            char c = t[i];
            tArr[(int)c - 97].add(i);
        }

		int numQueries = Integer.parseInt(br.readLine());
		for(int i = 0; i < numQueries; i++) {
			TreeSet<Integer> tA = new TreeSet<Integer>(), tB = new TreeSet<>();
            char[] query = br.readLine().toCharArray();

            for(char c : query) {
                //add all indices of c s & t to tA/tB
                tA.addAll(sArr[(int)c - 97]);
                tB.addAll(tArr[(int)c - 97]);

            }
			
			//System.out.println(tA.first().equals(tB.first()));

			if(tA.size() != tB.size()) {
				System.out.print("N");
				continue;
			}

			boolean works = true;
            for(int j = 0; j < tA.size(); j++) {
                if(s[tA.pollFirst()] != t[tB.pollFirst()]) {
                    works = false;
                    break;
                }
            }
			//need to make this less than N
			
//			if(!tA.equals(tB)) {
//				System.out.print("N");
//			}
//			else System.out.print("Y");
			
			if(works) System.out.print("Y");
			else System.out.print("N");
		}

		br.close();
	}

}

class SubsetCharacter implements Comparable<SubsetCharacter> {
	char chara;
	int index;
	
	
	public SubsetCharacter(char chara, int index) {
		this.chara = chara;
		this.index = index;
	}
	
	public char getChara() {
		return chara;
	}
	public int getIndex() {
		return index;
	}
	public void setChara(char chara) {
		this.chara = chara;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(((SubsetCharacter)obj).getChara() == chara) return true;
		return false;
	}

	@Override
	public int compareTo(SubsetCharacter o) {
		return index - o.index;
	}
	
}
