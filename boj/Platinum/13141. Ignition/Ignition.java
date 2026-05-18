import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int[][] dist;
	static final int MAX = Integer.MAX_VALUE;

	static int[][] edges;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		dist = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = i; j <= n; j++) {
				if (i == j) dist[i][j] = 0;
				else dist[i][j] = dist[j][i] = MAX;
			}
		}

		edges = new int[m + 1][3];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges[i + 1][0] = u;
			edges[i + 1][1] = v;
			edges[i + 1][2] = w;

			dist[u][v] = dist[v][u] = Math.min(dist[u][v], w);
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == k || j == k || i == j) continue;

					if (dist[i][k] == MAX || dist[k][j] == MAX) continue;

					dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
					dist[j][i] = dist[i][j];
				}
			}
		}

		double ans = Double.MAX_VALUE;

		for (int i = 1; i <= n; i++) {

			double res = 0;

			for (int j = 1; j <= m; j++) {

				int u = edges[j][0];
				int v = edges[j][1];
				int w = edges[j][2];

				res = Math.max(res, (dist[i][u] + dist[i][v] + (double)w) / 2);
			}

			ans = Math.min(ans, res);
		}

		System.out.println(ans);
	}
}