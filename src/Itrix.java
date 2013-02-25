import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Itrix{

    public static void main(String[] args) {

        System.out.println(new Itrix().question3(new int[]{103, 24, 77, 65, 12, 108, 69, 25, 66, 83}));
        System.out.println(new Itrix().question3(new int[]{83, 112, -16, 72, 161, 75, 152, -23, 77, 247}));
        System.out.println(new Itrix().question3(new int[]{19, 81, 2, 41, 61, 59, 28, 69, 76, 88}));


        System.out.println(Arrays.toString(new Itrix().question8(5, 76)));
        System.out.println(Arrays.toString(new Itrix().question8(7, 4197)));
        System.out.println(Arrays.toString(new Itrix().question8(9, 191082)));

        System.out.println(new Itrix().question6(8));
        System.out.println(new Itrix().question6(9));
        System.out.println(new Itrix().question6(10));
        System.out.println(new Itrix().question6(12));
        System.out.println(new Itrix().question6(14));
        System.out.println(new Itrix().question6(15));
        System.out.println(new Itrix().question6(16));
        System.out.println(new Itrix().question6(17));
        System.out.println(new Itrix().question6(20));

        System.out.println(new Itrix().question7(400));
        System.out.println(new Itrix().question7(411));
        System.out.println(new Itrix().question7(421));
        System.out.println(new Itrix().question7(1000));
        System.out.println(new Itrix().question7(1100));
        System.out.println(new Itrix().question7(1111));
        System.out.println(new Itrix().question7(1245));
        System.out.println(new Itrix().question7(1255));
        System.out.println(new Itrix().question7(1500));

    }

    private int question7(int n) {
        int[] N = new int[n + 1];
        N[0] = 1;
        N[1] = 2;

        int i = 1;
        int k = 2;

        while (i <= n) {
            for (int j = 1; j <= N[k-1] && i <=n; j++) {
                N[i++] = k;
            }
            k++;
        }
        return N[n];
    }

    private long question6(int n) {
        maxLcm = Integer.MIN_VALUE;
        rec(new ArrayList<Integer>(), n);
        return maxLcm;
    }

    long maxLcm;

    void rec(List<Integer> numbers, int left) {
        if (left == 0) {
            maxLcm = Math.max(maxLcm, getLCM(numbers));
            return;
        }
        for (int i = 1; i <= left; i++) {
            numbers.add(new Integer(i));
            rec(numbers, left - i);
            numbers.remove(new Integer(i));
        }
    }

    private long getLCM(List<Integer> numbers) {
        return lcm(numbers.toArray(new Integer[0]));
    }

    private static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }

    private static long lcm(Integer[] input)
    {
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }

    private static long gcd(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    private int[] question8(int n, int k) {

        int[] A = new int[n];

        for (int i = 0; i < A.length; i++) {
            A[i] = i + 1;
        }

        for (int i = 1; i < k; i++) {
            RecoverTheSequence.Permutations.nextPermutation(A);
        }
        return A;
    }

    private int question3(int[] n) {
        int res = Integer.MAX_VALUE;
        do {
           int maxWeight = Integer.MIN_VALUE;
           for (int i =0; i < n.length - 1; i+=2) {
                maxWeight = Math.max(maxWeight, n[i] + n[i+1]);
           }
           res = Math.min(res, maxWeight);

        } while (RecoverTheSequence.Permutations.nextPermutation(n));

        return res;
    }

}
