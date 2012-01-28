import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Billboards {

    Scanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int tn = in.nextInt();
        in.nextLine();
        for (int test = 1; test <= tn; test++) {
            String line = in.nextLine();
            Scanner lineScanner = new Scanner(line);
            int width = lineScanner.nextInt();
            int height = lineScanner.nextInt();
            String s = "";
            while (lineScanner.hasNext()) {
                s += " " + lineScanner.next();
            }
            int ans =  calculate(width, height, s.trim());
            out.println("Case #" + test + ": " + ans);
        }
    }

    private int calculate(int width, int height, String line) {
        int res = 0;
        String[] text = line.split(" ");        
        for (int i = Math.min(width, height); i >= 1; i--) {
            if (doesfit(width, height, i, text))
                return i;
        }
        return 0;
    }

    private boolean doesfit(int width, int height, int size, String[] text) {
        int currentRow = size;
        int currentColumn = 0;
        int i = 0;
        for (; i < text.length; i++) {
            if (currentColumn + text[i].length()*size <= width ) {
                currentColumn += text[i].length()*size;
                if (currentColumn + size > width) {
                    currentColumn = 0;
                    if (i < text.length - 1)
                        currentRow += size;
                } else {
                    currentColumn += size;
                }
            } else {
                if (currentColumn == 0) {
                    break;
                }

                i--;
                currentColumn = 0;
                currentRow += size;               
            }
        }
        if (currentRow > height || i < text.length)
            return false;
        
        return true;
    }

    public void run() {
        try {
            in = new Scanner(Billboards.class.getResourceAsStream("/resources//billboards.txt"));
            out = new PrintWriter(new File("/tmp/billboards.out"));

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new Billboards().run();
    }
}
