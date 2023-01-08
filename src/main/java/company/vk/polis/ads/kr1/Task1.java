package company.vk.polis.ads.kr1;

public class Task1 {
    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 5, 2, 34, 17, 7, 1, 123, 11, 2};
        solve(array, array.length);
    }

    public static void solve(int[] a, int size) {
        int[] d = new int[size];
        int[] path = new int[size];
        int restoreIndex = Integer.MIN_VALUE;
        int pos = -1;

        for (int i = 0; i < size; i++) {
            d[i] = 1;
            path[i] = -1;
            for (int j = 0; j < size; j++) {
                if (a[i] > a[j]) {
                    if (d[i] < d[j] + 1) {
                        path[i] = j;
                        d[i] = d[j] + 1;
                        if (restoreIndex < j) {
                            restoreIndex = j;
                            pos = i;
                        }
                    }
                }
            }
        }

        //restore path
        if (pos > 0) {
            System.out.println(a[pos]);
            while (restoreIndex >= 0) {
                System.out.println(a[restoreIndex]);
                restoreIndex = path[restoreIndex];
            }
        }

    }

}
