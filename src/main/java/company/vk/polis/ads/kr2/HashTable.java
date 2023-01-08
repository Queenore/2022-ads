package company.vk.polis.ads.kr2;

import java.util.Objects;

public class HashTable<Key extends Comparable<Key>, Value> {

    private class Pair {
        private Key key;
        private Node node;
    }

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node root = null;
    private Pair[] array;
    private Integer capacity;

    public Value get(Key key) {
        int startPos = Objects.hash(key) % capacity;
        Node node = array[startPos].node;
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

}
