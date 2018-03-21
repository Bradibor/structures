package TaskTwo;

public class ColorNode<K extends Comparable, V> extends Node{
    private boolean color;
    public ColorNode(K key, V value) {
        super(key, value);
        color = false;
    }

    public ColorNode(Object key, Object value, boolean color) {
        super(key, value);
        this.color = color;
    }

    public static boolean isRed(ColorNode x){
        if(x == null) return false;
        return x.color;
    }

    public void setRed(boolean color){
        this.color = color;
    }

    @Override
    public ColorNode getLeft() {
        return (ColorNode) super.getLeft();
    }

    @Override
    public String toString() {
        return "ColorNode{" +
                "color=" + color +
                '}';
    }

    @Override
    public ColorNode getRight() {
        return (ColorNode) super.getRight();
    }

    @Override
    public void setLeft(Node left) {
        super.setLeft(left);
    }

    @Override
    public void setRight(Node right) {
        super.setRight(right);
    }

    private static String getColorAsString(ColorNode x){
        if(x==null) return "black";
        return x.color ? "red": "black";
    }

    @Override
    protected void printHook(StringBuilder sb) {
        int index = sb.lastIndexOf(";");
        sb.insert(index, "[color="+getRight().getColorAsString(this.getRight())+"]");
        index = index--;
        sb.insert(sb.lastIndexOf(";",index ), "[color="+getLeft().getColorAsString(this.getLeft())+"]");
    }
}
