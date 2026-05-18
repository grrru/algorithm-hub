import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static String text;
	static String pattern;
	static int t;
	static int p;
	static int[] table;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		text = br.readLine();
		pattern = br.readLine();
		t = text.length();
		p = pattern.length();
		table = new int[p];
		
		piTable();
		sb = new StringBuilder();
		System.out.println(KMP());
		System.out.println(sb);
	}

	static void piTable() {
		int j = 0;
		
		for (int i = 1; i < p; i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = table[j - 1];
			}
			
			if (pattern.charAt(i) == pattern.charAt(j)) {
				j++;
				table[i] = j;
			}
		}
	}
	
	static int KMP() {
		int cnt = 0;
		int j = 0;
		
		for (int i = 0; i < t; i++) {
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = table[j - 1];
			}
			
			if (text.charAt(i) == pattern.charAt(j)) {
				if (j == p - 1) {
					cnt++;
					j = table[j];
					sb.append(i - p + 2).append(' ');
				}
				else {
					j++;
				}
			}
		}
		
		return cnt;
	}
	
}