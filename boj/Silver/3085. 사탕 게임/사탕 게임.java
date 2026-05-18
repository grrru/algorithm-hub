import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
    static int n;
    static char[][] arr;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (arr[i][j] != arr[i][j + 1]) {
                    swap(i, j, i, j + 1);
                    check();
                    swap(i, j, i, j + 1);
                }
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != arr[i + 1][j]) {
                    swap(i, j, i + 1, j);
                    check();
                    swap(i, j, i + 1, j);
                }
            }
        }

        System.out.println(max);
    }

    static void swap(int r1, int c1, int r2, int c2) {
        char k = arr[r1][c1];
        arr[r1][c1] = arr[r2][c2];
        arr[r2][c2] = k;
    }

    static void check() {
        for (int i = 0; i < n; i++) {
            int cnt = 1;
            
            for (int j = 0; j < n - 1; j++) {
                if (arr[i][j] == arr[i][j + 1]) cnt++;
                else {
                    max = Math.max(max, cnt);
                    cnt = 1;
                }
            }
            max = Math.max(max, cnt);
        }

        for (int j = 0; j < n; j++) {
            int cnt = 1;
            
            for (int i = 0; i < n - 1; i++) {
                if (arr[i][j] == arr[i + 1][j]) cnt++;
                else {
                    max = Math.max(max, cnt);
                    cnt = 1;
                }
            }
            max = Math.max(max, cnt);
        }
    }
}