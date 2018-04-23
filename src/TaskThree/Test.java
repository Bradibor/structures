package TaskThree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Test {
    static int coinsArraySize = 20;
    static int maxAmount = 100;
    static long maxWaitTime = 5000;

    public static void main(String[] args) {
        MinCoinsSolver brute = new BruteForce();
        MinCoinsSolver dynamic = new DynamicProgramming();

//        int[] coins = new int[] {1,5,10};
//        int amount = 23;


        for(int i = 0; i < 10; i++){
            int[] coins = generateCoins();
            int amount = generateAmount();
            long startTime = System.currentTimeMillis();
            Thread dynamicThread = new Thread(()->{
//                MinCoinsSolver.Result result = dynamic.solve(coins, amount);
//                System.out.println("For coins: " + Arrays.toString(coins) +
//                        "\nWith amount: " + amount + "\n" + result);
            });
            Thread bruteThread = new Thread(()->{
                System.out.println("For coins: " + Arrays.toString(coins) +
                        "\nWith amount: " + amount + "\n" + brute.solve(coins, amount));
            });
            dynamicThread.start();
            bruteThread.start();
            while (dynamicThread.isAlive() && bruteThread.isAlive()) {
                if(System.currentTimeMillis() - startTime > maxWaitTime) {
                    dynamicThread.interrupt();
                    bruteThread.interrupt();
                }
            }
        }
    }

    static int[] generateCoins() {
        Random r = new Random(System.currentTimeMillis());
        return IntStream.generate(()->r.nextInt(maxAmount)).distinct().filter(n -> n>0).limit(coinsArraySize).sorted().toArray();
    }

    static int generateAmount() {
        Random r = new Random(System.currentTimeMillis());
        return Math.abs(r.nextInt(maxAmount));
    }
}
