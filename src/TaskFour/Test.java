package TaskFour;

import java.util.Random;

public class Test {

    public static String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        int targetStringLength = random.nextInt(3) + 4;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        final int tableSize = 100;
        HashTable[] hashTables = new HashTable[]{
                new HashTable(tableSize, (key, n) -> {
                    return key.length() % n;
                }),
                new HashTable(tableSize, (key, n) -> {
                    int hash = 0;
                    for (char c : key.toCharArray()) {
                        hash = 31 * hash + c;
                    }
                    return (int) Math.abs(hash) % n;
                }),
                new HashTable(tableSize, (key, n) -> {
                    //k = all possible values
                    long k = 0;
                    char[] chars = key.toCharArray();
                    for (int i = 0; i < chars.length; i++) {
                        if (chars[i] > 1000)
                            throw new IllegalArgumentException("Symbol '" + chars[i] + "' unsupported for hashing");
                        k += chars[i] * Math.pow(1000, i);
                    }
                    return (int) (k % n);
                })
        };

        String[] randomStrings = new String[100];
        for(int i = 0; i < 100; i++){
            randomStrings[i] = generateRandomString();
        }

        for (HashTable hashTable : hashTables) {
            for(int i = 0; i < 100; i++){
                hashTable.insert(new Data(randomStrings[i], 5));
            }
            for(int i = 0; i < 100; i++){
                hashTable.find(randomStrings[i]);
            }
            System.out.println(hashTable.statistic.getAvg());
        }
    }
}
