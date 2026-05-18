import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static long b;
	static long c;
	static int[] weight;
	static int[] cnt;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());
        
        cnt = new int[n];
        
        weight = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) weight[i] = Integer.parseInt(st.nextToken());
        
        long ans = 0L;
        if (b <= c) {
        	for (int i = 0; i < n; i++) ans += weight[i] * b;
        	System.out.println(ans);
        	return;
        }
        
        
        for (int i = 0; i < n; i++) {
        	ans += Math.min(weight[i], cnt[i]) * c;
        	
        	if (cnt[i] >= weight[i]) continue;
        	
        	ans += (weight[i] - cnt[i]) * b;
        	
        	if (i + 1 < n) cnt[i + 1] += weight[i] - cnt[i];
        	if (i + 2 < n) cnt[i + 2] += Math.min(weight[i + 1], weight[i] - cnt[i]);
        }
        System.out.println(ans);
    }
}