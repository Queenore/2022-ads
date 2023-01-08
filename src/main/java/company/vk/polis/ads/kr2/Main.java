package company.vk.polis.ads.kr2;

public class Main {

    public static void main(String[] args) {
        //bst
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.put(4, 2);
        bst.put(2, 1);
        bst.put(6, 2);
        bst.put(3, 1);

        //graph
        // 5   6
        //  \ /
        //   1
        //  /
        // 2 --- 3 --- 4
        Graph graph = new Graph(6, 5);
        graph.addConnection(1, 5);
        graph.addConnection(1, 6);
        graph.addConnection(1, 2);
        graph.addConnection(2, 3);
        graph.addConnection(3, 4);
        System.out.println(graph.contains(4));
    }
}
