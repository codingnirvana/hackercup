package bitwise;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProblemD {

    Scanner in;
    PrintWriter out;
    int M;
    Map<Pair, Long> map = new HashMap<Pair, Long>();
    
    public void solve() {
        int tn = in.nextInt();
        M = in.nextInt();
        in.nextLine();
        for (int test = 1; test <= tn; test++) {
            int n = in.nextInt();
            int k = in.nextInt();
            out.println(calculate(n, k));
        }
    }

    private long calculate(int n, int k) {
        return (pow(3,pow(n,k)%M))%M;
    }
    
    private long pow(long a, long b) {
        Pair pair = new Pair(a,b);
        if (map.get(pair) != null) {
            return map.get(pair);            
        }
        long res;
        if (b == 0) {
            res = 1;
        } else if (b % 2 == 0) {
            res = ((pow(a, b / 2) % M) * (pow(a, b / 2) % M)) % M;
        }
        else {
            res = (a * pow(a, b - 1)) % M;
        }
        map.put(pair, res);
        return res;
    }
    
    class Pair {
        public long a;
        public long b;
        Pair(long a, long b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (a != pair.a) return false;
            if (b != pair.b) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (int) (a ^ (a >>> 32));
            result = 31 * result + (int) (b ^ (b >>> 32));
            return result;
        }
    }

    public void run() {
        in = new Scanner(new InputStreamReader(System.in));;
        out = new PrintWriter(new OutputStreamWriter(System.out));

        solve();

        in.close();
        out.close();
    }

    public static void main(String[] arg) {
        new ProblemD().run();
    }
}
