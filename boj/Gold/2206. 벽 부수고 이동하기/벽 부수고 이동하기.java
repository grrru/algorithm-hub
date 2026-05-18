import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Hammer {
	int x;
	int y;
	int h;

	public Hammer(int x, int y, int h) {
		super();
		this.x = x;
		this.y = y;
		this.h = h;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer s = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(s.nextToken());
		int m = Integer.parseInt(s.nextToken());
		
		int[][] arr = new int[n][m];
		
		boolean[][] visitHammer = new boolean[n][m];
		boolean[][] visitNoHammer = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		Queue<Hammer> pts = new ArrayDeque<>();
		
		pts.add(new Hammer(0, 0, 1));
		
		int [] di = {1, -1, 0, 0};
		int [] dj = {0, 0, 1, -1};
		
		int time = 1;
		int nextCnt;
		int cnt = 1;
		boolean escape = false;
		
		run : while (!pts.isEmpty()) {
			nextCnt = cnt;
			cnt = 0;
			time++;
			
			while (nextCnt != 0) {
				Hammer hm = pts.poll();
				
				for (int d = 0; d < 4; d++) {
					int ni = hm.x + di[d];
					int nj = hm.y + dj[d];
					if (ni == n - 1 && nj == m - 1) {
						escape = true;
						break run;
					}
					
					if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
						if (hm.h == 0) {
							if (arr[ni][nj] == 1) continue;
							if (!visitNoHammer[ni][nj]) {
								cnt++;
								pts.add(new Hammer(ni, nj, hm.h));
								visitNoHammer[ni][nj] = true;
							}
						} else {
							if (arr[ni][nj] == 1) {
								if (!visitHammer[ni][nj]) {
									cnt++;
									pts.add(new Hammer(ni, nj, 0));
									visitHammer[ni][nj] = true;
								}
								
							} else {
								if (!visitHammer[ni][nj]) {
									cnt++;
									pts.add(new Hammer(ni, nj, 1));
									visitHammer[ni][nj] = true;
								}

							}
						}
					}
				}
				nextCnt--;
			}
		}
		if (escape) System.out.println(time);
		else if (n == 1 && m == 1 && arr[0][0] == 0) System.out.println(1);
		else System.out.println(-1);
	}

}