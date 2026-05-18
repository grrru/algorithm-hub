import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static char[][] arr;

	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};

	static int ans;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new char[n][m];
		for (int i = 0; i < n; i++) arr[i] = br.readLine().toCharArray();

		Queue<Point> queue = new ArrayDeque<>();
		int[][] visit;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 'W') continue;
				queue.clear();
				queue.offer(new Point(i, j));
				visit = new int[n][m];
				int res = 1;
				visit[i][j] = 1;

				while (!queue.isEmpty()) {
					Point cur = queue.poll();
					for (int d = 0; d < 4; d++) {
						int ni = di[d] + cur.x;
						int nj = dj[d] + cur.y;

						if (!isBound(ni, nj)) continue;
						if (arr[ni][nj] == 'W') continue;
						if (visit[ni][nj] != 0) continue;

						visit[ni][nj] = visit[cur.x][cur.y] + 1;
						res = Math.max(res, visit[ni][nj]);
						queue.offer(new Point(ni, nj));
					}
				}
				ans = Math.max(ans, res);
			}
		}
		System.out.println(ans - 1);
	}

	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static boolean isBound(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}
}
