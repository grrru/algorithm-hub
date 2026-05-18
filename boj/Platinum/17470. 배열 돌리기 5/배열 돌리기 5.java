import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] arr;
	static int n;
	static int m;
	static int[][] command;
	static int dir;
	static boolean reverse;
	static int[][] ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		command = new int[2][2];
		command[0][0] = 1;
		command[0][1] = 2;
		command[1][0] = 3;
		command[1][1] = 4;
		dir = 0;
		reverse = false;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < r; i++) {
			int cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case 1:
				first();
				break;
			case 2:
				second();
				break;
			case 3:
				third();
				break;
			case 4:
				fourth();
				break;
			case 5:
				fifth();
				break;
			case 6:
				sixth();
				break;
			}
		}
		
		makeResult();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans[0].length; j++) {
				sb.append(ans[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static void makeResult() {
		
		if (reverse) reverseArr();
		
		
		if (dir == 0) {
			ans = new int[n][m];
			
			switch(command[0][0]) {
			case 1:
				for (int i = 0; i < n / 2; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = arr[i][j];
					}
				}
				break;
			case 2:
				for (int i = 0; i < n / 2; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = arr[i][j + m / 2];
					}
				}
				break;
			case 3:
				for (int i = 0; i < n / 2; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = arr[i + n / 2][j];
					}
				}
				break;
			case 4:
				for (int i = 0; i < n / 2; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = arr[i + n / 2][j + m / 2];
					}
				}
				break;
			}
			
			switch(command[0][1]) {
			case 1:
				for (int i = 0; i < n / 2; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = arr[i][j - m / 2];
					}
				}
				break;
			case 2:
				for (int i = 0; i < n / 2; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = arr[i][j];
					}
				}
				break;
			case 3:
				for (int i = 0; i < n / 2; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = arr[i + n / 2][j - m / 2];
					}
				}
				break;
			case 4:
				for (int i = 0; i < n / 2; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = arr[i + n / 2][j];
					}
				}
				break;
			}
			
			switch(command[1][0]) {
			case 1:
				for (int i = n / 2; i < n; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = arr[i - n / 2][j];
					}
				}
				break;
			case 2:
				for (int i = n / 2; i < n; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = arr[i - n / 2][j + m / 2];
					}
				}
				break;
			case 3:
				for (int i = n / 2; i < n; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = arr[i][j];
					}
				}
				break;
			case 4:
				for (int i = n / 2; i < n; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = arr[i][j + m / 2];
					}
				}
				break;
			}
			
			switch(command[1][1]) {
			case 1:
				for (int i = n / 2; i < n; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = arr[i - n / 2][j - m / 2];
					}
				}
				break;
			case 2:
				for (int i = n / 2; i < n; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = arr[i - n / 2][j];
					}
				}
				break;
			case 3:
				for (int i = n / 2; i < n; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = arr[i][j - m / 2];
					}
				}
				break;
			case 4:
				for (int i = n / 2; i < n; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = arr[i][j];
					}
				}
				break;
			}
		}
		else if (dir == 2) {
			ans = new int[n][m];
			
			int[][] copy = new int[n][m];
			
			for (int i = 0; i < n / 2; i++) {
				for (int j = 0; j < m / 2; j++) {
					copy[i][j] = arr[n / 2 - i - 1][m / 2 - j - 1];
					copy[i + n / 2][j] = arr[n - i - 1][m / 2 - j - 1];
					copy[i][j + m / 2] = arr[n / 2 - i - 1][m - j - 1];
					copy[i + n / 2][j + m / 2] = arr[n - i - 1][m - j - 1];
				}
			}

			switch(command[0][0]) {
			case 1:
				for (int i = 0; i < n / 2; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = copy[i][j];
					}
				}
				break;
			case 2:
				for (int i = 0; i < n / 2; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = copy[i][j + m / 2];
					}
				}
				break;
			case 3:
				for (int i = 0; i < n / 2; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = copy[i + n / 2][j];
					}
				}
				break;
			case 4:
				for (int i = 0; i < n / 2; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = copy[i + n / 2][j + m / 2];
					}
				}
				break;
			}
			
			switch(command[0][1]) {
			case 1:
				for (int i = 0; i < n / 2; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = copy[i][j - m / 2];
					}
				}
				break;
			case 2:
				for (int i = 0; i < n / 2; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = copy[i][j];
					}
				}
				break;
			case 3:
				for (int i = 0; i < n / 2; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = copy[i + n / 2][j - m / 2];
					}
				}
				break;
			case 4:
				for (int i = 0; i < n / 2; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = copy[i + n / 2][j];
					}
				}
				break;
			}
			
			switch(command[1][0]) {
			case 1:
				for (int i = n / 2; i < n; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = copy[i - n / 2][j];
					}
				}
				break;
			case 2:
				for (int i = n / 2; i < n; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = copy[i - n / 2][j + m / 2];
					}
				}
				break;
			case 3:
				for (int i = n / 2; i < n; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = copy[i][j];
					}
				}
				break;
			case 4:
				for (int i = n / 2; i < n; i++) {
					for (int j = 0; j < m / 2; j++) {
						ans[i][j] = copy[i][j + m / 2];
					}
				}
				break;
			}
			
			switch(command[1][1]) {
			case 1:
				for (int i = n / 2; i < n; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = copy[i - n / 2][j - m / 2];
					}
				}
				break;
			case 2:
				for (int i = n / 2; i < n; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = copy[i - n / 2][j];
					}
				}
				break;
			case 3:
				for (int i = n / 2; i < n; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = copy[i][j - m / 2];
					}
				}
				break;
			case 4:
				for (int i = n / 2; i < n; i++) {
					for (int j = m / 2; j < m; j++) {
						ans[i][j] = copy[i][j];
					}
				}
				break;
			}
		}
		else if (dir == 1) {
			ans = new int[m][n];
			
			int[][] copy = new int[m][n];
			
			for (int i = 0; i < m / 2; i++) {
				for (int j = 0; j < n / 2; j++) {
					copy[i][j] = arr[n / 2 - j - 1][i];
					copy[i + m / 2][j] = arr[n - j - 1][i];
					copy[i][j + n / 2] = arr[n / 2 - j - 1][i + m / 2];
					copy[i + m / 2][j + n / 2] = arr[n - j - 1][i + m / 2];
				}
			}
			
			switch(command[0][0]) {
			case 1:
				for (int i = 0; i < m / 2; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i][j];
					}
				}
				break;
			case 2:
				for (int i = 0; i < m / 2; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i][j + n / 2];
					}
				}
				break;
			case 3:
				for (int i = 0; i < m / 2; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i + m / 2][j];
					}
				}
				break;
			case 4:
				for (int i = 0; i < m / 2; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i + m / 2][j + n / 2];
					}
				}
				break;
			}
			
			switch(command[0][1]) {
			case 1:
				for (int i = 0; i < m / 2; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i][j - n / 2];
					}
				}
				break;
			case 2:
				for (int i = 0; i < m / 2; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i][j];
					}
				}
				break;
			case 3:
				for (int i = 0; i < m / 2; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i + m / 2][j - n / 2];
					}
				}
				break;
			case 4:
				for (int i = 0; i < m / 2; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i + m / 2][j];
					}
				}
				break;
			}
			
			switch(command[1][0]) {
			case 1:
				for (int i = m / 2; i < m; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i - m / 2][j];
					}
				}
				break;
			case 2:
				for (int i = m / 2; i < m; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i - m / 2][j + n / 2];
					}
				}
				break;
			case 3:
				for (int i = m / 2; i < m; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i][j];
					}
				}
				break;
			case 4:
				for (int i = m / 2; i < m; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i][j + n / 2];
					}
				}
				break;
			}
			
			switch(command[1][1]) {
			case 1:
				for (int i = m / 2; i < m; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i - m / 2][j - n / 2];
					}
				}
				break;
			case 2:
				for (int i = m / 2; i < m; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i - m / 2][j];
					}
				}
				break;
			case 3:
				for (int i = m / 2; i < m; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i][j - n / 2];
					}
				}
				break;
			case 4:
				for (int i = m / 2; i < m; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i][j];
					}
				}
				break;
			}
		}
		else {
			ans = new int[m][n];
			
			int[][] copy = new int[m][n];
			
			for (int i = 0; i < m / 2; i++) {
				for (int j = 0; j < n / 2; j++) {
					copy[i][j] = arr[j][m / 2 - i - 1];
					copy[i + m / 2][j] = arr[j + n / 2][m / 2 - i - 1];
					copy[i][j + n / 2] = arr[j][m - i - 1];
					copy[i + m / 2][j + n / 2] = arr[j + n / 2][m - i - 1];
				}
			}
			
			switch(command[0][0]) {
			case 1:
				for (int i = 0; i < m / 2; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i][j];
					}
				}
				break;
			case 2:
				for (int i = 0; i < m / 2; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i][j + n / 2];
					}
				}
				break;
			case 3:
				for (int i = 0; i < m / 2; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i + m / 2][j];
					}
				}
				break;
			case 4:
				for (int i = 0; i < m / 2; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i + m / 2][j + n / 2];
					}
				}
				break;
			}
			
			switch(command[0][1]) {
			case 1:
				for (int i = 0; i < m / 2; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i][j - n / 2];
					}
				}
				break;
			case 2:
				for (int i = 0; i < m / 2; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i][j];
					}
				}
				break;
			case 3:
				for (int i = 0; i < m / 2; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i + m / 2][j - n / 2];
					}
				}
				break;
			case 4:
				for (int i = 0; i < m / 2; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i + m / 2][j];
					}
				}
				break;
			}
			
			switch(command[1][0]) {
			case 1:
				for (int i = m / 2; i < m; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i - m / 2][j];
					}
				}
				break;
			case 2:
				for (int i = m / 2; i < m; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i - m / 2][j + n / 2];
					}
				}
				break;
			case 3:
				for (int i = m / 2; i < m; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i][j];
					}
				}
				break;
			case 4:
				for (int i = m / 2; i < m; i++) {
					for (int j = 0; j < n / 2; j++) {
						ans[i][j] = copy[i][j + n / 2];
					}
				}
				break;
			}
			
			switch(command[1][1]) {
			case 1:
				for (int i = m / 2; i < m; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i - m / 2][j - n / 2];
					}
				}
				break;
			case 2:
				for (int i = m / 2; i < m; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i - m / 2][j];
					}
				}
				break;
			case 3:
				for (int i = m / 2; i < m; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i][j - n / 2];
					}
				}
				break;
			case 4:
				for (int i = m / 2; i < m; i++) {
					for (int j = n / 2; j < n; j++) {
						ans[i][j] = copy[i][j];
					}
				}
				break;
			}
		}
	}
	
	static void reverseArr() {
		int[][] copy = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m / 2; j++) {
				copy[i][j] = arr[i][m / 2 - j - 1];
			}
			for (int j = m / 2; j < m; j++) {
				copy[i][j] = arr[i][m * 3 / 2 - j - 1];
			}
		}
		arr = copy;
	}

	static void first() {
		int one = command[0][0];
		int two = command[0][1];
		command[0][0] = command[1][0];
		command[0][1] = command[1][1];
		command[1][0] = one;
		command[1][1] = two;
		
		if (dir == 0) dir = 2;
		else if (dir == 2) dir = 0;
		reverse = !reverse;
	}
	
	static void second() {
		int one = command[0][0];
		int three = command[1][0];
		command[0][0] = command[0][1];
		command[1][0] = command[1][1];
		command[0][1] = one;
		command[1][1] = three;

		if (dir == 1) dir = 3;
		else if (dir == 3) dir = 1;
		reverse = !reverse;
	}
	
	static void third() {
		fifth();
		
		dir = (dir + 1) % 4;
	}
	
	static void fourth() {
		sixth();
		
		dir = (dir + 3) % 4;
	}
	
	static void fifth() {
		int one = command[0][0];
		command[0][0] = command[1][0];
		command[1][0] = command[1][1];
		command[1][1] = command[0][1];
		command[0][1] = one;
	}
	
	static void sixth() {
		int one = command[0][0];
		command[0][0] = command[0][1];
		command[0][1] = command[1][1];
		command[1][1] = command[1][0];
		command[1][0] = one;
	}
}
