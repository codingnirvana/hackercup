import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class BalancedSmileys {

    public static final char EMPTY = '\0';
    Scanner in;
    PrintWriter out;
    private String line;

    public void solve() throws IOException {
        int tn = in.nextInt();
        in.nextLine();
        for (int test = 1; test <= tn; test++) {
            String line = in.nextLine();
            String ans = calculate(line);
            out.println("Case #" + test + ": " + ans);
        }
    }

    private String calculate(String line) {
        this.line = line;
        return isValid(0, State.VALID, 0) ? "YES" : "NO";
    }

    private boolean isValid(int index, State current, int open) {
        if (index > line.length() - 1) {
           return current == State.VALID && open == 0;
        }

        char at = line.charAt(index);
        char next = index == line.length() - 1 ? EMPTY : line.charAt(index + 1);

        if ((at >= 'a' && at <= 'z') || at == ' ') {
            return isValid(index + 1, current, open);
        }

        if (at == ':' && (next == '(' || next == ')')) {
            return isValid(index + 1, current, open) || isValid(index + 2, current, open);
        }

        if (at == ':') {
            return isValid(index + 1, current, open);
        }

        if (at == '(') {
            return isValid(index + 1, State.OPEN_P, open + 1);
        }

        if (at == ')') {
            return isValid(index + 1, State.VALID, open - 1);
        }

        return false;
    }

    private enum State {
        VALID,
        OPEN_P
    }

    public void run() {
        try {
            in = new Scanner(BalancedSmileys.class.getResourceAsStream("/resources/balanced_smileys.txt"));
            out = new PrintWriter(new File("/tmp/balanced_smileys.out"));

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new BalancedSmileys().run();
    }
}
