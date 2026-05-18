import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[] remain;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());

		remain = new int[m];
		
		int c = 0;
		for (int i = 1; i <= n; i++) {
			c = (c + Integer.parseInt(st.nextToken())) % m;
			remain[c]++;
		}
		remain[0]++;
		
		long cnt = 0;
		for (int i = 0; i < m; i++) {
			int k = remain[i];
			cnt += (long) k * (k - 1) >> 1;
		}
		System.out.println(cnt);
	}
}