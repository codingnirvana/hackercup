import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class BeautifulStrings {

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
        int res = 0;
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < line.length(); i++) {
           String c = String.valueOf(line.charAt(i)).toLowerCase();
           if (c.charAt(0) < 'A' || c.charAt(0) > 'z') {
                continue;
           }
           if (map.containsKey(c)) {
              map.put(c, map.get(c) + 1);
           } else {
               map.put(c, 1);
           }
        }
        Integer[] keys = map.values().toArray(new Integer[0]);
        Arrays.sort(keys);

        for (int i = 0; i < keys.length; i++) {
            res += keys[i]* (26 - (keys.length - 1) + i);
        }

        return res;
    }

    public void run() {
        try {
            in = new Scanner(BeautifulStrings.class.getResourceAsStream("/resources/beautiful_strings.txt"));
            out = new PrintWriter(new File("/tmp/beautiful_strings.out"));

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new BeautifulStrings().run();
    }
}
