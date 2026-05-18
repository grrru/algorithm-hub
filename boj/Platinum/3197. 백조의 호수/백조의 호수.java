import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static char[][] arr;
	static Point[] swan = new Point[2];
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new char[n][m];
		visit = new boolean[n][m];
		
		Queue<Point> queue = new ArrayDeque<>();
		Queue<Point> water = new ArrayDeque<>();
		
		// 1. 맵 정보 입력
		int sw = 0;
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == 'L') swan[sw++] = new Point(i, j);
				
				if (arr[i][j] != 'X') water.offer(new Point(i, j));
			}
		}
		
		queue.offer(swan[0]);
		visit[swan[0].x][swan[0].y] = true;
		
		int time = 0;
		int depth;
		
		run : while (true) {
			time++;
			depth = water.size();
			while (depth-- > 0) {
				Point p = water.poll();
				
				for (int d = 0; d < 4; d++) {
					int ni = p.x + di[d];
					int nj = p.y + dj[d];
					
					if (ni >= 0 && ni < n && nj >= 0 && nj < m && arr[ni][nj] == 'X') {
						arr[ni][nj] = '.';
						water.offer(new Point(ni, nj));
					}
				}
			}
			
			Queue<Point> next = new ArrayDeque<>();
			
			while (!queue.isEmpty()) {
				Point p = queue.poll();
				
				if (p.x == swan[1].x && p.y == swan[1].y) break run;
				
				for (int d = 0; d < 4; d++) {
					int ni = p.x + di[d];
					int nj = p.y + dj[d];
					
					if (ni >= 0 && ni < n && nj >= 0 && nj < m && !visit[ni][nj]) {
						visit[ni][nj] = true;
						
						if (arr[ni][nj] == 'X') {
							next.offer(new Point(ni, nj));
						} else {
							queue.offer(new Point(ni, nj));
						}
					}
				}
			}
			queue = next;
			
		}
		System.out.println(time);
	}
}