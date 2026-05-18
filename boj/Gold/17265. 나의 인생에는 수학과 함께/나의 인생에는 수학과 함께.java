import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static char[][] arr;
	static int[] di = {1, 0};
	static int[] dj = {0, 1};
	static long[][] big;
	static long[][] small;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) arr[i][j] = st.nextToken().charAt(0);
		}
		
		big = new long[n][n];
		small = new long[n][n];
		
		for (int i = 0; i < n; i++) {
			Arrays.fill(big[i], Long.MIN_VALUE);
			Arrays.fill(small[i], Long.MAX_VALUE);
		}
		
		System.out.println(findMax(n - 1, n - 1) + " " + findMin(n - 1, n - 1));		
	}
	
	static long findMax(int x, int y) {
		if (big[x][y] != Long.MIN_VALUE) return big[x][y];
		if (x == 0 && y == 0) return big[x][y] = arr[x][y] - '0';
		
		long res = Long.MIN_VALUE;
		
		if (isBound(x - 2, y)) {
			switch(arr[x - 1][y]) {
			case '+':
				res = findMax(x - 2, y) + (arr[x][y] - '0');
				break;
			case '-':
				res = findMax(x - 2, y) - (arr[x][y] - '0');
				break;
			case '*':
				res = findMax(x - 2, y) * (arr[x][y] - '0');
				break;
			}
		}
		
		if (isBound(x, y - 2)) {
			switch(arr[x][y - 1]) {
			case '+':
				res = Math.max(findMax(x, y - 2) + (arr[x][y] - '0'), res);
				break;
			case '-':
				res = Math.max(findMax(x, y - 2) - (arr[x][y] - '0'), res);
				break;
			case '*':
				res = Math.max(findMax(x, y - 2) * (arr[x][y] - '0'), res);
				break;
			}
		}
		
		if (isBound(x - 1, y - 1)) {
			switch(arr[x][y - 1]) {
			case '+':
				res = Math.max(findMax(x - 1, y - 1) + (arr[x][y] - '0'), res);
				break;
			case '-':
				res = Math.max(findMax(x - 1, y - 1) - (arr[x][y] - '0'), res);
				break;
			case '*':
				res = Math.max(findMax(x - 1, y - 1) * (arr[x][y] - '0'), res);
				break;
			}
			
			switch(arr[x - 1][y]) {
			case '+':
				res = Math.max(findMax(x - 1, y - 1) + (arr[x][y] - '0'), res);
				break;
			case '-':
				res = Math.max(findMax(x - 1, y - 1) - (arr[x][y] - '0'), res);
				break;
			case '*':
				res = Math.max(findMax(x - 1, y - 1) * (arr[x][y] - '0'), res);
				break;
			}
		}
		
		return big[x][y] = res;
	}
	
	static long findMin(int x, int y) {
		if (small[x][y] != Long.MAX_VALUE) return small[x][y];
		if (x == 0 && y == 0) return small[x][y] = arr[x][y] - '0';
		
		long res = Long.MAX_VALUE;
		
		if (isBound(x - 2, y)) {
			switch(arr[x - 1][y]) {
			case '+':
				res = findMin(x - 2, y) + (arr[x][y] - '0');
				break;
			case '-':
				res = findMin(x - 2, y) - (arr[x][y] - '0');
				break;
			case '*':
				res = findMin(x - 2, y) * (arr[x][y] - '0');
				break;
			}
		}
		
		if (isBound(x, y - 2)) {
			switch(arr[x][y - 1]) {
			case '+':
				res = Math.min(findMin(x, y - 2) + (arr[x][y] - '0'), res);
				break;
			case '-':
				res = Math.min(findMin(x, y - 2) - (arr[x][y] - '0'), res);
				break;
			case '*':
				res = Math.min(findMin(x, y - 2) * (arr[x][y] - '0'), res);
				break;
			}
		}
		
		if (isBound(x - 1, y - 1)) {
			switch(arr[x][y - 1]) {
			case '+':
				res = Math.min(findMin(x - 1, y - 1) + (arr[x][y] - '0'), res);
				break;
			case '-':
				res = Math.min(findMin(x - 1, y - 1) - (arr[x][y] - '0'), res);
				break;
			case '*':
				res = Math.min(findMin(x - 1, y - 1) * (arr[x][y] - '0'), res);
				break;
			}
			
			switch(arr[x - 1][y]) {
			case '+':
				res = Math.min(findMin(x - 1, y - 1) + (arr[x][y] - '0'), res);
				break;
			case '-':
				res = Math.min(findMin(x - 1, y - 1) - (arr[x][y] - '0'), res);
				break;
			case '*':
				res = Math.min(findMin(x - 1, y - 1) * (arr[x][y] - '0'), res);
				break;
			}
		}
		
		return small[x][y] = res;
	}
	
	static boolean isBound(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
}