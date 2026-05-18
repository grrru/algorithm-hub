import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int s;
	static int[] height;
	static int[] dp;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	s = Integer.parseInt(st.nextToken());
    	
    	height = new int[20000001];
    	int max = 0;
    	for (int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		int h = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		
    		if (height[h] == 0) height[h] = c;
    		else height[h] = Math.max(height[h], c);
    		
    		if (h > max) max = h;
    	}
    	
    	dp = new int[max + 1];
    	
    	for (int h = s; h <= max; h++) {
    		
    		if (height[h] == 0) {
    			dp[h] = dp[h - 1];
    			continue;
    		}
	
    		dp[h] = Math.max(dp[h - 1], dp[h - s] + height[h]);
    	}
    	
    	System.out.println(dp[max]);
    }
}