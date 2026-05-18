import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static StringBuilder sb;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		
		quad(0, 0, n);
		System.out.println(sb);

	}

	static void quad(int x, int y, int depth) {
		
		int k = arr[x][y];
		if (depth == 1) {
			sb.append(k);
			return;
		}

		for (int i = x; i < x + depth; i++) {
			for (int j = y; j < y + depth; j++) {
				if (arr[i][j] != k) {
					sb.append('(');
					quad(x, y, depth / 2);
					quad(x, y + depth / 2, depth / 2);
					quad(x + depth / 2, y, depth / 2);
					quad(x + depth / 2, y + depth / 2, depth / 2);
					sb.append(')');
					return;
				}
			}
		}
		sb.append(k);
	}
}
