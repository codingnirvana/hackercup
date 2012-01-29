
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class StudiosStudent {

    Scanner in;
    PrintWriter out;

    private void solve() {
        int tn = in.nextInt();
        in.nextLine();
        for (int test = 1; test <= tn; test++) {
            int n = in.nextInt();
            String[] words = new String[n];
            for (int j = 0; j < n; j++) {
                words[j] = in.next();
            }
            String ans = calculate(words);
            out.println("Case #" + test + ": " + ans);
        }
    }

    private String calculate(String[] words) {
        Arrays.sort(words,new Comparator<String>() {
            @Override
            public int compare(String first, String second) {
                return first.compareTo(second);                
            }
        });
        return StringUtils.join(words);
    }


    public void run() {
        try {
            in = new Scanner(StudiosStudent.class.getResourceAsStream("/resources/studios_student.txt"));
            out = new PrintWriter(new File("/tmp/studios_student.out"));

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new StudiosStudent().run();
    }
}
