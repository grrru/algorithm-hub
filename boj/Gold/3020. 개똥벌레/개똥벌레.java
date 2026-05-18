import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[] prefix = new int[h + 1];
        
        for (int i = 0; i < n / 2; i++) {
        	int k = Integer.parseInt(br.readLine());
        	
        	prefix[1]++;
        	prefix[k + 1]--;
        	
        	k = Integer.parseInt(br.readLine());
        	prefix[h - k + 1]++;
        }
        
        int ans = Integer.MAX_VALUE;
        int cnt = 1;
        
        for (int i = 1; i <= h; i++) {
        	prefix[i] += prefix[i - 1];
        	
        	if (ans == prefix[i]) cnt++;
        	if (ans > prefix[i]) {
        		ans = prefix[i];
        		cnt = 1;
        	}
        }

        System.out.print(ans + " " + cnt);
    }
}