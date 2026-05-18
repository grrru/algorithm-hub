import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static long[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		arr = new long[10001];

		arr[0] = 1;
		arr[1] = 1L;
		arr[2] = 2L;
		
		for (int i = 3; i <= 10000; i++) {
			arr[i] += arr[i - 2] + 1;
		}
		
		for (int i = 3; i <= 10000; i++) {
			arr[i] += arr[i - 3];
		}
		
		
		int n = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while (n-- > 0) {
			int k = Integer.parseInt(br.readLine());
			sb.append(arr[k]).append('\n');
		}
		
		System.out.println(sb);
	}
}
