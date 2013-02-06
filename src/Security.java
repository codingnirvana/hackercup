
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Security {

    Scanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int tn = in.nextInt();
        in.nextLine();
        for (int test = 1; test <= tn; test++) {
            int m = Integer.parseInt(in.nextLine());
            String k1 = in.nextLine();
            String k2 = in.nextLine();
            String ans = calculate(m, k1, k2);
            out.println("Case #" + test + ": " + ans);
        }
    }

    String K1,K2;
    int M;
    int N;
    String ans;
    private String calculate(int m, String k1, String k2) {
        this.N = k1.length();
        this.M = N/m;
        this.ans = "";
       this.K1 = k1;
       this.K2 = k2;

       rec("","",0);

       return ans.equals("") ? "IMPOSSIBLE" : ans;
    }


    private void rec(String k1, String k2, int n) {
        if (!ans.equals("")) {
            return;
        }
        if (n == N) {
            return;
        }
        if (areEqual(k1,k2)) {
           ans = k1;
           return;
        }

        if (K1.charAt(n) != '?' && K2.charAt(n) != '?') {
            rec(k1 + Character.toString(K1.charAt(n)) ,k2 + Character.toString(K2.charAt(n)), n + 1);
        }

        if (K1.charAt(n) == '?' && K2.charAt(n) == '?') {
            for (char c1 = 'a'; c1 < 'f'; c1++) {
                for (char c2 = 'a'; c2 < 'f'; c2++) {
                    rec(k1 + Character.toString(c1),k2 + Character.toString(c2),n + 1);
                }
            }
        }

        if (K1.charAt(n) == '?') {
            for (char c = 'a'; c < 'f'; c++) {
                rec(k1 + Character.toString(c), k2 + Character.toString(K2.charAt(n)), n + 1);
            }
        }
        if (K2.charAt(n) == '?') {
            for (char c = 'a'; c < 'f'; c++) {
                rec(k1 + Character.toString(K1.charAt(n)), k2 + Character.toString(c), n + 1);
            }
        }
    }


    private boolean areEqual(String k1, String k2) {
        if (k1.length() < this.N) {
            k1 += this.K1.substring(k1.length());
            k2 += this.K2.substring(k2.length());
        }

        List<String> first = new ArrayList<String>();
        List<String> second = new ArrayList<String>();
        for (int i = 0; i < k1.length(); i += this.M) {
            first.add(k1.substring(i, i + this.M));
            second.add(k2.substring(i, i + this.M));
        }
        Collections.sort(first);
        Collections.sort(second);
        for (int i = 0; i < first.size(); i++) {
            if (!first.get(i).equals(second.get(i))) {
                return false;
            }
        }
        return true;
    }



    public void run() {
        try {
            in = new Scanner(Security.class.getResourceAsStream("/resources/security.txt"));
            out = new PrintWriter(new File("/tmp/security.out"));

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new Security().run();
    }
}
