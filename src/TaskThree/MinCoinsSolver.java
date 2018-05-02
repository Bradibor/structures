package TaskThree;

import java.util.*;
import java.util.concurrent.Callable;

public abstract class MinCoinsSolver implements Callable {

    private int[] coins;
    private int amount;
    protected int[] coinsSolution;

    protected abstract void beforeSolve();

    public Result solve(int[] coins, int amount) {
        coinsSolution = null;
        beforeSolve();
        Result result = new Result();
        long startTime = System.currentTimeMillis();
        int res = 0;
        try {
            res = minCoins(coins, amount);
        } catch (StackOverflowError e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - startTime;
        if(res == Integer.MAX_VALUE) {
            result.time = time;
            result.solved = false;
        }
        if(res == 0) result.time = time;
        else {
            result.result = res;
            result.time = time;
            result.solved = true;
            result.coinsSolution = coinsSolution;
        }
        result.coins = coins;
        result.amount = amount;
        return result;
    }

    public MinCoinsSolver setTask(int[] coins, int amount) {
        this.coins = coins;
        this.amount = amount;
        return this;
    }

    abstract int minCoins(int[] coins, int amount);

    @Override
    public Result call() {
        Result result = solve(coins, amount);
        System.out.println(result);
        return result;
    }

    class Result {
        public int[] coins;
        public int amount;
        public int result;
        public long time;
        public boolean solved;
        public int[] coinsSolution;

        Result(int result, long time, int[] coinsSolution) {
            this.result = result;
            this.time = time;
            this.solved = true;
            this.coinsSolution = coinsSolution;
        }

        Result(long time){
            this.time = time;
            this.solved = false;
        }

        Result() {  }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("For coins: "+Arrays.toString(coins)+"\nAmount: " + amount + "\n");
            if (solved)
                sb.append("Result: " + result + "\n" +
                        "With Coins: " + Arrays.toString(coinsSolution) + "\n" +
                        "Time: " + time + "\n");
            else if (time == 0)
                sb.append("Too long to solve\n");
            else sb.append("No solution\n");
            return sb.toString();
        }
    }
}
