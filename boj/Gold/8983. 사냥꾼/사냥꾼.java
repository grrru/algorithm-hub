import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
    static int m;
    static int n;
    static int l;
    static int[] arr;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        
        arr = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        
        for (int t = 0; t < n; t++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	binarySearch(x, y);
        }
        
        System.out.println(ans);
    }

	private static void binarySearch(int x, int y) {
		if (y > l) return;
		
		int start = 0;
		int end = m - 1;
		int res = 0;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (arr[mid] < x) {
				res = mid;
				start = mid + 1;
			} else if (arr[mid] > x) {
				end = mid - 1;
			} else {
				if (y <= l) ans++;
				return;
			}
		}
		
		if (res != m - 1) {
			if (arr[res + 1] - x < x - arr[res]) res++;
		}
		
		if (y + Math.abs(arr[res] - x) <= l) ans++;
	}
}