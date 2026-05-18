import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static String str;
	static List<Integer> red;
	static List<Integer> blue;

	static int[] ans;

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
         
         int n = Integer.parseInt(st.nextToken());
         int q = Integer.parseInt(st.nextToken());
         
         str = br.readLine();
         
         red = new ArrayList<>();
         blue = new ArrayList<>();
         ans = new int[4];
         
         for (int i = 0; i < n; i++) {
        	 char c = str.charAt(i);
        	 
        	 if (c == 'R') red.add(i);
        	 else if (c == 'B') blue.add(i);
         }
         
         StringBuilder sb = new StringBuilder();
         
         while (q-- > 0) {
        	 st = new StringTokenizer(br.readLine());
        	 
        	 int s = Integer.parseInt(st.nextToken());
        	 int e = Integer.parseInt(st.nextToken());
        	 
        	 if (rinary(s, e)) {
        		 for (int i = 0; i < 4; i++) sb.append(ans[i]).append(' ');
        	 } else {
        		 sb.append(-1);
        	 }
        	 sb.append('\n');
         }
         System.out.println(sb);
    }
    
    static boolean rinary(int s, int e) {
    	
    	int idx = -1;
    	int start = 0;
    	int end = red.size() - 1;
    	
    	while (start <= end) {
    		int mid = (start + end) / 2;
    		
    		if (red.get(mid) < s) {
    			start = mid + 1;
    		} else {
    			idx = mid;
    			end = mid - 1;
    		}
    	}
    	
    	if (idx == -1) return false;
    	if (idx == red.size() - 1) return false;
    	if (red.get(idx + 1) >= e) return false;
    	
    	ans[0] = red.get(idx);
    	ans[1] = red.get(idx + 1);
    	
    	return binary(red.get(idx + 1), e);
    }
    
    static boolean binary(int s, int e) {
    	
    	int idx = -1;
    	int start = 0;
    	int end = blue.size() - 1;
    	
    	while (start <= end) {
    		int mid = (start + end) / 2;
    		
    		if (blue.get(mid) > e) {
    			end = mid - 1;
    		} else {
    			idx = mid;
    			start = mid + 1;
    		}
    	}
    	
    	if (idx == -1) return false;
    	if (idx == 0) return false;
    	if (blue.get(idx - 1) <= s) return false;
    	
    	ans[2] = blue.get(idx - 1);
    	ans[3] = blue.get(idx);
    	return true;
    }
}