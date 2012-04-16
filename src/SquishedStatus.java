import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SquishedStatus {
    Scanner in;
    PrintWriter out;

    String M;
    int N;
    static long MOD = 4207849484L;
    long[][] DP = new long[1200][300];

    private void solve() {
        int tn = in.nextInt();
        in.nextLine();
        for (int test = 1; test <= tn; test++) {
            int n = in.nextInt();
            String message = in.next();
            long ans = calculate(n, message);
            out.println("Case #" + test + ": " + ans);
        }
    }

    private long calculate(int n, String message) {
        this.N = n;
        this.M = message;
        for (int i =0 ; i < 1200; i++)
            Arrays.fill(DP[i], -1);
        return rec(0, 0) % MOD;
    }

    private long rec(int i, int current) {
        if (DP[i][current] != -1)
            return DP[i][current];

        if (i >= M.length()) {
            return 1;
        }
        Integer x = M.charAt(i) - '0';
        int next = 10 * current + x;

        long res = 0;

        if (next > 0 && next <= N) {
            res = rec(i + 1, next)%MOD;
        }

        if (i > 0 && x != 0 && x <= N) {
            res = (res + rec(i + 1, x))%MOD;
        }

        return DP[i][current] = res%MOD;
    }


    public void run() {
        try {
            in = new Scanner(SquishedStatus.class.getResourceAsStream("/resources/squished_status.txt"));
            out = new PrintWriter(new File("/tmp/squished_status.out"));

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new SquishedStatus().run();
    }
}
