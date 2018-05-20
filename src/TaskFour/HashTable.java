package TaskFour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntSupplier;

public class HashTable {
    private Data[] hashArray;
    private int size;
    private HashFunction hashFunction;
    public Statistic statistic;

    public HashTable(int n, HashFunction hashFunction) {
        this.hashArray = new Data[n];
        this.hashFunction = hashFunction;
        this.statistic = new Statistic();
    }

    public boolean insert(Data data) {

        //hash func
        int index = this.hashFunction.hash(data.key, hashArray.length);
        //collision resolve
        int hops = 0;
        while (hashArray[index] != null && !hashArray[index].key.equals(data.key)) {
            index++;
            if (++hops > hashArray.length) {
                return false;
            }
            if (index >= hashArray.length) index -= hashArray.length;
        }
        hashArray[index] = data;
        return true;
    }

    public void remove(String key) {
        int index = hashFunction.hash(key, hashArray.length);
        int hops = 0;
        while (hashArray[index] == null || !hashArray[index].key.equals(key)) {
            index++;
            if (++hops > hashArray.length) {
                return;
            }
            if (index >= hashArray.length) index -= hashArray.length;
        }
        hashArray[index] = null;
    }

    public Data find(String key) {
        int index = hashFunction.hash(key, hashArray.length);
        int hops = 0;
        while (hashArray[index] == null || !hashArray[index].key.equals(key)) {
            index++;
            if (++hops > hashArray.length) {
                statistic.add(hops);
                return null;
            }
            if (index >= hashArray.length) index -= hashArray.length;
        }
        statistic.add(hops);
        return hashArray[index];
    }

    @Override
    public String toString() {

        return Arrays.toString(hashArray);
    }

    public static interface HashFunction {
        int hash(String k, int size);
    }

    public class Statistic {
        public List<Integer> hopsList;

        public Statistic() {
            this.hopsList = new ArrayList<>();
        }

        public void add(int hops) {
            hopsList.add(hops);
        }

        public void reset() {
            hopsList.clear();
        }

        public double getAvg() {
            return hopsList.stream().mapToInt(Integer::intValue).average().getAsDouble();
        }
    }
}
