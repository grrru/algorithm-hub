import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int hx = Integer.parseInt(st.nextToken()) - 1;
		int hy = Integer.parseInt(st.nextToken()) - 1;
		
		st = new StringTokenizer(br.readLine());
		int ex = Integer.parseInt(st.nextToken()) - 1;
		int ey = Integer.parseInt(st.nextToken()) - 1;
		
		int[][] arr = new int[n][m];
		boolean[][][] visit = new boolean[n][m][2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) arr[i][j] = Integer.parseInt(st.nextToken());	
		}
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(hx, hy, 0, 0));
		visit[hx][hy][0] = true;
		
		int ans = -1;
		
		run:
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int ni = cur.x + di[d];
				int nj = cur.y + dj[d];
				
				if (!isBound(ni, nj)) continue;
				
				if (ni == ex && nj == ey) {
					ans = cur.cnt + 1;
					break run;
				}
				
				if (arr[ni][nj] == 0) {
					if (visit[ni][nj][cur.used]) continue;
					
					visit[ni][nj][cur.used] = true;
					queue.offer(new Point(ni, nj, cur.used, cur.cnt + 1));
				} else {
					if (cur.used == 1) continue;
					if (visit[ni][nj][1]) continue;
					
					visit[ni][nj][1] = true;
					queue.offer(new Point(ni, nj, 1, cur.cnt + 1));
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean isBound(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}
	
	static class Point {
		int x;
		int y;
		int used;
		int cnt;
		
		public Point (int x, int y, int used, int cnt) {
			this.x = x;
			this.y = y;
			this.used = used;
			this.cnt = cnt;
		}
	}
}
