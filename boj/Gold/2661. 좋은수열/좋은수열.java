import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] arr;
	static String ans = null;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        arr = new int[n];
        arr[0] = 1;
        
        backtracking(1);
        System.out.println(ans);
    }
    
    static void backtracking(int dep) {
    	
    	if (ans != null) return;
    	
    	if (dep == n) {
    		StringBuilder sb = new StringBuilder();
    		for (int i = 0; i < n; i++) {
    			sb.append(arr[i]);
    		}
    		ans = sb.toString();
    		return;
    	}
    	
    	for (int next = 1; next <= 3; next++) {
    		if (check(dep, next)) {
    			arr[dep] = next;
    			backtracking(dep + 1);
    			arr[dep] = 0;
    		}
    	}
    }
    
    static boolean check(int dep, int next) {
    	
    	int maxLen = dep % 2 == 0 ? dep / 2 : dep / 2 + 1;
    	
    	arr[dep] = next;
    	
    	for (int l = 1; l <= maxLen; l++) {
    		int k = dep - l;
    		
    		boolean flag = true;
    		for (int i = dep; i > k; i--) {
    			if (arr[i] != arr[i - l]) {
    				flag = false;
    				break;
    			}
    		}
    		
    		if (flag) {
    			arr[dep] = 0;
        		return false;
    		}
    	}
    	
    	arr[dep] = 0;
    	return true;
    }
}