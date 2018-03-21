package TaskTwo;

import java.io.PrintStream;

public abstract class BinaryTree<K extends Comparable, V> {
    protected Node<K, V> root;

    public void add(K key, V value){
        if(root == null) {
            root = new Node(key, value);
            return;
        }
        Node current = root;
        while(true){
            int compare = key.compareTo(current.getData().getKey());
            if(compare < 0) {
                if(current.getLeft() == null) {
                    current.setLeft(new Node(key, value));
                    return;
                }
                current = current.getLeft();
                continue;
            }
            if(compare > 0) {
                if(current.getRight() == null) {
                    current.setRight(new Node(key, value));
                    return;
                }
                current = current.getRight();
                continue;
            }
            if(compare == 0) throw new IllegalArgumentException();
        }
    }

    public V get(K key){
        Node current = root;

        while (current != null){
            int compare = key.compareTo(current.getData().getKey());
            if(compare == 0) return (V) current.getData().getValue();
            else if(compare < 0) current = current.getLeft();
            else if (compare > 0) current = current.getRight();
        }
        throw new IllegalArgumentException("No such value");
    }

    public BinaryTree(){
        this.root = null;
    }

    public void toGraph(PrintStream stream){
        stream.println("digraph G {\n");
        StringBuilder sb = new StringBuilder();
        toGraph(root, sb);
        stream.println(sb.toString());
        stream.println("}");
    }

    private void toGraph(Node node, StringBuilder sb) {
        if(node == null) return;
        toGraph(node.getLeft(), sb);
        node.toString(sb);
        toGraph(node.getRight(), sb);
    }
}
