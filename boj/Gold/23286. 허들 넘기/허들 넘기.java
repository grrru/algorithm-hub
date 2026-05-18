import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int t;
	static int[][] dist;
	static final int INF = 10_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		dist = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) Arrays.fill(dist[i], INF);
		
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			dist[s][e] = Math.min(dist[s][e], d);
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == k || j == k || i == j) continue;
					
					if (dist[i][k] == INF || dist[k][j] == INF) continue;
					
					if (dist[i][j] == INF) dist[i][j] = Math.max(dist[i][k], dist[k][j]);
					else {
						dist[i][j] = Math.min(dist[i][j], Math.max(dist[i][k], dist[k][j]));
					}					
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			sb.append(dist[s][e] == INF ? -1 : dist[s][e]).append('\n');
		}
		System.out.println(sb);
	}
}
