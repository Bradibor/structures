package TaskThree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Test {
    static int coinsArraySize = 3;
    static int maxAmount = 100;
    static long maxWaitTime = 5000;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MinCoinsSolver brute = new BruteForce();
        MinCoinsSolver dynamic = new DynamicProgramming();

        ExecutorService bruteExecutor = Executors.newFixedThreadPool(10);
        ExecutorService dynamicExecutor = Executors.newFixedThreadPool(10);

        List<Future<MinCoinsSolver.Result>> solutionList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            int[] coins = generateCoins();
//            int amount = generateAmount();
//            Future<MinCoinsSolver.Result> future = bruteExecutor.submit(new BruteForce().setTask(coins, amount));
//            solutionList.add(future);
//        }
        for (int i = 0; i < 10; i++) {
            int[] coins = generateCoins();
            int amount = generateAmount();
            Future<MinCoinsSolver.Result> future = dynamicExecutor.submit(new DynamicProgramming().setTask(coins, amount));
            solutionList.add(future);
        }
        while (!solutionList.stream().allMatch(Future::isDone)) {
            if(System.currentTimeMillis() - startTime > maxWaitTime) {
                solutionList.forEach(future -> {
                    if (!future.isDone())
                        future.cancel(true);
                });
            };
        }



    }

    static Random r = new Random(System.currentTimeMillis());

    static int[] generateCoins() {
        return IntStream.generate(() -> r.nextInt(maxAmount)).distinct().filter(n -> n > 0).limit(coinsArraySize).sorted().toArray();
    }

    static int generateAmount() {
        return Math.abs(r.nextInt(maxAmount));
    }
}
