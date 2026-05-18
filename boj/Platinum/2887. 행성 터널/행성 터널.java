import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int [][] pts;
	static PriorityQueue<int[]> pq;
	static PriorityQueue<Edge> list;
	static int[] parent;
	static long res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		pts = new int[n][4];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pts[i][1] = Integer.parseInt(st.nextToken());
			pts[i][2] = Integer.parseInt(st.nextToken());
			pts[i][3] = Integer.parseInt(st.nextToken());
			pts[i][0] = i;
		}
		
		list = new PriorityQueue<>();
		
		for (int i = 1; i <= 3; i++) makeList(i);
		
		parent = new int[n];
		for (int i = 1; i < n; i++) parent[i] = i;
		
		int cnt = 0;
		res = 0l;
		
		while (!list.isEmpty()) {
			Edge cur = list.poll();
			if (!union(cur.u, cur.v)) continue;
			
			res += cur.w;
			if (++cnt == n - 1) break;
		}
		System.out.println(res);
		
	}
	
	static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int x = find(a);
		int y = find(b);
		
		if (x == y) return false;
		
		parent[x] = y;
		return true;
	}
	
	static void makeList(int t) {
		int[] cur;
		int[] next;
		
		pq = new PriorityQueue<>((o1, o2) -> {
			return o1[t] - o2[t];
		});
		for (int i = 0; i < n; i++) pq.add(pts[i]);
		
		cur = pq.poll();
		
		while (!pq.isEmpty()) {
			next = pq.poll();
			int u = cur[0];
			int v = next[0];
			int w = next[t] - cur[t];
			list.offer(new Edge(u, v, w));
			cur = next;
		}
	}
	static class Edge implements Comparable<Edge> {
		int u;
		int v;
		int w;
		
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
}
