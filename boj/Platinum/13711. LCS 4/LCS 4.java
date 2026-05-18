import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] arr;
	static int[] seq;
	static int[] lis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		seq = new int[n + 1];
		arr = new int[n + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) seq[Integer.parseInt(st.nextToken())] = i;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) arr[i] = seq[Integer.parseInt(st.nextToken())];
		
		lis = new int[n + 1];
		
		int size = 0;
		for (int i = 1; i <= n; i++) {
			if (lis[size] < arr[i]) lis[++size] = arr[i];
			else {
				int idx = Arrays.binarySearch(lis, 0, size, arr[i]); 
				if (idx < 0) idx = -(idx + 1);
				lis[idx] = arr[i];
			}
		}
		System.out.println(size);
		
	}
}