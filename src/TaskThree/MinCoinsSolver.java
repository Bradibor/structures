package TaskThree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class MinCoinsSolver {

    protected List<Integer> coinsSolution;

    Result solve(int[] coins, int amount) {
        long startTime = System.currentTimeMillis();
        coinsSolution = new ArrayList<>();
        try{
            int result = minCoins(coins, amount);
            return result == Integer.MAX_VALUE? new Result() : new Result(result, System.currentTimeMillis() - startTime, coinsSolution) ;
        }catch (StackOverflowError e){
            return new Result();
        }catch (OutOfMemoryError e){
            return new Result();
        }
    }

    abstract int minCoins(int[] coins, int amount) ;

    class Result{
        public int result;
        public long time;
        public boolean solved;
        public List<Integer> coinsSolution;

        Result(int result, long time, List<Integer> list){
            this.result = result;
            this.time = time;
            this.solved = true;
            this.coinsSolution = list;
        }

        Result(){
            this.solved = false;
        }

        @Override
        public String toString (){
            if(solved)
                return "Result: " + result + "\n" +
                        "With Coins: " + coinsSolution + "\n" +
                        "Time: " + time + "\n";
            else return "No solution";
        }
    }
}
