package TaskThree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Test {
    static int coinsArraySize = 200;
    static int maxAmount = 1000;
    static long maxWaitTime = 1000;

    public static void main(String[] args) {
        MinCoinsSolver brute = new BruteForce();
        MinCoinsSolver dynamic = new DynamicProgramming();

//        int[] coins = new int[] {1,5,10};
//        int amount = 23;


        for (int i = 0; i < 20; i++) {
            int[] coins = generateCoins();
            int amount = generateAmount();
            System.out.println("For coins: " + Arrays.toString(coins) +
                        "\nWith amount: " + amount);
            long startTime = System.currentTimeMillis();
//            Thread dynamicThread = new Thread(() -> {
////                MinCoinsSolver.Result result = dynamic.solve(coins, amount);
////                System.out.println("For coins: " + Arrays.toString(coins) +
////                        "\nWith amount: " + amount + "\n" + result);
//            });
//            Thread bruteThread = new Thread(() -> {
//                System.out.println("For coins: " + Arrays.toString(coins) +
//                        "\nWith amount: " + amount + "\n" + brute.solve(coins, amount));
//            });

            Thread bruteThread = new BruteForce().setTask(coins, amount);
            bruteThread.start();
            bruteThread.interrupt();
            System.out.println(bruteThread.isAlive());
            while ( bruteThread.isAlive()) {
                System.out.println(System.currentTimeMillis() - startTime );
                if (System.currentTimeMillis() - startTime > maxWaitTime) {
                    System.out.println("interrupted");

                    bruteThread.interrupt();
                }
            }
        }
    }

    static int[] generateCoins() {
        Random r = new Random(System.currentTimeMillis());
        return IntStream.generate(() -> r.nextInt(maxAmount)).distinct().filter(n -> n > 0).limit(coinsArraySize).sorted().toArray();
    }

    static int generateAmount() {
        Random r = new Random(System.currentTimeMillis());
        return Math.abs(r.nextInt(maxAmount));
    }
}
