package TaskFour;

public class Data {
    public final String key;
    public final int value;

    public Data(String key,int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "["+key+", "+value + "]";
    }
}
