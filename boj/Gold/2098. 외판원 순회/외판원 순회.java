import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] cost;
	static int[][] dp;
	// dp[next_pos][visited + next] = dp[cur_pos][visited] + cost[cur][next]
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		cost = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[n][1 << n];

		System.out.println(travel(0, 1));
	}
	static int travel(int cur, int visit) {
		
		if (dp[cur][visit] != 0) return dp[cur][visit];
		
		if (visit == (1 << n) - 1) {
			if (cost[cur][0] == 0) return 20000000;
			return dp[cur][visit] = cost[cur][0];
		}
		
		int res = 20000000;
		for (int i = 0; i < n; i++) {
			if (cur == i) continue;
			if (cost[cur][i] == 0) continue;
			if ((visit & (1 << i)) != 0) continue;
			res = Math.min(res, travel(i, visit | (1 << i)) + cost[cur][i]);
		}
		
		return dp[cur][visit] = res; 
	}
}