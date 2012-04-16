package bitwise;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProblemA {

    Scanner in;
    PrintWriter out;
    boolean[][] friends;
    boolean[] visited;
    int N;
    int res = 0;

    public void run() {
        in = new Scanner(new InputStreamReader(System.in));;
        out = new PrintWriter(new OutputStreamWriter(System.out));

        solve();

        in.close();
        out.close();
    }

    private void solve() {
        int tn = in.nextInt();
        for (int test = 1; test <= tn; test++) {
            N = in.nextInt();
            friends = new boolean[N][N];
            in.nextLine();
            for (int i =0; i < N; i++) {
               String[] split = in.nextLine().split(" ");
                for (int j = 1; j < split.length; j++) {
                    int pair = Integer.parseInt(split[j]);
                    friends[i][pair - 1] = true;
                }
            }
            out.println(calc());
        }
    }

    private String calc() {
        visited = new boolean[N];
        makePairs();
        return res == 1? "YES": "NO";            
    }
    
    
    void makePairs() {
        if (res > 1)
            return;
        
        boolean allVisited = true;
        for (int i = 0; i < N; i++) {
            allVisited &= visited[i];            
        }
        
        if (allVisited) {
            res++;
            return;
        }
        
        for (int index = 0; index < N; index++)
            for (int i = 0; i < N; i++) {
                if (friends[index][i] && !visited[i]) {
                    visited[index] = true;
                    visited[i] = true;
                    makePairs();
                    visited[index] = false;
                    visited[i] = false;
                }
            }
    }

    public static void main(String[] arg) {
        new ProblemA().run();
    }
}
