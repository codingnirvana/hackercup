import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class CardGame {

    Scanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int tn = in.nextInt();
        in.nextLine();
        for (int test = 1; test <= tn; test++) {
            String[] first = in.nextLine().split(" ");
            String[] second = in.nextLine().split(" ");
            Long[] a = new Long[second.length];

            for (int i = 0; i < a.length; i++) {
                a[i] = Long.parseLong(second[i]);
            }
             long ans = calculate(Integer.parseInt(first[0]), Integer.parseInt(first[1]), a);
            //long ans = new CardGame1().solve(Integer.parseInt(first[0]), Integer.parseInt(first[1]), a);
            out.println("Case #" + test + ": " + ans);
        }
    }

    int MOD = 1000000007;

    private long calculate(int n, int k, Long[] a) {
        long res = 0;
        Arrays.sort(a);
        if (n == k)
            return a[n-1];

        for (int i = k - 1; i < n; i++) {
            res = (res + (a[i]* c(i,k-1)%MOD)%MOD)%MOD;
        }

        return res;
    }

    long c(int n, int k)
    {
        long res = 1;

        for (int i = 1, j = n; i <= k; ++i, --j) {
            res = (res *j) % MOD;
            res = (res/ i);
        }

        return res;
    }


    public void run() {
        try {
            in = new Scanner(CardGame.class.getResourceAsStream("/resources/card_game.txt"));
            out = new PrintWriter(new File("/tmp/card_game.out"));

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new CardGame().run();
    }
}
