import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int n;
	static int[] arr;
	static int[] prefix;
	static int res;
	static int max;
		
	public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	arr = new int[n + 1];
    	prefix = new int[n + 1];
    	st = new StringTokenizer(br.readLine());
    	
    	for (int i = 1; i <= n; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    		prefix[i] = prefix[i - 1] + arr[i];
    		
    		if (i != 1 && i != n) max = Math.max(max, arr[i]);
    	}
    	
    	res = prefix[n] - arr[1] - arr[n] + max;
    	
    	for (int i = 2; i <= n - 1; i++) {
    		int k = prefix[n] - prefix[1] - arr[i];
    		k += prefix[n] - prefix[i];
    		
    		res = Math.max(res, k);
    		
    		k = prefix[n] - arr[n] - arr[i];
    		k += prefix[i - 1];
    		
    		res = Math.max(res, k);
    	}
    	
    	System.out.println(res);
    }
}