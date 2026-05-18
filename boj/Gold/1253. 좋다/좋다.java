import java.util.*;
import java.io.*;

public class Main {
  
    static int n;
    static int[] arr;
  
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      
      n = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      
      arr = new int[n];
      for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
      Arrays.sort(arr);

      int ans = 0;
      for (int i = 0; i < n; i++) {
        int s = 0;
        int e = n - 1;

        while (s < e) {
          if (s == i) s++;
          else if (e == i) e--;
          else {
            int k = arr[s] + arr[e];
            if (k > arr[i]) e--;
            else if (k < arr[i]) s++;
            else {
              ans++;
              break;
            }
          }
        }
      }

      System.out.println(ans);
  }
}