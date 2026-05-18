import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	
	static int n;
	static int m;
	static int[][] arr;
	static boolean[][] visit;
	static int[] di = {1, 1, 1, 0, -1, -1, -1, 0};
	static int[] dj = {-1, 0, 1, 1, 1, 0, -1, -1};
		
	public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	arr = new int[n][m];
    	visit = new boolean[n][m];
    	
    	for (int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
    	}
    	
    	int res = 0;
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < m; j++) {
    			if (visit[i][j]) continue;
    			if (bfs(i, j)) res++;
    			
    			
    		}
    	}
    	System.out.println(res);
    }
	
	static boolean bfs(int i, int j) {
		if (!isTop(i, j)) return false;
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(i, j));
		
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int d = 0; d < 8; d++) {
				int ni = cur.x + di[d];
				int nj = cur.y + dj[d];
				if (!isBound(ni, nj)) continue;
				if (visit[ni][nj]) continue;
				if (arr[ni][nj] != arr[i][j]) continue;
				if (!isTop(ni, nj)) return false;
				
				queue.offer(new Point(ni, nj));
				visit[ni][nj] = true;
			}	
		}
		return true;
	}
	
	static boolean isTop(int i, int j) {
		for (int d = 0; d < 8; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if (!isBound(ni, nj)) continue;
			if (arr[ni][nj] > arr[i][j]) return false;
		}
		
		return true;
	}
	

	static boolean isBound(int ni, int nj) {
		return ni >= 0 && ni < n && nj >= 0 && nj < m;
	}
}