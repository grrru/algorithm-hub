import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static long x;
	static long ans;
	static long[] layer;
	static long[] patty;

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
         
         n = Integer.parseInt(st.nextToken());
         x = Long.parseLong(st.nextToken());
         ans = 0;
         
         layer = new long[n + 1];
         patty = new long[n + 1];
         
         layer[0] = 1;
         patty[0] = 1;
         for (int i = 1; i <= n; i++) {
        	 layer[i] = layer[i - 1] * 2 + 3;
        	 patty[i] = patty[i - 1] * 2 + 1;
         }
         
         find(n, x);
         System.out.println(ans);
    }

	private static void find(int lev, long cnt) {
		if (cnt == 0) return;
		if (lev == 0) {
			ans += 1;
			return;
		}
		
		if (layer[lev] == cnt || layer[lev] - 1 == cnt) {
			ans += patty[lev];
			return;
		}
		
		if (layer[lev - 1] + 1 == cnt) {
			ans += patty[lev - 1];
			return;
		}
		if (layer[lev - 1] + 2 == cnt) {
			ans += patty[lev - 1] + 1;
			return;
		}
		
		if (layer[lev - 1] + 2 < cnt) {
			ans += patty[lev - 1] + 1;
			find(lev - 1, cnt - (layer[lev - 1] + 2));
		} else {
			find(lev - 1, cnt - 1);
		}
	}
}