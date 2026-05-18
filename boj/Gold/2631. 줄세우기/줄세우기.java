import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n + 1];
		
		int idx = 0;
		
		for (int i = 1; i <= n; i++) {
			int k = Integer.parseInt(br.readLine());
			
			if (k > arr[idx]) {
				arr[++idx] = k;
				continue;
			}
			
			int s = 0;
			int e = idx;
			
			int t = s;
			boolean flag = false;
			
			while (s <= e) {
				int mid = (s + e) / 2;
				
				if (arr[mid] > k) e = mid - 1;
				else if (arr[mid] < k) {
					s = mid + 1;
					t = mid;
				}
				else {
					flag = true;
					break;
				}
			}
			
			if (!flag) arr[t + 1] = k;
		}
		System.out.println(n - idx);
	}
}
