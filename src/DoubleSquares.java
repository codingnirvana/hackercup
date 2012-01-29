import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DoubleSquares {

    Scanner in;
    PrintWriter out;
    private final static double EPSILON = 0.00001;

    private void solve() {
        int tn = in.nextInt();
        in.nextLine();
        for (int test = 1; test <= tn; test++) {
            int n = in.nextInt();
            int ans = calculate(n);
            out.println("Case #" + test + ": " + ans);
        }
    }

    private int calculate(int n) {
        int res = 0;
        for (int i = 0; i <= Math.sqrt(n); i++) {
            if (isSquare(n - i * i)) {
                res++;
            }
        }
        return (res + 1)/2;
    }

    private boolean isSquare(int i) {
        double sqrt = Math.sqrt(i);
        if (Math.abs(Math.floor (sqrt) - sqrt) < EPSILON || Math.abs(Math.floor (sqrt) - sqrt) < EPSILON) {
            return true;
        }
        return false;
    }

    public void run() {
        try {
            in = new Scanner(DoubleSquares.class.getResourceAsStream("/resources//double_squares.txt"));
            out = new PrintWriter(new File("/tmp/double-squares.out"));

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new DoubleSquares().run();
    }
}
