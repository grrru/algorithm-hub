import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new char[n][m];
		
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken()) - 1;
		int y1 = Integer.parseInt(st.nextToken()) - 1;
		int x2 = Integer.parseInt(st.nextToken()) - 1;
		int y2 = Integer.parseInt(st.nextToken()) - 1;
		
		for (int i = 0; i < n; i++) arr[i] = br.readLine().toCharArray();
		
		int ans = 0;
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x2, y2));
		boolean[][] visit = new boolean[n][m];
		visit[x2][y2] = true;
		
		run:
		while (true) {
			ans++;
			Queue<Point> rest = new ArrayDeque<>();
			
			while (!queue.isEmpty()) {
				Point cur = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int ni = cur.x + di[d];
					int nj = cur.y + dj[d];
					
					if (!bound(ni, nj) || visit[ni][nj]) continue;
					
					visit[ni][nj] = true;
					if (arr[ni][nj] == '0') queue.offer(new Point(ni, nj));
					else if (arr[ni][nj] == '1') rest.offer(new Point(ni, nj));
					else break run;
				}
			}
			
			while (!rest.isEmpty()) queue.offer(rest.poll());
		}
		
		System.out.println(ans);
	}
	
	static boolean bound(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}
}
