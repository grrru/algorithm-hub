import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int n;
	static String[] temp;
	static long[] arr;
	static int k;
	static int[] tenMod = new int[751];
	static long[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		temp = new String[n + 1];
		arr = new long[n + 1];
		
		int maxLen = 0;
		for (int i = 1; i <= n; i++) {
			temp[i] = br.readLine();
			maxLen += temp[i].length();
		}
		k = Integer.parseInt(br.readLine());
		
		tenMod[0] = 1;
		tenMod[1] = 10 % k;
		for (int i = 2; i <= 750; i++) {
			tenMod[i] = (tenMod[i - 1] * (tenMod[1])) % k;
		}
		
		for (int i = 1; i <= n; i++) {
			arr[i] = 0;
			for (int j = 0; j < temp[i].length(); j++) {
			    arr[i] = (arr[i] * 10 + (temp[i].charAt(j) - '0')) % k;
			}

		}
		
		dp = new long[(1 << (n + 1)) + 1][k];
		
		for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], -1);
		
		long child = find((1 << (n + 1)) - 1, 0, maxLen);
		
		long parent = 1;
		for (int i = 2; i <= n; i++) parent *= i;
		
		System.out.println(ans(child, parent));
	}
	
	static String ans(long child, long parent) {
		
		long g = gcd(child, parent);
		
		return child / g + "/" + parent / g;
	}
	
	static long gcd(long a, long b) {
	    while (b != 0) {
	        long t = a % b;
	        a = b;
	        b = t;
	    }
	    return a;
	}
	
	static long find(int cur, int mod, int len) {
		if (len == 0) return mod == 0 ? 1 : 0;
		if (dp[cur][mod] != -1) return dp[cur][mod];
		
		long res = 0;
		
		for (int i = 1; i <= n; i++) {
			if ((cur & (1 << i)) == 0) continue;
			
			int cal = (int) ((arr[i] * tenMod[len - temp[i].length()]) % k);
			
			int nextMod = mod - cal;
			if (nextMod < 0) nextMod += k;
			
			res += find(cur - (1 << i), nextMod, len - temp[i].length());
		}
		
		return dp[cur][mod] = res;
	}
}
