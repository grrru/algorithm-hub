import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[] dist;
	static int[] weather;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		dist = new int[n + 1];
		dp  =new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			dist[i] = Integer.parseInt(br.readLine());
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		
		weather = new int[m + 1];
		for (int i = 1; i <= m; i++) weather[i] = Integer.parseInt(br.readLine());
		
		for (int j = 1; j <= m; j++) {
			for (int i = 1; i <= n; i++) {
				if (dp[i - 1][j - 1] == Integer.MAX_VALUE) continue;
				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + dist[i] * weather[j]);
				dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
			}
		}
		
		int ans = Integer.MAX_VALUE;
		for (int j = 1; j <= m; j++) {
			ans = Math.min(ans, dp[n][j]);
		}
		System.out.println(ans);
	}
}