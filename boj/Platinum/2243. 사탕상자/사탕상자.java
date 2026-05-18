import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		int size = 1;
		while (size < 1000000) size *= 2;
		
		tree = new int[size * 2];
		StringBuilder sb = new StringBuilder();
		
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int cmd = Integer.parseInt(st.nextToken());
			
			if (cmd == 1) {
				int rank = Integer.parseInt(st.nextToken());
				int idx = 1;
				
				while (idx < 1048576) {
					if (rank > tree[idx * 2]) {
						rank -= tree[idx * 2];
						idx = idx * 2 + 1;
					} else {
						idx = idx * 2;
					}
				}
				sb.append(idx - 1048575).append('\n');
				while (idx >= 1) {
					tree[idx]--;
					idx /= 2;
				}
			} else {
				int f = Integer.parseInt(st.nextToken()) + 1048575;
				int cnt = Integer.parseInt(st.nextToken());
				
				while (f >= 1) {
					tree[f] += cnt;
					f /= 2;
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}