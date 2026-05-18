import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int t;
	static int[][] arr;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[][][] visit = new int[n][m][2];
		visit[0][0][0] = 1;
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(0, 0, 0));
		
		int safe = 0;
		
		run:
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			if (visit[cur.x][cur.y][cur.king] == t + 1) continue;
			
			for (int d = 0; d < 4; d++) {
				int ni = cur.x + di[d];
				int nj = cur.y + dj[d];
				if (!isBound(ni, nj)) continue;
				
				if (ni == n - 1 && nj == m - 1) {
					safe = visit[cur.x][cur.y][cur.king];
					break run;
				}
				
				if (arr[ni][nj] == 1) {
					if (cur.king == 0) continue;
					if (visit[ni][nj][1] != 0) continue;
					visit[ni][nj][1] = visit[cur.x][cur.y][1] + 1;
					queue.offer(new Point(ni, nj, 1));
				} else if (arr[ni][nj] == 0) {
					if (visit[ni][nj][cur.king] != 0) continue;
					visit[ni][nj][cur.king] = visit[cur.x][cur.y][cur.king] + 1;
					queue.offer(new Point(ni, nj, cur.king));
				} else {
					if (visit[ni][nj][0] != 0 || visit[ni][nj][1] != 0) continue;
					visit[ni][nj][0] = visit[cur.x][cur.y][cur.king] + 1;
					visit[ni][nj][1] = visit[ni][nj][0];
					queue.offer(new Point(ni, nj, 1));
				}
			}
		}
		
		System.out.println(safe == 0 ? "Fail" : safe);
	}
	
	static class Point {
		int x;
		int y;
		int king;
		
		public Point(int x, int y, int king) {
			this.x = x;
			this.y = y;
			this.king = king;
		}
	}
	
	static boolean isBound(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}
}