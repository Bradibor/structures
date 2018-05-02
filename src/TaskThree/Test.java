package TaskThree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Test {
    static int coinsArraySize = 20;
    static int maxAmount = 100;
    static long maxWaitTime = 5000;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MinCoinsSolver brute = new BruteForce();
        MinCoinsSolver dynamic = new DynamicProgramming();

        ExecutorService bruteExecutor = Executors.newFixedThreadPool(10);

        List<Future<MinCoinsSolver.Result>> solutionList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            int[] coins = generateCoins();
            int amount = generateAmount();
            Future<MinCoinsSolver.Result> future = bruteExecutor.submit(new BruteForce().setTask(coins, amount));
            //future.get();
            solutionList.add(future);
        }
        while(System.currentTimeMillis() - startTime < maxWaitTime){

        }
        solutionList.forEach(future->{
            System.out.println("Time out");
            if(!future.isDone()) future.cancel(true);
        });

//        solutionList.stream().map((future)->{
//            MinCoinsSolver.Result result = null;
//            try {
//                result = future.get();
//                System.out.println(result);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//            return result;
//        });

    }

    static Random r = new Random(System.currentTimeMillis());

    static int[] generateCoins() {
        return IntStream.generate(() -> r.nextInt(maxAmount)).distinct().filter(n -> n > 0).limit(coinsArraySize).sorted().toArray();
    }

    static int generateAmount() {
        return Math.abs(r.nextInt(maxAmount));
    }
}
