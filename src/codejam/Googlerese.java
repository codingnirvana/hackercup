package codejam;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Googlerese {
    Scanner in;
    PrintWriter out;

    Map<Character,Character> map = new HashMap<Character, Character>();

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
       String ans = "";
       for (int i = 0; i < line.length(); i++) {
           ans += map.get(line.charAt(i));
       }
       return ans;
    }

    private void buildMap() {
        String[] input = new String[] {
            "ejp mysljylc kd kxveddknmc re jsicpdrysi",
            "rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd",
            "de kr kd eoya kw aej tysr re ujdr lkgc jv"
        };

        String[] output = new String[] {
          "our language is impossible to understand",
          "there are twenty six factorial possibilities",
          "so it is okay if you want to just give up"
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                if (!map.containsKey(input[i].charAt(j)))
                    map.put(input[i].charAt(j),output[i].charAt(j));
            }
        }

        map.put('z','q');
        map.put('q','z');

//        for (int i = 0; i < 26;  i++) {
//            System.out.print((char)('a' + i));
//            System.out.println(map.get((char)('a' + i)));
//        }
    }

    public void run() {
        try {
            in = new Scanner(Googlerese.class.getResourceAsStream("/resources/A-small-attempt2.in"));
            out = new PrintWriter(new File("/tmp/ProblemA.out"));

            buildMap();
            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new Googlerese().run();
    }
}
