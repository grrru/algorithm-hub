import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());


		int [] arr = new int[n + 1];
		arr[1] = 3;
		for (int i = 2; i <= n; i++) {
			arr[i] = (2 * arr[i - 1] + 5) % 1000000007;
		}
		System.out.println(arr[n]);
	}
}
