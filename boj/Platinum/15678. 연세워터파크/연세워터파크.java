import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int d;
	static int[] arr;
	static long[] dp;
	static long ans = Long.MIN_VALUE;
	static PriorityQueue<Node> pq;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[n + 1];
		for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());

		dp = new long[n + 1];
		pq = new PriorityQueue<>();

		for (int i = 1; i <= n; i++) {
			dp[i] = findMax(i);
			ans = Math.max(dp[i], ans);
		}

		System.out.println(ans);
	}

	static long findMax(int cur) {

		while (!pq.isEmpty()) {
			Node c = pq.poll();
			if (c.idx + d < cur) continue;

			if (c.w < 0) {
				dp[cur] = arr[cur];
			} else {
				dp[cur] = c.w + arr[cur];
			}

			pq.offer(c);
			pq.offer(new Node(cur, dp[cur]));
			return dp[cur];
		}

		pq.offer(new Node(cur, arr[cur]));

		return arr[cur];
	}

	static class Node implements Comparable<Node> {
		long idx;
		long w;

		public Node(int idx, long w) {
			this.idx = idx;
			this.w = w;
		}

        @Override
        public int compareTo(Node o) {
            return Long.compare(o.w, this.w);
        }
	}
}