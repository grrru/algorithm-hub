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
	static int k;
	static int[][] arr;
	static int[][] points;
	static int dir;
	static int[] cur;
	static int[] dice;
	static int ans = 0;
	static boolean[][] visit;
	static int[] di = {0, 0, 1, -1};
	static int[] dj = {1, -1, 0, 0};
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        arr = new int[n][m];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        
        points = new int[n][m];
        
        dir = 0;
        cur = new int[] {0, 0};
        dice = new int[] {1, 2, 3, 4, 5, 6};
        visit = new boolean[n][m];
        
        while (k-- > 0) {
        	move();
        	changeDice();
        	addPoint();
        	changeDir();
        }
        
        System.out.println(ans);
    }
    
    static void addPoint() {
    	if (points[cur[0]][cur[1]] != 0) {
    		ans += points[cur[0]][cur[1]];
    		return;
    	}
    	
    	Queue<Point> v = new ArrayDeque<>();
    	Queue<Point> queue = new ArrayDeque<>();
    	queue.offer(new Point(cur[0], cur[1]));
    	int ob = arr[cur[0]][cur[1]];
    	
    	v.offer(new Point(cur[0], cur[1]));
    	visit[cur[0]][cur[1]] = true;
    	
    	while (!queue.isEmpty()) {
    		Point c = queue.poll();
    		
    		for (int d = 0; d < 4; d++) {
    			int ni = c.x + di[d];
    			int nj = c.y + dj[d];
    			
    			if (!bound(ni, nj)) continue;
    			if (visit[ni][nj]) continue;
    			if (arr[ni][nj] != ob) continue;
    			
    			visit[ni][nj] = true;
    			v.offer(new Point(ni, nj));
    			queue.offer(new Point(ni, nj));
    		}
    	}
    	
    	int size = v.size();
    	int pt = size * ob;
    	
    	while (!v.isEmpty()) {
    		Point c = v.poll();
    		points[c.x][c.y] = pt; 
    	}
    	
    	ans += pt;
    }
    
    static void changeDir() {
    	int a = dice[5];
    	int b = arr[cur[0]][cur[1]];
    	
    	if (a > b) {
    		if (dir == 0) dir = 2;
    		else if (dir == 1) dir = 3;
    		else if (dir == 2) dir = 1;
    		else dir = 0;
    	} else if (a < b) {
    		if (dir == 0) dir = 3;
    		else if (dir == 1) dir = 2;
    		else if (dir == 2) dir = 0;
    		else dir = 1;
    	}
    }
    
    static void move() {
    	switch (dir) {
		case 0:
			if (cur[1] + 1 == m) {
				cur[1]--;
				dir = 1;
			}
			else cur[1]++;
			break;
		case 1:
			if (cur[1] == 0) {
				cur[1]++;
				dir = 0;
			}
			else cur[1]--;
			break;
		case 2:
			if (cur[0] + 1 == n) {
				cur[0]--;
				dir = 3;
			}
			else cur[0]++;
			break;
		case 3:
			if (cur[0] == 0) {
				cur[0]++;
				dir = 2;
			}
			else cur[0]--;
			break;
		}
    }
    
    static void changeDice() {
    	int s;
    	switch (dir) {
		case 0:
			s = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[2];
			dice[2] = s;
			break;
		case 1:
			s = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[5];
			dice[5] = dice[3];
			dice[3] = s;
			break;
		case 2:
			s = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[4];
			dice[4] = s;
			break;
		case 3:
			s = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[1];
			dice[1] = s;
			break;
		}
    }
    
    static boolean bound(int i, int j) {
    	return i >= 0 && i < n && j >= 0 && j < m;
    }
}