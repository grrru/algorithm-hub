import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int[][] arr;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) arr[i][j] = str.charAt(j) - '0';
		}
		
		parent = new int[n * m];
		for (int i = 0; i < parent.length; i++) parent[i] = i;
		
		Queue<Point> queue = new ArrayDeque<>();
		int[] count = new int[n * m];
		boolean[][] visit = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1) continue;
				if (visit[i][j]) continue;
				
				int p = find(i * m + j);
				
				queue.offer(new Point(i, j));
				visit[i][j] = true;
				int cnt = 0;
				
				while (!queue.isEmpty()) {
					Point cur = queue.poll();
					cnt++;
					
					for (int d = 0; d < 4; d++) {
						int ni = cur.x + di[d];
						int nj = cur.y + dj[d];
						
						if (!isBound(ni, nj)) continue;
						if (arr[ni][nj] == 1) continue;
						if (visit[ni][nj]) continue;
						visit[ni][nj] = true;
						
						if (union(p, ni * m + nj)) {
							queue.offer(new Point(ni, nj));
						}
					}
				}
				count[find(i * m + j)] = cnt;
			}
		}

		StringBuilder sb = new StringBuilder();
		Set<Integer> set = new HashSet<>();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0) {
					sb.append(0);
					continue;
				}
				
				int k = 1;
				
				for (int d = 0; d < 4; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					if (!isBound(ni, nj)) continue;
					
					int p = find(ni * m + nj);
					if (set.contains(p)) continue;
					set.add(p);
					k += count[p];
				}
				sb.append(k % 10);
				set.clear();
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static boolean isBound(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
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
}