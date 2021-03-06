package TaskTwo;

import java.io.PrintStream;
import java.util.Random;

public class Node<K, V> {
    private Data<K,V> data;
    public Node left;
    public Node right;

    public Node(K key, V value) {
        this.data = new Data<K, V>(key, value);
    }

    public Node getLeft(){
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Data<K, V> getData(){
        return data;
    }

    protected void printHook(StringBuilder sb){

    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public void toDot(StringBuilder sb) {
        Random rnd = new Random();
        sb.append("\"" + data.getKey() + "\" -> " );
        if(left != null) sb.append("\"" + left.data.getKey() + "\";\n");
        else {
            int nullId = rnd.nextInt();
            sb.append(nullId+";\n"+nullId+"[label=\"null\"]\n");
        }
        sb.append("\"" + data.getKey() + "\" -> " );
        if(right != null) sb.append("\"" + right.data.getKey() + "\";\n");
        else {
            int nullId = rnd.nextInt();
            sb.append(nullId+";\n"+nullId+"[label=\"null\"]\n");
        }
        printHook(sb);
    }
}
