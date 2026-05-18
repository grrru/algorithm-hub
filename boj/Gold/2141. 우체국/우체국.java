import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] arr;
	static long sum;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			
			sum += arr[i][1];
		}
		
		Arrays.sort(arr, (o1, o2) -> {
			return Integer.compare(o1[0], o2[0]);
		});
		
		long k = 0;
		int ans = arr[n - 1][0];
		
		for (int i = 0; i < n; i++) {
		    k += arr[i][1];
		    if (k >= (sum + 1) / 2) {
		        ans = arr[i][0];
		        break;
		    }
		}
				
		System.out.println(ans);
	}
}