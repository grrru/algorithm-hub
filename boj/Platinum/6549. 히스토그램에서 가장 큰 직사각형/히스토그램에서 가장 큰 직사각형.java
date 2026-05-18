import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static long[] height;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			if (n == 0) break;
			
			height = new long[n];
			for (int i = 0; i < n; i++) height[i] = Integer.parseInt(st.nextToken());
			
			sb.append(findMax(0, n - 1)).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static long findMax(int start, int end) {
		
		if (start == end) return height[start];
		
		int mid = (start + end) / 2;
		
		long res = Math.max(findMax(start, mid), findMax(mid + 1, end));
		
		int s = mid;
		int e = mid + 1;
		long hei = Math.min(height[s], height[e]);
		long size = hei * 2;
		
		while (s > start && e < end) {
			if (height[s - 1] < height[e + 1]) {
				e++;
				hei = Math.min(hei, height[e]);
			} else {
				s--;
				hei = Math.min(hei, height[s]);
			}
			
			size = Math.max(size, hei * (e - s + 1));
		}
		
		while (s > start) {
			hei = Math.min(hei, height[--s]);
			size = Math.max(hei * (e - s + 1), size);
		}
		
		while (e < end) {
			hei = Math.min(hei, height[++e]);
			size = Math.max(hei * (e - s + 1), size);
		}
		
		res = Math.max(res, size);
		
		return res;
	}
}