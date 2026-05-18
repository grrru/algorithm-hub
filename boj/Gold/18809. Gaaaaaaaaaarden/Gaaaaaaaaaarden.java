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
	static int r;
	static int g;
	static int[][] arr;
	static int[][] pos = new int[10][3]; // 배양액을 뿌릴 수 있는 땅의 위치 정보
	static int[][] posRG;
	static int cnt = 0; // 배양액을 뿌릴 수 있는 땅의 개수
	static int flower = 0;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken()); // 3
		g = Integer.parseInt(st.nextToken()); // 4
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					pos[cnt][0] = i;
					pos[cnt][1] = j;
					cnt++;
				}
			}
		}
		posRG = new int[r + g][3];
		findRed(0, 0);
		System.out.println(flower);
	}
	static void bfs() {
		int[][] farm = new int[n][m];
		int[][] depth = new int[n][m];
		for (int i = 0; i < n; i++) farm[i] = Arrays.copyOf(arr[i], m);
		
		int ans = 0;
		Queue<Point> queue = new ArrayDeque<>();
		
		for (int i = 0; i < r + g; i++) {
			queue.offer(new Point(posRG[i][0], posRG[i][1]));
			depth[posRG[i][0]][posRG[i][1]] = 1;
			farm[posRG[i][0]][posRG[i][1]] = posRG[i][2];
		}
		
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			
			if (farm[p.x][p.y] != 3 && farm[p.x][p.y] != 4) continue;
			
			for (int d = 0; d < 4; d++) {
				int ni = p.x + di[d];
				int nj = p.y + dj[d];
				
				if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
					if (farm[ni][nj] == 0) continue;
					if (farm[ni][nj] == -1) continue;
					if (farm[ni][nj] == 1 || farm[ni][nj] == 2) {
						farm[ni][nj] = farm[p.x][p.y];
						depth[ni][nj] = depth[p.x][p.y] + 1; 
						queue.offer(new Point(ni, nj));
						continue;
					}
					if (farm[ni][nj] == 3 && farm[p.x][p.y] == 4) {
						if (depth[ni][nj] != depth[p.x][p.y] + 1) continue;
						ans++;
						farm[ni][nj] = -1;
						continue;
					}
					if (farm[ni][nj] == 4 && farm[p.x][p.y] == 3) {
						if (depth[ni][nj] != depth[p.x][p.y] + 1) continue;
						ans++;
						farm[ni][nj] = -1;
						continue;
					}
				}
			}
		}
		if (ans > flower) flower = ans;
	}
	
	static void findRed(int depth, int idx) {
		if (depth == r) {
			findGreen(depth, 0);
			return;
		}
		for (int i = idx; i < cnt; i++) {
			posRG[depth][0] = pos[i][0];
			posRG[depth][1] = pos[i][1];
			pos[i][2] = 3;
			posRG[depth][2] = 3;
			findRed(depth + 1, i + 1);
			pos[i][2] = 0;
		}
	}
	
	static void findGreen(int depth, int idx) {
		if (depth == r + g) {
			bfs();
			return;
		}
		for (int i = idx; i < cnt; i++) {
			if (pos[i][2] == 3) continue;
			posRG[depth][0] = pos[i][0];
			posRG[depth][1] = pos[i][1];
			pos[i][2] = 4;
			posRG[depth][2] = 4;
			findGreen(depth + 1, i + 1);
		}
	}
}
