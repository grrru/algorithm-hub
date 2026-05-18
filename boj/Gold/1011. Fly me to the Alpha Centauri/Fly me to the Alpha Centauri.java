import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dist = - Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
            int max = (int) Math.sqrt(dist);
            System.out.println(2 * max - 1 + (dist - max * max + max - 1) / max);
        }
    }
}