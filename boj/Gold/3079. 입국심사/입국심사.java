import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		time = new int[n];
		for (int i = 0; i < n; i++) time[i] = Integer.parseInt(br.readLine());
		
		long s = 1;
		long e = 1_000_000_000L * 1_000_000_000;
		
		long res = e;
		while (s <= e) {
			long mid = (s + e) / 2;
			
			if (canFinish(mid)) {
				res = mid;
				e = mid - 1;
			} else s = mid + 1;
		}
		
		System.out.println(res);
	}
	
	static boolean canFinish(long mid) {
		long y = 0;
		
		for (int i = 0; i < n; i++) {
			y += mid / time[i];
			if (y >= m) return true;
		}
		
		return y >= m;
	}
}
