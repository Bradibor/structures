package TaskOne;


import java.util.Random;

public class AutoTest {
    public static void main(String[] args) {

        //test order
        Random rnd = new Random(System.currentTimeMillis());
        LinkedList list = new LinkedList();
        int[] keys = new int[1000];
        for(int i = 0; i < 1000; i++){
            keys[i] = rnd.nextInt();
            list.add(keys[i], i);
        }
        LinkedList.Iterator iterator = list.getIterator();
        while (iterator.hasNext()){
            Data data1 = iterator.next();
            if(!iterator.hasNext()) break;
            if(data1.getKey().compareTo(iterator.next().getKey()) > 0) {
                System.out.println("test 1 failed");
                return;
            };
        }
        System.out.println("test 1 passed");

        //test delete
        list = new LinkedList();
        int N = 1000;
        for(int i = 0; i < N; i++){
            list.add(i, i+"lola");
        }
        for(int i = 0; i < N; i++){
            list.delete(0);
        }
        if(list.getSize() != 0) {
            System.out.println("test 2 failed");
            return;
        }
        System.out.println("test 2 passed");

    }
}
