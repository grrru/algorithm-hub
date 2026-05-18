import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static long[] tree;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		size = 1;
		while (size < n) size *= 2;
		tree = new long[size * 2];
		
		for (int i = size; i < size + n; i++) {
			tree[i] = Long.parseLong(br.readLine());
		}
		
		makeTree(1);
		
		StringBuilder sb = new StringBuilder();
		int cnt = m + k;
		while (cnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
			if (cmd == 1) {
				
				int r = Integer.parseInt(st.nextToken());
				long t = Long.parseLong(st.nextToken());
				
				int idx = r + size - 1;
				long diff = t - tree[idx];
				
				while (idx >= 1) {
					tree[idx] += diff;
					idx /= 2;
				}
				
			} else {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				sb.append(find(b, c, 1, size, 1)).append('\n');
			}
		}
		
		System.out.println(sb.toString());
		
	}
	private static long find(int b, int c, int start, int end, int idx) {
		if (b > end || c < start) return 0L;
		
		if (b <= start && end <= c) return tree[idx];
		
		int mid = (start + end) / 2;
		
		return find(b, c, start, mid, idx * 2) + find(b, c, mid + 1, end, idx * 2 + 1);	
	}
	static long makeTree(int idx) {
		if (idx >= size) {
			return tree[idx];
		}
		
		return tree[idx] = makeTree(idx * 2) + makeTree(idx * 2 + 1);
	}
}
