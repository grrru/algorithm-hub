import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] height;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		height = new int[1000001];
		
		st = new StringTokenizer(br.readLine());
		
		int ans = 0;
		
		while (n-- > 0) {
			int k = Integer.parseInt(st.nextToken());
			
			if (height[k] == 0) {
				ans++;
				height[k - 1]++;
				continue;
			}
			
			height[k]--;
			height[k - 1]++;
		}
		
		System.out.println(ans);
	}
}
