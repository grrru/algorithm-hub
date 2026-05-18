import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] arr;
	static int s;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n - 1; i++) {
			
			int max = arr[i];
			int idx = i;
			
			for (int j = i + 1; j <= i + s; j++) {
				if (j >= n) break;
				
				if (max < arr[j]) {
					idx = j;
					max = arr[j];
				}
			}
			
			for (int k = idx; k >= i + 1; k--) {
				int cur = arr[k];
				arr[k] = arr[k - 1];
				arr[k - 1] = cur;
			}
			s -= (idx - i);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) sb.append(arr[i]).append(' ');
		System.out.println(sb);
	}
}
