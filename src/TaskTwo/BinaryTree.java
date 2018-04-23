package TaskTwo;

import java.io.PrintStream;

public class BinaryTree<K extends Comparable, V> {
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

    public void delete(K key){
        root = delete(root, key);
    }

    private Node delete(Node x, K key){
        if(x == null) return null;
        int compare = key.compareTo(x.getData().getKey());

        if(compare < 0) x.setLeft(delete(x.getLeft(), key));
        else if(compare > 0) x.setRight(delete(x.getRight(), key));
        else {
            if (x.getRight() == null) return x.getLeft();
            if (x.getLeft() == null) return x.getRight();

            Node t = x;
            x = min(t.getRight());
            x.setRight(deleteMin(t.getRight()));
            x.setLeft(t.getLeft());
        }
        return x;
    }

    public K min(){
        return min(root).getData().getKey();
    }

    private Node<K, V> min(Node x){
        if(x.getLeft() == null) return x;
        return min(x.getLeft());
    }

    private Node deleteMin(Node x) {
        if (x.getLeft() == null) return x.getRight();
        x.setLeft(deleteMin(x.getLeft()));
        return x;
    }

    public String toDot(){
        StringBuilder sb = new StringBuilder("digraph G {\n");
        toDot(root, sb);
        sb.append("}");
        return sb.toString();
    }

    private void toDot(Node node, StringBuilder sb) {
        if(node == null) return;
        toDot(node.getLeft(), sb);
        node.toDot(sb);
        toDot(node.getRight(), sb);
    }
}
