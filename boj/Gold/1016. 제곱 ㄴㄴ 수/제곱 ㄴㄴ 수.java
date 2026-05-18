import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long start = sc.nextLong();
		long fin = sc.nextLong();
		
		boolean [] arr = new boolean[(int) (fin - start + 1)];
		
		
		for (long i = 2; i <= Math.sqrt(fin); i++) {
			long a = start / (i * i) * (i * i);
			if (a != start) a += i * i;
			
			while (a <= fin) {
				arr[(int) (a - start)] = true;
				a += i * i;
			}
		}
		int ans = 0;
		for (boolean b : arr) {
			if(!b) ans++;
		}
		
		System.out.println(ans);
	}

}
