package company.vk.polis.ads.kr2;

public class Avl<Key extends Comparable<Key>, Value> {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    private Node root = null;

//    private int factor(Node x) {
//        return height(x.left) - height(x.right);
//    }
//
//    private Node balance(Node x) {
//        if (factor(x) == 2) {
//            if (factor(x.left) < 0) {
//                x.left = rotateLeft(x.left);
//            }
//            return rotateRight(x);
//        }
//        if (factor(x) == -2) {
//            if (factor(x.right) > 0) {
//                x.right = rotateRight(x.right);
//            }
//            return rotateLeft(x);
//        }
//        return x;
//    }

}
