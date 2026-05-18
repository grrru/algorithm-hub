import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int s = Integer.parseInt(br.readLine());

    Queue<Emo> queue = new ArrayDeque<>();
    queue.offer(new Emo(1, 0, 0));

    boolean[][] visit = new boolean[1025][1025];

    while (!queue.isEmpty()) {
      Emo cur = queue.poll();

      if (cur.clipBoard != 0) {

        if (cur.cnt + cur.clipBoard == s) {
          System.out.println(cur.time + 1);
          break;
        }

        if (cur.cnt + cur.clipBoard < 1025 && !visit[cur.cnt + cur.clipBoard][cur.clipBoard]) {
          queue.offer(new Emo(cur.cnt + cur.clipBoard, cur.clipBoard, cur.time + 1));
          visit[cur.cnt + cur.clipBoard][cur.clipBoard] = true;
        }
        
      }

      if (!visit[cur.cnt][cur.cnt]) {
        queue.offer(new Emo(cur.cnt, cur.cnt, cur.time + 1));
        visit[cur.cnt][cur.cnt] = true;
      }
      

      if (cur.cnt > 0) {
        if (cur.cnt - 1 == s) {
          System.out.println(cur.time + 1);
          break;
        }

        if (!visit[cur.cnt - 1][cur.clipBoard]) {
          queue.offer(new Emo(cur.cnt - 1, cur.clipBoard, cur.time + 1));
          visit[cur.cnt - 1][cur.clipBoard] = true;
        }
      }
    }
  }

  static class Emo {
    int cnt;
    int clipBoard;
    int time;

    public Emo(int cnt, int clipBoard, int time) {
      this.cnt = cnt;
      this.clipBoard = clipBoard;
      this.time = time;
    }
  }
}