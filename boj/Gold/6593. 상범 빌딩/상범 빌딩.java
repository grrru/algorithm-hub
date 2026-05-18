import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	
	static char[][][] arr;
	static int l;
	static int n;
	static int m;
	
	static int[] di = {1, -1, 0, 0, 0, 0};
	static int[] dj = {0, 0, 1, -1, 0, 0};
	static int[] ds = {0, 0, 0, 0, 1, -1};
		
	public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	StringBuilder sb = new StringBuilder();
    	while (true) {
    		st = new StringTokenizer(br.readLine());
    		
    		l = Integer.parseInt(st.nextToken());
    		n = Integer.parseInt(st.nextToken());
    		m = Integer.parseInt(st.nextToken());
    		
    		if (l == 0 && n == 0 && m == 0) break;
    		arr = new char[l][n][m];
    		int[][][] visit = new int[l][n][m];
    		
    		Queue<Point> queue = new ArrayDeque<>();
    		for (int s = 0; s < l; s++) {
    			for (int i = 0; i < n; i++) {
    				String str = br.readLine();
    				for (int j = 0; j < m; j++) {
    					arr[s][i][j] = str.charAt(j);
    					
    					if (arr[s][i][j] == 'S') {
    						queue.offer(new Point(s, i, j));
    						visit[s][i][j] = 1;
    					}
    				}
    			}
    			br.readLine();
    		}
    		
    		int cnt = -1;
    		
    		run:
    		while (!queue.isEmpty()) {
    			Point cur = queue.poll();
    			
    			for (int d = 0; d < 6; d++) {
    				int ni = cur.x + di[d];
    				int nj = cur.y + dj[d];
    				int ns = cur.s + ds[d];
    				
    				if (!isBound(ni, nj, ns)) continue;
    				if (arr[ns][ni][nj] == '#') continue;
    				if (visit[ns][ni][nj] != 0) continue;
    				
    				if (arr[ns][ni][nj] == 'E') {
    					cnt = visit[cur.s][cur.x][cur.y];
    					break run;
    				}
    				
    				visit[ns][ni][nj] = visit[cur.s][cur.x][cur.y] + 1;
    				queue.offer(new Point(ns, ni, nj));
    			}
    		}
    		
    		if (cnt == -1) {
    			sb.append("Trapped!");
    		} else {
    			sb.append("Escaped in ").append(cnt).append(" minute(s).");
    		}
    		sb.append('\n');
    	}
    	
    	System.out.println(sb);
    }
	
	static boolean isBound(int i, int j, int s) {
		return i >= 0 && i < n && j >= 0 && j < m && s >= 0 && s < l;
	}
	
	static class Point {
		int s;
		int x;
		int y;
		
		public Point (int s, int x, int y) {
			this.s = s;
			this.x = x;
			this.y = y;
		}
	}
}