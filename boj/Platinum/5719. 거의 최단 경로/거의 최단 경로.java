import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int s;
	static int d;
	static PriorityQueue<Edge> pq;
	static List<Edge>[] graph;
	static boolean[][] used;
	static boolean[] visit;
	static int[] dp;
	static List<Integer>[] list;
	static final int MAX = 5_000_000;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		pq = new PriorityQueue<>();

		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			if (n == 0 && m == 0)
				break;

			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());

			graph = new List[n];
			list = new List[n];

			for (int i = 0; i < n; i++) {
				graph[i] = new ArrayList<>();
				list[i] = new LinkedList<>();
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				graph[u].add(new Edge(v, w));
			}

			used = new boolean[n][n];

			pq.clear();
			pq.offer(new Edge(s, 0));
			visit = new boolean[n];
			dp = new int[n];
			Arrays.fill(dp, MAX);
			dp[s] = 0;

			while (!pq.isEmpty()) {
				Edge cur = pq.poll();

				if (visit[cur.to])
					continue;
				visit[cur.to] = true;

				for (Edge next : graph[cur.to]) {
					if (dp[next.to] > dp[cur.to] + next.w) {
						dp[next.to] = dp[cur.to] + next.w;
						pq.offer(new Edge(next.to, dp[next.to]));

						list[next.to].clear();
						list[next.to].add(cur.to);

					} else if (dp[next.to] == dp[cur.to] + next.w) {
						list[next.to].add(cur.to);
					}
				}
			}

			dfs(list, new boolean[n], d);

			pq.clear();
			pq.offer(new Edge(s, 0));
			visit = new boolean[n];
			Arrays.fill(dp, MAX);
			dp[s] = 0;

			while (!pq.isEmpty()) {
				Edge cur = pq.poll();
				if (visit[cur.to])
					continue;
				visit[cur.to] = true;

				for (Edge next : graph[cur.to]) {
					if (used[cur.to][next.to])
						continue;
					if (dp[next.to] > dp[cur.to] + next.w) {
						dp[next.to] = dp[cur.to] + next.w;
						pq.offer(new Edge(next.to, dp[next.to]));
					}
				}
			}

			sb.append(dp[d] == MAX ? -1 : dp[d]).append('\n');
		}

		System.out.println(sb);
	}

	static void dfs(List<Integer>[] list, boolean[] visit, int cur) {
		if (visit[cur]) return;
		visit[cur] = true;

		for (int next : list[cur]) {
			used[next][cur] = true;
			if (visit[next]) continue;

			dfs(list, visit, next);
		}
	}

	static class Edge implements Comparable<Edge> {
		int to;
		int w;

		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
}