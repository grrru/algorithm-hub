import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] arr;
	static int[] temp;
	static long ans;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());
		temp = new int[n + 1];

		mergeSort(1, n);
		System.out.println(ans);
	}

	static void mergeSort(int s, int e) {
		if (s == e) return;

		int mid = (s + e) / 2;

		mergeSort(s, mid);
		mergeSort(mid + 1, e);

		merge(s, mid, e);
	}

	static void merge(int s, int m, int e) {
		for (int i = s; i <= e; i++) {
            temp[i] = arr[i];
        }

		int l = s;
		int r = m + 1;

		int idx = s;

		while (l <= m && r <= e) {
			if (temp[l] > temp[r]) {
				arr[idx++] = temp[r++];
				ans += m - l + 1;
			} else {
				arr[idx++] = temp[l++];
			}
		}

		while (l <= m) arr[idx++] = temp[l++];
		while (r <= e) arr[idx++] = temp[r++];
	}
}