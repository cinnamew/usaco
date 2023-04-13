import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GetEven {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("geteven.in")));
		int possibilities = Integer.parseInt(br.readLine());
		Letter[] chars = new Letter[7];
		//B E S I G O M
		for(int i = 0; i < possibilities; i++) {
			String[] temp = br.readLine().split(" ");
			char c = temp[0].charAt(0);
			int index = getIndex(c);
			int value = Integer.parseInt(temp[1]);
			if(chars[index] == null) {
				chars[index] = new Letter(c, value);
			}else {
				chars[index].addValue(value);
			}
		}
		
		ArrayList<Integer> bessie = new ArrayList<Integer>();
		ArrayList<Integer> goes = new ArrayList<Integer>();
		ArrayList<Integer> moo = new ArrayList<Integer>();
		
		for(int b = 0; b < chars[0].getValueNum(); b++) {
			int bValue = chars[0].getValues().get(b);
			for(int e = 0; e < chars[1].getValueNum(); e++) {
				int eValue = chars[1].getValues().get(e);
				for(int s = 0; s < chars[2].getValueNum(); s++) {
					int sValue = chars[2].getValues().get(s);
					for(int s2 = 0; s2 < chars[2].getValueNum(); s2++) {
						int sValue2 = chars[2].getValues().get(s2);
						for(int i = 0; i < chars[3].getValueNum(); i++) {
							int iValue = chars[3].getValues().get(i);
							for(int e2 = 0; e2 < chars[1].getValueNum(); e2++) {
								int eValue2 = chars[1].getValues().get(e2);
								bessie.add(bValue + eValue + sValue + sValue2 + iValue + eValue2);
							}
						}
					}
				}
			}
		}
		
		for(int g = 0; g < chars[getIndex('G')].getValueNum(); g++) {
			int gValue = chars[getIndex('G')].getValues().get(g);
			for(int o = 0; o < chars[getIndex('O')].getValueNum(); o++) {
				int oValue = chars[getIndex('O')].getValues().get(o);
				for(int e = 0; e < chars[getIndex('E')].getValueNum(); e++) {
					int eValue = chars[getIndex('E')].getValues().get(e);
					for(int s = 0; s < chars[getIndex('S')].getValueNum(); s++) {
						int sValue = chars[getIndex('S')].getValues().get(s);
						goes.add(gValue + oValue + eValue + sValue);
					}
				}
			}
		}
		
		for(int m = 0; m < chars[getIndex('M')].getValueNum(); m++) {
			int mValue = chars[getIndex('M')].getValues().get(m);
			for(int o = 0; o < chars[getIndex('O')].getValueNum(); o++) {
				int oValue = chars[getIndex('O')].getValues().get(o);
				for(int o2 = 0; o2 < chars[getIndex('O')].getValueNum(); o2++) {
					int oValue2 = chars[getIndex('O')].getValues().get(o2);
					moo.add(mValue + oValue + oValue2);
				}
			}
		}
		
		int ans = 0;
		for(int b = 0; b < bessie.size(); b++) {
			int bValue = bessie.get(b);
			for(int g = 0; g < goes.size(); g++) {
				int gValue = goes.get(g);
				for(int m = 0; m < moo.size(); m++) {
					int mValue = moo.get(m);
					int product = bValue*gValue*mValue;
					if(product%2 == 0) {
						ans++;
					}
				}
			}
		}
		
		System.out.println(ans);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("geteven.out")));
		pw.write("" + ans);
		pw.close();
	}
	
	public static int getIndex(char c) {
		int ret = 0;
		switch(c) {
		case 'B': 
			ret = 0;
			break;
		case 'E':
			ret = 1;
			break;
		case 'S':
			ret = 2;
			break;
		case 'I':
			ret = 3;
			break;
		case 'G':
			ret = 4;
			break;
		case 'O':
			ret = 5;
			break;
		case 'M':
			ret = 6;
			break;
		default:
			ret = 0;
			break;
		}
		return ret;
	}

}

class Letter {
	char letter = 'a';
	ArrayList<Integer> values = new ArrayList<Integer>();
	
	public Letter(char letter, int value) {
		this.letter = letter;
		values.add(value);
	}
	
	public void addValue(int value) {
		values.add(value);
	}
	
	public int getValueNum() {
		return values.size();
	}
	
	public ArrayList<Integer> getValues() {
		return values;
	}
	
	public char getChar() {
		return letter;
	}
	
}