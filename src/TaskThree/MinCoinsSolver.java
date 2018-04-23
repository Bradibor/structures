package TaskThree;

import java.util.*;

public abstract class MinCoinsSolver extends Thread{

    private int[] coins;
    private int amount;
    protected int[] coinsSolution;

    protected abstract void beforeSolve();

    public Result solve(int[] coins, int amount) {
        beforeSolve();
        long startTime = System.currentTimeMillis();
        try{
            int result = minCoins(coins, amount);
            return result == Integer.MAX_VALUE? new Result() : new Result(result, System.currentTimeMillis() - startTime, coinsSolution) ;
        }catch (StackOverflowError e){
            return new Result();
        }catch (OutOfMemoryError e){
            return new Result();
        }
    }

    @Override
    public synchronized void start() {
        Result result = solve(coins, amount);
        System.out.println(result);
    }

    public MinCoinsSolver setTask(int [] coins, int amount) {
        this.coins = coins;
        this.amount = amount;
        return this;
    }

    abstract int minCoins(int[] coins, int amount) ;

    class Result{
        public int result;
        public long time;
        public boolean solved;
        public int[] coinsSolution;

        Result(int result, long time, int[] coinsSolution){
            this.result = result;
            this.time = time;
            this.solved = true;
            this.coinsSolution = coinsSolution;
        }

        Result(){
            this.solved = false;
        }

        @Override
        public String toString (){
            if(solved)
                return "Result: " + result + "\n" +
                        "With Coins: " + Arrays.toString(coinsSolution) + "\n" +
                        "Time: " + time + "\n";
            else return "No solution";
        }
    }
}
