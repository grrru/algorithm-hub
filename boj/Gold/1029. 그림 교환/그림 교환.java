import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int n;
	static int[][] dp;
	static int[][] cost;
	static int ans = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		cost = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			String str = br.readLine();
			for (int j = 1; j <= n; j++) {
				cost[i][j] = str.charAt(j - 1) - '0';
			}
		}
		
		dp = new int[n + 1][1 << n];
		
		dfs(1, 1, 1);
		System.out.println(ans);
	}
	static void dfs(int artist, int visit, int cnt) {
		
		if (visit == 1 << n - 1) return;
		if (ans == n) return;
		
		ans = Math.max(cnt, ans);
		
		for (int next = 1; next <= n; next++) {
			if (next == artist) continue;
			if (cost[artist][next] < dp[artist][visit]) continue;
			
			if ((visit & 1<<(next - 1)) != 0) continue;
			
			if (dp[next][visit | 1<<(next - 1)] != 0 && dp[next][visit | 1<<(next - 1)] <= cost[artist][next]) continue;
			
			dp[next][visit | 1<<(next - 1)] = cost[artist][next];
			dfs(next, visit | 1<<(next - 1), cnt + 1);
		}
	}
}
