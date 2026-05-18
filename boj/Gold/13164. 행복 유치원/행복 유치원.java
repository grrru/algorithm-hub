import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	
	static int n;
	static int k;
	static int[] arr;
	static int[] diff;
    
	public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	arr = new int[n];
    	
    	st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
    	
    	int res = arr[n - 1] - arr[0];
    	
    	diff = new int[n - 1];
    	for (int i = 0; i < n - 1; i++) diff[i] = arr[i] - arr[i + 1];
    	Arrays.sort(diff);
    	
    	for (int i = 0; i < k - 1; i++) res += diff[i];
    	
    	System.out.println(res);
    }
}