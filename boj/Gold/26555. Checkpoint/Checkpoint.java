import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int k;
	static char[][] arr;
	static int[][] visit;
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			Queue<Point> queue = new ArrayDeque<>();

			arr = new char[n][m];
			int x = 0;
			int y = 0;

			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < m; j++) {
					arr[i][j] = str.charAt(j);
					if (arr[i][j] == 'S') {
						x = i;
						y = j;
					}
				}
			}

			int path = 0;

			int target;
			if (k == 0)
				target = 10;
			else
				target = 1;

				while (target != 11) {
				visit = new int[n][m];
				queue.clear();
				queue.offer(new Point(x, y));
				visit[x][y] = 1;
				run: while (!queue.isEmpty()) {
					Point next = queue.poll();
					for (int d = 0; d < 4; d++) {
						int ni = di[d] + next.x;
						int nj = dj[d] + next.y;
						if (!isBound(ni, nj))
							continue;
						if (visit[ni][nj] != 0)
							continue;
						if (arr[ni][nj] == '#')
							continue;

						if (arr[ni][nj] == (target == 10 ? 'E' : target + '0')) {
							// System.out.println("target = " + target);
							// System.out.println("path = " + path);
							// System.out.println(ni + " " + nj);
							path += visit[next.x][next.y];
							
							target = (target == k ? 10 : target + 1);
							x = ni;
							y = nj;
							break run;
						}

						visit[ni][nj] = visit[next.x][next.y] + 1;
						queue.offer(new Point(ni, nj));
					}
				}
			}

			sb.append(path).append('\n');
		}

		System.out.println(sb);
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static boolean isBound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
}
