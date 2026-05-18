
import java.util.*;
import java.io.*;

public class Main {
    static List<Integer> crane = new ArrayList<>();
    static List<Integer> box = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            crane.add(sc.nextInt());
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            box.add(sc.nextInt());
        }
        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());

        if (crane.get(0) < box.get(0)) System.out.println(-1);
        else {
            int time = 0;
            while (!box.isEmpty()) {
                int j = 0;
                for (int i = 0; i < n;) {
                    if (j == box.size()) break;
                    else if (crane.get(i) >= box.get(j)) {
                        i++;
                        box.remove(j);
                    } else j++;
                }
                time++;
            }
            System.out.println(time);
        }
    }
}

