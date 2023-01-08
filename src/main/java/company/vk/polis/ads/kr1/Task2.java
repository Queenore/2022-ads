package company.vk.polis.ads.kr1;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private static void mergeSort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(array, fromInclusive, mid);
        mergeSort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
    }

    private static void merge(int[] array, int fromInclusive, int mid, int toExclusive) {
        int halfIndex1 = fromInclusive;
        int halfIndex2 = mid;
        List<Integer> tempList = new ArrayList<>();
        while (halfIndex1 < mid || halfIndex2 < toExclusive) {
            if (halfIndex1 < mid &&
                    (halfIndex2 >= toExclusive || array[halfIndex1] < array[halfIndex2])) {
                tempList.add(array[halfIndex1]);
                halfIndex1++;
            } else {
                tempList.add(array[halfIndex2]);
                halfIndex2++;
            }
        }
        for (int i = 0; i < (toExclusive - fromInclusive); i++) {
            int currIndex = fromInclusive + i;
            array[currIndex] = tempList.get(i);
        }
    }


    public static void main(String[] args) {
        int size = 7;
        int[] array = new int[]{12, 23, 11, 2, 12, 323, 1};
        mergeSort(array, 0, size);
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }


}
