import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AlphabetSoup {

    Scanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int tn = in.nextInt();
        in.nextLine();
        for (int test = 1; test <= tn; test++) {
            String line = in.nextLine();
            int ans = calculate(line);
            out.println("Case #" + test + ": " + ans);
        }
    }

    private int calculate(String line) {
        String s = "HAKERUP";
        int res = 0;
        int[] counts = new int[26];
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') continue;
            int pos =  line.charAt(i) - 'A';
            counts[pos]++;
        }
        int minOthers = Integer.MAX_VALUE;
        
        for (int i =0; i < s.length(); i++) {
            int pos = s.charAt(i) - 'A';
            if (counts[pos] < minOthers) {
                minOthers = counts[pos];
            }
        }
        
        if (counts['C' -'A'] >=  minOthers*2) {
            res = minOthers;
        } else {
            res = Math.min(counts['C' - 'A']/2, minOthers);
        }


        return res;
    }

    public void run() {
        try {
            in = new Scanner(AlphabetSoup.class.getResourceAsStream("/resources/alphabet_soup.txt"));
            out = new PrintWriter(new File("/tmp/alphabet_soup.out"));

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new AlphabetSoup().run();
    }
}
