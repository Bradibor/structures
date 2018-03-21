package TaskOne;

public class Data<K extends Comparable,V> {
    private K key;
    private V value;

    public Data(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) throws IllegalArgumentException{
        if(this.value.getClass() != value.getClass()) throw new IllegalArgumentException("Value of type: "+value.getClass().getSimpleName() +
                " can not be set for Data<"+key.getClass().getSimpleName()+","+ this.value.getClass().getSimpleName()+">");
        this.value = value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"[" + key + ", " + value + "]";
    }

    public static void main(String[] args) {
        Data data1 = new Data(1, "First");
        System.out.println(data1.getKey());
        System.out.println(data1.getValue());
        System.out.println(data1.toString());
        data1.setValue(true);
        System.out.println(data1);
    }
}
