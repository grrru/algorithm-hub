import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int x;
	static int n;
	static int[] arr;
	static final int CHANGE = 10_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str;
		while ((str = br.readLine()) != null && !str.equals("")) {
			x = Integer.parseInt(str) * CHANGE;
			n = Integer.parseInt(br.readLine());
			
			arr = new int[n];
			for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());
			Arrays.sort(arr);
			
			int s = 0;
			int e = n - 1;
			boolean flag = false;
			
			while (s < e) {
				int sum = arr[s] + arr[e];
				
				if (sum > x) {
					e--;
				} else if (sum < x) {
					s++;
				} else {
					flag = true;
					sb.append("yes ").append(arr[s]).append(' ').append(arr[e]);
					break;
				}
			}
			
			if (!flag) sb.append("danger");
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
}
