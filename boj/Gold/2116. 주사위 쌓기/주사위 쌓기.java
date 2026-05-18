import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] max;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		max = new int[n + 1][6 + 1];
		arr = new int[n + 1][6 + 1];
		
		for (int i = 1; i <= n; i++) check(new StringTokenizer(br.readLine()), i);
		
		int res = 0;
		for (int i = 1; i <= 6; i++) {
			int temp = 0;
			int up = i;
			for (int j = 1; j <= n; j++) {
				temp += max[j][up];
				up = arr[j][up];
			}
			
			res = Math.max(temp, res);
		}
		
		System.out.println(res);
	}
	
	static void check(StringTokenizer st, int lev) {
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int f = Integer.parseInt(st.nextToken());
		
		max[lev][a] = Math.max(Math.max(b, c), Math.max(d, e));
		max[lev][b] = Math.max(Math.max(a, c), Math.max(f, e));
		max[lev][c] = Math.max(Math.max(b, a), Math.max(d, f));
		max[lev][d] = max[lev][b];
		max[lev][e] = max[lev][c];
		max[lev][f] = max[lev][a];
		
		arr[lev][a] = f;
		arr[lev][b] = d;
		arr[lev][c] = e;
		arr[lev][d] = b;
		arr[lev][e] = c;
		arr[lev][f] = a;
	}
}
