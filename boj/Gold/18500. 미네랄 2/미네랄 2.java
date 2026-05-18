import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static char[][] arr;
	static int t;
	static int h;
	static int mineral;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new char[n][m];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == 'x') mineral++;
			}
		}

		t = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < t; i++) {
			h = Integer.parseInt(st.nextToken());
			shoot(i, h);
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	static void shoot(int i, int h) {
		h = n - h;
		
		if (i % 2 == 0) {
			for (int j = 0; j < m; j++) {
				if (arr[h][j] == 'x') {
					arr[h][j] = '.';
					mineral--;
					
					for (int d = 0; d < 4; d++) {
						int ni = h + di[d];
						int nj = j + dj[d];
						if (!isBound(ni, nj)) continue;
						if (arr[ni][nj] == '.') continue;
						
						if (yield(ni, nj)) break;
					}
					
					break;
				}
			}
		} else {
			for (int j = m - 1; j >= 0; j--) {
				if (arr[h][j] == 'x') {
					arr[h][j] = '.';
					mineral--;
					
					for (int d = 0; d < 4; d++) {
						int ni = h + di[d];
						int nj = j + dj[d];
						if (!isBound(ni, nj)) continue;
						if (arr[ni][nj] == '.') continue;
						
						if (yield(ni, nj)) break;
					}
					break;
				}
			}
		}
	}
	
	static boolean yield(int i, int j) {
		
		if (i == n - 1) return false;
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(i, j));
		
		boolean[][] visit = new boolean[n][m];
		List<Point> fall = new ArrayList<>();

		visit[i][j] = true;
		fall.add(new Point(i, j));

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int ni = p.x + di[d];
				int nj = p.y + dj[d];
				if (!isBound(ni, nj)) continue;
				if (arr[ni][nj] == '.') continue;
				if (visit[ni][nj]) continue;
				
				if (ni == n - 1) return false;
				
				visit[ni][nj] = true;
				Point pt = new Point(ni, nj);
				fall.add(pt);
				queue.offer(pt);
			}
		}
		
		Collections.sort(fall, (o1, o2) -> {
			return o2.x - o1.x;
		});
		
		int h = 1;
		while (true) {
			boolean flag = false;
			for (Point p : fall) {
				if (p.x + h == n || (!visit[p.x + h][p.y] && arr[p.x + h][p.y] == 'x')) {
					flag = true;
					break;
				}
			}
			if (flag) break;
			h++;
		}
		
		if (h == 1) return true;
		
		for (Point p : fall) {
			arr[p.x + h - 1][p.y] = 'x';
			arr[p.x][p.y] = '.'; 
		}
		
		return true;
	}
	static boolean isBound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
}