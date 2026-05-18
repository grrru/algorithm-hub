import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		String str = br.readLine();
		
		int[] arr = new int[26];
		
		for (int i = 0; i < str.length(); i++) arr[str.charAt(i) - 'A']++;
		
		int[] brr = new int[26];
		
		int ans = 0;
		while (n-- > 1) {
			str = br.readLine();
			for (int i = 0; i < str.length(); i++) brr[str.charAt(i) - 'A']++;
			
			if (check(arr, brr)) ans++;
			
			Arrays.fill(brr, 0);
		}
		
		System.out.println(ans);
	}
	
	static boolean check(int[] arr, int[] brr) {
		
		int minus = 0;
		int plus = 0;
		
		for (int i = 0; i < 26; i++) {
			if (Math.abs(arr[i] - brr[i]) >= 2) return false;
			
			if (arr[i] == brr[i]) continue;
			
			if (arr[i] - brr[i] == 1) {
				if (++plus == 2) return false;
			}
			else {
				if (++minus == 2) return false;
			}
		}
		
		return (plus + minus == 0) || (minus == 1 && plus == 1) || (plus + minus == 1);
	}
}