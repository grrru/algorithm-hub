import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[c + 100];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			for (int j = p; j <= c + 99; j++) {
				if (dp[j - p] == Integer.MAX_VALUE) continue;
				dp[j] = Math.min(dp[j], dp[j - p] + cost);
			}
		}
		
		int ans = Integer.MAX_VALUE;
		for (int i = c; i <= c + 99; i++) {
			ans = Math.min(ans, dp[i]);
		}
		
		System.out.println(ans);
	}
}
