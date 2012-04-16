import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Checkpoint {
    Scanner in;
    PrintWriter out;
        
    private static final int MAX = 10000002;
    private static final int ROW = 4500;
    private static final int COLUMN = 4500;
    static long[][] C = new long[ROW][COLUMN];
    static long[] BEST = new long[MAX];

    private void solve() {
        int tn = in.nextInt();
        in.nextLine();
        for (int test = 1; test <= tn; test++) {
            int s = in.nextInt();
            long ans = calculate(s);
            out.println("Case #" + test + ": " + ans);
        }
    }

    private long calculate(int s) {
        long res = Integer.MAX_VALUE;
        for (int i = 1; i <= Math.sqrt(s); i++) {
            if (s % i != 0)
                continue;
            res = Math.min(res, BEST[i] + BEST[s/i]);
        }

        return res;
    }

    private static void generate() {
        for (int i = 0; i < ROW; i++)
            Arrays.fill(C[i], -1);
        for (int i = 0; i < MAX; i++)
            BEST[i] = i;
        
        for (int i = 1; i < COLUMN; i++)
            C[0][i] = 1;
        for (int i = 1; i < ROW; i++)
            C[i][0] = 1;
        
        for (int i = 1; i < ROW; i++)
            for (int j = 1; j < COLUMN; j++) {
                C[i][j] = C[i-1][j] + C[i][j-1];
                if (C[i][j] > MAX) C[i][j] = MAX;
                if (C[i][j] > 0 && C[i][j] < MAX) {
                    BEST[(int) C[i][j]] = Math.min((int) C[i][j], BEST[(int) C[i][j]]);
                }
            }
    }

    public void run() {
        try {
            in = new Scanner(Checkpoint.class.getResourceAsStream("/resources/checkpoint.txt"));
            out = new PrintWriter(new File("/tmp/checkpoint.out"));

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        Checkpoint.generate();
        new Checkpoint().run();
    }
}
