import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class RecoverTheSequence {
    Scanner in;
    PrintWriter out;

    int count = 0;
    String S;
    private void solve() {
        int tn = in.nextInt();
        in.nextLine();
        for (int test = 1; test <= tn; test++) {
            int n = in.nextInt();
            in.nextLine();
            String sequence = in.nextLine();
            int ans = calculate(n, sequence);           
            out.println("Case #" + test + ": " + ans);
        }
    }

    private int calculate(int n, String sequence) {
        count = 0;
        S = sequence;
        Integer[] a = new Integer[n];
        for (int i = 0; i < a.length; i++)
            a[i] = i + 1;
        Integer[] integers = merge_sort_solution(a);
        for (int i = 0; i < a.length; i++) {
            a[integers[i] - 1] = i + 1;
        }
        System.out.println(String.format("%s - %s", checksum(a), Arrays.toString(integers)));
        return checksum(a);
    }


    private Integer[] merge_sort_solution(Integer[] a) {
        int n = a.length;
        if (n <= 1)
            return a;
        
        int mid = (int)Math.floor(n/2);

        Integer[] first = Arrays.copyOfRange(a, 0, mid);
        Integer[] first_half = merge_sort_solution(first);
        Integer[] second = Arrays.copyOfRange(a, mid, n);
        Integer[] second_half = merge_sort_solution(second);
        return merge2(first_half, second_half);
        
    }

    private Integer[] merge_sort(Integer[] a) {
        int n = a.length;
        if (n <= 1)
            return a;

        int mid = (int)Math.floor(n/2);

        Integer[] first = Arrays.copyOfRange(a, 0, mid);
        Integer[] first_half = merge_sort(first);
        Integer[] second = Arrays.copyOfRange(a, mid, n);
        Integer[] second_half = merge_sort(second);
        return merge(first_half, second_half);

    }

    private Integer[] merge(Integer[] first_half, Integer[] second_half) {
        List<Integer> result = new ArrayList<Integer>();
        List<Integer> arr1 = new ArrayList<Integer>(Arrays.asList(first_half));
        List<Integer> arr2 = new ArrayList<Integer>(Arrays.asList(second_half));

        while(arr1.size() > 0 && arr2.size() > 0) {
            if (arr1.get(0) < arr2.get(0)) {
                System.out.print("1");
                //System.out.println(String.format("%s - %s %s", 1,arr1.get(0), arr2.get(0)));
                result.add(arr1.get(0));
                arr1.remove(arr1.get(0));
            }   else {
                System.out.print("2");
                //System.out.println(String.format("%s - %s %s", 2,arr1.get(0), arr2.get(0)));
                result.add(arr2.get(0));
                arr2.remove(arr2.get(0));
            }
        }
        result.addAll(arr1);
        result.addAll(arr2);
        return result.toArray(first_half);
    }


    private Integer[] merge2(Integer[] first_half, Integer[] second_half) {
        List<Integer> result = new ArrayList<Integer>();
        List<Integer> arr1 = new ArrayList<Integer>(Arrays.asList(first_half));
        List<Integer> arr2 = new ArrayList<Integer>(Arrays.asList(second_half));

        while(arr1.size() > 0 && arr2.size() > 0) {
            if (S.charAt(count) == '1') {
                result.add(arr1.get(0));
                arr1.remove(arr1.get(0));
                count++;
            }   else {
                result.add(arr2.get(0));
                arr2.remove(arr2.get(0));
                count++;
            }
        }
        result.addAll(arr1);
        result.addAll(arr2);
        return result.toArray(first_half);
    }

    /**
     * Works with <a href='http://en.wikipedia.org/wiki/Permutation'>permutations</a>
     */
    public static class Permutations {
        /**
         * Accepts an array of <b>ints</b> and reorders it's elements
         * to recieve lexicographically next permutation
         *
         * @param p permutation
         * @return false, if given array is lexicographically last permutation, true otherwise
         */
        public static boolean nextPermutation(int[] p) {
            int a = p.length - 2;
            while (a >= 0 && p[a] >= p[a + 1]) {
                a--;
            }
            if (a == -1) {
                return false;
            }
            int b = p.length - 1;
            while (p[b] <= p[a]) {
                b--;
            }
            int t = p[a];
            p[a] = p[b];
            p[b] = t;
            for (int i = a + 1, j = p.length - 1; i < j; i++, j--) {
                t = p[i];
                p[i] = p[j];
                p[j] = t;
            }
            return true;
        }
    }


    private int checksum(Integer[] a){
        int res = 1;
        for (int i = 0; i < a.length; i++) {
            res = (31*res + a[i])%1000003;
        }
        return res;
    }


    public void run() {
        try {
            in = new Scanner(RecoverTheSequence.class.getResourceAsStream("/resources/recover_the_sequence.txt"));
            out = new PrintWriter(new File("/tmp/recover_the_sequence.out"));

//            merge_sort_solution(new Integer[] {2,3,5,1,4});
//            int[] a = new int[] {1,2,3,4,5};
//
//            while (Permutations.nextPermutation(a)) {
//                System.out.println(String.format("%s - %s", checksum(a), Arrays.toString(a)));
//            }
//            int limit = 10000;
//            Integer[] a = new Integer[limit];
//            for (int i = 0; i < limit; i++)
//                a[i] = i + 1;
//
//            int r = (Math.abs(new Random().nextInt()))%100000003;
//            while (r > 0) {
//                Permutations.nextPermutation(a);
//                r--;
//            }
//            System.out.println(String.format("%s - %s", checksum(a), Arrays.toString(a)));
//            merge_sort(a);

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
