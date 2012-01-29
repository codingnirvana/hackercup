import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Checkpoint {
    Scanner in;
    PrintWriter out;

    private static final int ROW = 100;
    private static final int COLUMN = 10000;
    static long[][] DP = new long[ROW][COLUMN];

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
        long res = s + 1;
        for (int i = 1; i <= Math.sqrt(s); i++) {
            if (s % i != 0)
                continue;
            res = Math.min(res, find(i, s/i));
        }

        return res;
    }

    private long find(int first, int second) {
        ArrayList<Pair> F = new ArrayList<Pair>();
        ArrayList<Pair> S = new ArrayList<Pair>();
        
        long res = Integer.MAX_VALUE;
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COLUMN; j++)
                if (DP[i][j] == first) {
                    F.add(new Pair(i, j));
                }
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COLUMN; j++)
                if (DP[i][j] == second) {
                    S.add(new Pair(i, j));
                }
        for (Pair f : F) {
            for (Pair s : S) {
                res = Math.min(res, s.X + f.X + s.Y + f.Y);
            }
        }

        return res;
    }
    
    public class Pair{
        Pair(int X, int Y) {
            this.X = X;
            this.Y = Y;
        }
        public long X;
        public long Y;
    }

    private static void generate() {
        for (int i = 0; i < ROW; i++)
            Arrays.fill(DP[i], -1);
        DP[0][0] = 0;
        for (int i = 1; i < COLUMN; i++)
            DP[0][i] = 1;
        for (int i = 1; i < ROW; i++)
            DP[i][0] = 1;
        
        for (int i = 1; i < ROW; i++)
            for (int j = 1; j < COLUMN; j++)
                DP[i][j] = DP[i-1][j] + DP[i][j-1];

       // pascal(ROW - 1, COLUMN - 1);
    }

    private long pascal(int r, int c) {
        if (DP[r][c] != -1)
            return DP[r][c];
        long res = pascal(r - 1, c) + pascal(r, c - 1);
        return DP[r][c] = res;
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
