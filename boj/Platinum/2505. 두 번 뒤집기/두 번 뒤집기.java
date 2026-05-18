import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
		
	public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	int n = Integer.parseInt(br.readLine());
    	st = new StringTokenizer(br.readLine());
    	
    	arr = new int[n + 1];
    	for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());
    	
    	int[] brr = Arrays.copyOf(arr, n + 1);
    	
    	if (check(1, n, 1, brr)) {
    		System.out.println(sb);
    		return;
    	} else {
    		brr = Arrays.copyOf(arr, n + 1);
    		sb = new StringBuilder();
    		check(n, 1, -1, brr);
    		System.out.println(sb);
    	}
    }
	
	static boolean check(int start, int end, int p, int[] arr) {
		
		int cnt = 0;
		
		if (p == 1) {
			for (int i = start; i <= end;) {
				if (arr[i] == i) {
					i += p;
					continue;
				}
				
				int o = i;
				while (i <= end && arr[i] != o) i += p;
				
				if (++cnt == 3) return false;
				reverse(o, i, arr);
				sb.append(o + " " + i).append('\n');
				i = o;
			}
		} else {
			for (int i = start; i >= end;) {
				if (arr[i] == i) {
					i += p;
					continue;
				}
				
				int o = i;
				while (i >= end && arr[i] != o) i += p;
				
				if (++cnt == 3) return false;
				reverse(i, o, arr);
				sb.append(i + " " + o).append('\n');
				i = o;
			}
		}
		
		if (cnt == 0) {
			sb.append(1 + " " + 1).append('\n').append(1 + " " + 1);
		} else if (cnt == 1) {
			sb.append(1 + " " + 1);
		}
		
		return true;
	}
	
	static void reverse(int s, int e, int[] arr) {
		
		int k = s + e;
		
		for (int i = s; i <= k / 2; i++) {
			int t = arr[i];
			arr[i] = arr[k - i];
			arr[k - i] = t;
		}
	}
}