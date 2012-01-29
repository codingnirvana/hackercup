import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class RecoverTheSequence {
    Scanner in;
    PrintWriter out;

    private void solve() {
        int tn = in.nextInt();
        in.nextLine();
        for (int test = 1; test <= tn; test++) {
            int n = in.nextInt();
            String sequence = in.nextLine();
            int ans = calculate(n, sequence);           
            out.println("Case #" + test + ": " + ans);
        }
    }

    private int calculate(int n, String sequence) {
        return 0;
    }


    public void run() {
        try {
            in = new Scanner(RecoverTheSequence.class.getResourceAsStream("/resources/recover_the_sequence.txt"));
            out = new PrintWriter(new File("/tmp/recover_the_sequence.out"));

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new RecoverTheSequence().run();
    }
}
