import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	
	static int n;
	static PriorityQueue<Str> pq;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str;
        pq = new PriorityQueue<>();
        
        while ((str = br.readLine()) != null && !str.equals("")) {
        	pq.add(new Str(str));
        }

        StringBuilder sb = new StringBuilder();
        
        while (!pq.isEmpty()) {
        	sb.append(pq.poll().s);
        }
        
        System.out.println(sb);
    }
    
    static class Str implements Comparable<Str> {
    	String s;
    	
    	public Str(String s) {
    		this.s = s;
    	}

		@Override
		public int compareTo(Str o) {
			
			if (this.s.equals(o.s)) return 0;
			
			String str1 = this.s.concat(o.s);
			String str2 = o.s.concat(this.s);
			
			for (int i = 0; i < str1.length(); i++) {
				if (str1.charAt(i) - str2.charAt(i) != 0) {
					return str2.charAt(i) - str1.charAt(i);
				}
			}
			
			return 0;
		}

		@Override
		public String toString() {
			return "Str [s=" + s + "]";
		}
    }
}