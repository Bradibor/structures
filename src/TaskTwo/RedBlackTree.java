package TaskTwo;

public class RedBlackTree<K extends Comparable, V> extends BinaryTree<K, V> {

    public RedBlackTree() {

    }

    private ColorNode rotateLeft(ColorNode h){
        if(!ColorNode.isRed(h.getRight())) throw new IllegalArgumentException("Right Node must be RED in order to left rotate");
        ColorNode x = h.getRight();
        h.setRight(x.getLeft());
        x.setLeft(h);
        x.setRed(ColorNode.isRed(h));
        h.setRed(true);
        return x;
    }

    private ColorNode rotateRigth(ColorNode h){
        if(!ColorNode.isRed(h.getLeft())) throw new IllegalArgumentException("Left Node must be RED in order to right rotate");
        ColorNode x = h.getLeft();
        h.setLeft(x.getRight());
        x.setRight(h);
        x.setRed(ColorNode.isRed(h));
        h.setRed(true);
        return x;
    }

    private void flipColors(ColorNode h){
        if(ColorNode.isRed(h)) throw new IllegalArgumentException("H must be BLACK. "+h);
        if(!ColorNode.isRed(h.getLeft())) throw new IllegalArgumentException("Left child must be RED");
        if(!ColorNode.isRed(h.getRight())) throw new IllegalArgumentException("Right child must be RED");
        h.setRed(true);
        h.getRight().setRed(false);
        h.getLeft().setRed(false);
    }

    @Override
    public void add(K key, V value) {
        if(root == null) root = new ColorNode<K,V>(key, value, false);
        else root = this.add((ColorNode)root, key, value);
    }

    private ColorNode add(ColorNode h, K key, V value){
        if(h == null) return new ColorNode(key, value, true);

        int cmp = key.compareTo(h.getData().getKey());
        if (cmp < 0 ) h.setLeft(add(h.getLeft(), key, value));
        else if (cmp > 0) h.setRight(add(h.getRight(), key, value));
        else if (cmp == 0) h.getData().setValue(value);

        if(h==root) h.setRed(false);
        if(ColorNode.isRed(h.getRight()) && !ColorNode.isRed(h.getLeft())) h = rotateLeft(h);
        if(ColorNode.isRed(h.getLeft()) && ColorNode.isRed(h.getLeft().getLeft())) h = rotateRigth(h);
        if(ColorNode.isRed(h.getLeft()) && ColorNode.isRed(h.getRight())) flipColors(h);

        return h;
    }
}
