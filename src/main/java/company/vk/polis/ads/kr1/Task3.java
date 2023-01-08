package company.vk.polis.ads.kr1;

import java.util.ArrayList;

public class Task3 {
    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(12);
        heap.insert(134);
        heap.insert(7);
        heap.insert(8);
        heap.insert(2);
        heap.insert(1);
    }

    public static class MinHeap<T extends Comparable<T>> {
        private final ArrayList<T> arr = new ArrayList<>();
        private int n = 0;
        private int size = 0;

        public int getSize() {
            return size;
        }

        public void insert(T value) {
            while (arr.size() <= n * 2 + 1) {
                arr.add(null);
            }
            arr.set(++n, value);
            swim(n);
            size++;
        }

        public void swim(int k) {
            while (k > 1 && arr.get(k) != null && arr.get(k).compareTo(arr.get(k / 2)) < 0) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void swap(int k, int j) {
            T temp = arr.get(k);
            arr.set(k, arr.get(j));
            arr.set(j, temp);
        }
    }
}
