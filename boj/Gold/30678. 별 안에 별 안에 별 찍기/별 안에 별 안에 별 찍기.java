import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int n;
	static char[][] arr;
	static boolean[][] star;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		int l = (int) Math.pow(5, n);
		
		arr = new char[l][l];
		star = new boolean[5][5];
		
		star[0][2] = true;
		star[1][2] = true;
		for (int i = 0; i < 5; i++) star[2][i] = true;
		for (int i = 1; i <= 3; i++) star[3][i] = true;
		star[4][1] = star[4][3] = true;
		
		solve(n, 0, 0);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l; j++) sb.append(arr[i][j] == '*' ? '*' : ' ');
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static void solve(int lev, int x, int y) {
		if (lev == 0) {
			arr[x][y] = '*';
			return;
		}
		
		int itv = (int) Math.pow(5, lev - 1);
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (star[i][j]) {
					solve(lev - 1, x + i * itv, y + j * itv);
				}
			}
		}
	}
}
