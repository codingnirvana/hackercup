import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

class CardGame1 {
    static final long MODULO = (long) (1e9 + 7);

    static final long[] inv;

    static {
        inv = new long[10001];
        for (int i = 1; i < inv.length; ++i)
            inv[i] = BigInteger.valueOf(i).modInverse(BigInteger.valueOf(MODULO)).longValue();
    }

    public long solve(int n, int k, Long[] a) {

        Arrays.sort(a);
        long oldprod = 0;
        long prod = 1;
        long res = 0;
        for (int i = k; i <= a.length; ++i) {
            res = (res + (prod - oldprod) * (a[i - 1] % MODULO)) % MODULO;
            if (res < 0) res += MODULO;
            oldprod = prod;
            prod = prod * (i + 1) % MODULO * inv[i - k + 1] % MODULO;
        }
        if (res < 0 || res >= MODULO) throw new RuntimeException();
        return res;
    }
}