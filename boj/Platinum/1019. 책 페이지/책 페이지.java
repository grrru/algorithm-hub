import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int n;
	static int[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		count = new int[10];
	
		int weight = 1;
		
		while (n > 0) {
			while (n >= 0 && n % 10 != 9) {
				String str = Integer.toString(n);
				for (int i = 0; i < str.length(); i++) {
					count[str.charAt(i) - '0'] += weight;
				}
				n--;
			}
			
			int line = (n + 1) / 10;
			
			for (int i = 0; i <= 9; i++) {
				count[i] += line * weight;
			}
			
			count[0] -= weight;
			
			n /= 10;
			weight *= 10;
		}

		for (int i = 0; i <= 9; i++) sb.append(count[i]).append(' ');
		System.out.println(sb);
		
	}
}
