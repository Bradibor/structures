package TaskTwo;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Map<String, Double> tree  = new LinkedHashMap<>();
        tree.put("Amazona", 1d);
        tree.put("Brah", 2d);
        tree.put("Gucci", 3d);
        tree.put("Kraven", 4d);
        tree.put("br", 4d);
        tree.put("asdf", 4d);
        tree.put("vcdsaf", 4d);
        tree.put("ds", 4d);
        tree.put("ds", 4d);
        tree.put("c", 4d);
        tree.put("f", 4d);
        tree.put("h", 4d);
        tree.put("w", 4d);
        tree.put("d", 4d);
        tree.put("b", 4d);
        tree.put("x", 4d);
        tree.put("o", 4d);
        tree.put("w", 4d);
        tree.put("n", 4d);
        tree.put("r", 4d);
        tree.put("j", 4d);
        tree.put("v", 4d);
        tree.put("h", 4d);

        List<Map.Entry<String, Double>> list = new ArrayList<>(tree.entrySet());


        try{
            for(int i = 0; i < 10000; i++){
                BinaryTree<String, Double> myTree = new RedBlackTree<>();
                list.forEach((Map.Entry<String, Double> e)->{
                    myTree.add(e.getKey(), e.getValue());
                   // myTree.toGraph(System.out);
                    Collections.shuffle(list);
                });
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
    }
}
