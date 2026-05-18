import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] height;
	static int[] cnt;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        cnt = new int[n];
        
        height = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) height[i] = Integer.parseInt(st.nextToken());
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
        	ans += Math.min(height[i], cnt[i]) * 2;
        	
        	if (cnt[i] >= height[i]) continue;
        	
        	ans += (height[i] - cnt[i]) * 3;
        	
        	if (i + 1 < n) cnt[i + 1] += height[i] - cnt[i];
        	if (i + 2 < n) cnt[i + 2] += Math.min(height[i + 1], height[i] - cnt[i]);
        }
        System.out.println(ans);
    }
}