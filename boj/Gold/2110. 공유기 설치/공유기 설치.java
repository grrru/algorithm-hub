import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    static int n;
    static int c;
    static int[] arr;

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
         
         n = Integer.parseInt(st.nextToken());
         c = Integer.parseInt(st.nextToken());
         
         arr = new int[n];
         for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());
         Arrays.sort(arr);

         
         int start = 0;
         int end = 1_000_000_000;
         int ans = 0;
         
         while (start <= end) {
        	 int mid = (start + end) / 2;
        	 
        	 if (prob(mid)) {
        		 ans = mid;
        		 start = mid + 1;
        	 } else {
        		 end = mid - 1;
        	 }
         }
         
         System.out.println(ans);
    }

	private static boolean prob(int mid) {

		int cnt = n - c;
		int[] copy = Arrays.copyOf(arr, arr.length);
		
		for (int i = 0; i < n - 1; i++) {
			if (copy[i + 1] - copy[i] < mid) {
				if (cnt == 0) return false;
				
				cnt--;
				copy[i + 1] = copy[i];
			}
		}
		
		return true;
	}
}