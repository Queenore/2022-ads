package company.vk.polis.ads.kr1;

import static java.lang.Math.pow;

public class Task4 {
    private final static int C = 0;
    private final static double EPS = 1.0E-5;

    static double f(double x) {
        return pow(x, 5) + pow(x, 2) + 10 - C;
    }

    static double binarySearch() {
        double left = -1000;
        double right = 1000;
        double mid = (left + right) / 2;
        while (f(right) - f(left) > EPS) {
            mid = (left + right) / 2;
            if (f(mid) < 0) {
                left = mid;
            } else{
                right = mid;
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch());
    }
}
