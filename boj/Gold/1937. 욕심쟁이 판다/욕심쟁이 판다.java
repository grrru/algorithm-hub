import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] cost;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static int[][] dp;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		cost = new int[n][n];
		
		dp = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = 1;
			}
		}
		
		
		ans = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ans = Math.max(ans, dfs(i, j));
			}
		}
		
		System.out.println(ans);
	}
	static int dfs(int x, int y) {
		if (dp[x][y] != 1) return dp[x][y];
		
		for (int d = 0; d < 4; d++) {
			int nx = x + di[d];
			int ny = y + dj[d];
			if (!bound(nx, ny)) continue;
			if (cost[nx][ny] <= cost[x][y]) continue;
			
			dp[x][y] = Math.max(1 + dfs(nx, ny), dp[x][y]);
		}
		
		return dp[x][y];
	}
	
	static boolean bound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < n;
	}
}
