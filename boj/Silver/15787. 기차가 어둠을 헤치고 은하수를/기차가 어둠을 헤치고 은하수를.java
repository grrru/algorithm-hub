import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    
    static int n;
    static int m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
         
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
         
         arr = new int[n];
         
         while (m-- > 0) {
        	 st = new StringTokenizer(br.readLine());
        	 
        	 int cmd = Integer.parseInt(st.nextToken());
        	 int t = Integer.parseInt(st.nextToken()) - 1;
        	 int k;
        	 
        	 switch (cmd) {
        	 	case 1:
        	 		k = Integer.parseInt(st.nextToken()) - 1;
        	 		
        	 		if ((arr[t] & (1 << k)) != 0) break;
        	 		arr[t] |= (1 << k);
        	 		break;
        	 	case 2:
        	 		k = Integer.parseInt(st.nextToken()) - 1;
        	 		
        	 		if ((arr[t] & (1 << k)) == 0) break;
        	 		arr[t] -= (1 << k);
        	 		
    				break;
        	 	case 3:
        	 		arr[t] <<= 1;
        	 		if ((arr[t] & (1 << 20)) == 0) break;
    				arr[t] -= (1 << 20);
    				break;
        	 	case 4:
    				arr[t] >>= 1;
    				break;
			}
         }
         
         int cnt = 0;
    	 boolean[] visit = new boolean[1 << 20];
    	 
    	 for (int p : arr) {
    		 if (visit[p]) continue;
    		 
    		 cnt++;
    		 visit[p] = true;
    	 }
    	 System.out.println(cnt);
    }
}