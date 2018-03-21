package TaskOne;

import java.util.Comparator;

public class LinkedList<K extends Comparable, V> {
    private Item<K,V> head;
    private Item<K,V> current;
    private Item<K,V> tail;
    private int size;
    private Iterator iterator;

    private K getKey(){
        return current.getData().getKey();
    }

    public Iterator getIterator() {
        return iterator;
    }

    public class Iterator {
        public Iterator(){ };
        public boolean hasNext(){
            return LinkedList.this.hasNext();
        }

        public Data next(){
            return LinkedList.this.next().getData();
        }
    }


    public <K,V>LinkedList(){
        tail = current = head = null;
        size = 0;
        iterator = new Iterator();
    };

    public int getSize(){
        return size;
    }

    public Data[] asArray(){
        Data[] datas = new Data[size];
        for(int i = 0; hasNext(); i++){
            datas[i] = next().getData();
        }
        return datas;
    }

    private boolean hasNext(){
        if(current == null && !isEmpty()) return true;
        if(current == null && isEmpty()) return false;
        if(current.getNext() != null) return true;
        return false;
    }

    private boolean hasPrevious(){
        if(current == null && !isEmpty()) return true;
        if(current == null && isEmpty()) return false;
        if(current.getPrev() != null) return true;
        return false;
    }

    private Item next(){
        if(!hasNext()) throw new NullPointerException();
        if(current == null) return current = head;
        return current = current.getNext();
    }

    private Item prev(){
        if(!hasPrevious()) throw new NullPointerException();
        if(this.current == null) {
            return current = tail;
        }
        return current = current.getPrev();
    }

    private boolean isEmpty(){
        return head == null;
    }

    private void checkIndex(int index){
        if(index > size - 1 || index < 0) throw new IllegalArgumentException("Index '"+index+"' out of bound. List size: "+this.size);
    }

    public void add(K key, V value){
        Item temp = current;
        if(isEmpty()) {
            head = tail = new Item(key,value);
            current = null;
            size++;
            return;
        }
        current = head;
        while(hasNext() && (key.compareTo(getKey())>0)) next();
        int c = key.compareTo(getKey());
        if(c == 0) throw new IllegalArgumentException("KEY '"+key+"' already exists in the list. Only unique keys are accepted"); //no duplicates
        if(!hasNext() && c >= 0) {
                current.setNext(new Item(key, value).setPrev(current)); //insert after
                tail = next();
        }else{
            if (!hasPrevious()) {
                current.setPrev(new Item(key, value).setNext(current));  //insert before
                head = prev();
            }else{
                current.getPrev().setNext(current.setPrev(new Item(key, value).setNext(current).setPrev(current.getPrev())).getPrev());
            }
        }
        size++;
        current = temp;
    }

    public void delete(int index){
        Item temp = current;
        current = null;
        checkIndex(index);
        int counter = -1;
        if(index >= size/2){ //start from tail
            while(++counter != size -1 - index) prev();
            if(counter == 0) {
                prev();
                if(!hasPrevious()){
                    head = tail = null;
                    size--;
                    return;
                }else{
                    tail = prev().setNext(null);
                }
            }else{
                current.getPrev().getPrev().setNext(current.setPrev(current.getPrev().getPrev()));
            }
        } else {    //start from head
            while(++counter != index) next();
            if(counter == 0){
                next();
                head = next().setPrev(null);
            }else{
                current.getNext().getNext().setPrev(current.setNext(current.getNext().getNext()));
            }
        }
        size--;
        current = temp; //unexpected behavior if current is deleted
    }

    public V getByKey(K key){
        Item temp = current;
        current = null;
        while(hasNext()){
            if(next().getData().getKey().equals(key)) return (V) current.getData().getValue();
        }
        current = temp;
        throw new IllegalArgumentException("Key '" + key + " ' not found");
    }

    public Data getByIndex(int index) {
        Item temp = current;
        current = null;
        checkIndex(index);
        int counter = 0;
        if(index >= size/2) { //start from tail
            while(counter++ != size -1 - index) prev();
            current = temp;
            return prev().getData();
        } else { //start from head
            while(counter++ != index) next();
            current = temp;
            return next().getData();
        }
    }

    @Override
    public String toString() {
        Item temp = current;
        current = null;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (hasNext()){
            sb.append(i++ + ". " + next() + "; ");
        }
        current = temp;
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(1, "one");
        list.add(5, "five");
        list.add(3, "three");
        list.add(2, "two");
        list.add(10, "one");
        list.add(25, "five");
        list.add(14, "three");
        list.add(12, "two");


        System.out.println(list);
        list.delete(3);
        System.out.println(list);
        System.out.println(list.getByIndex(-1));

    }
}