package TaskOne;

public class Item<K extends Comparable,V> {
    private Data<K,V> data;
    private Item next;
    private Item prev;

    public Item(K key, V value){
        this.data = new Data(key, value);
        next = prev = null;
    }

    public Data<K, V> getData() {
        return data;
    }

    public Item getNext() {
        return next;
    }

    public Item setNext(Item next) throws IllegalArgumentException{
        try{
            this.validateDatatypes(next, this);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Can not set "+next+" after "+this + ". Different datatypes!");
        }catch (NullPointerException e){

        }
        this.next = next;
        return this;
    }

    public Item getPrev() {
        return prev;
    }

    public Item setPrev(Item prev) throws IllegalArgumentException{
        try{
            this.validateDatatypes(prev, this);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Can not set "+prev+" before "+this +". Different datatypes!");
        }
        this.prev = prev;
        return this;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + data.getKey() + ", " + data.getValue() + "]";
    }

    public static void validateDatatypes(Item item1, Item item2) throws IllegalArgumentException{
        if(item1 == null || item2 == null) return;
        if(item1.data.getKey().getClass() != item2.data.getKey().getClass() || item1.data.getValue().getClass() != item2.data.getValue().getClass()) throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        Item item1 = new Item(12, "Cool!");
        Item item2 = new Item("kek", 3);
        item1.setNext(item2);

    }
}
