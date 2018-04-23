package TaskThree;

import TaskOne.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteForce extends MinCoinsSolver {

    @Override
    int minCoins(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        for (int coin: coins) {
            if (coin <= amount) {
                coinsSolution.add(coin);
                int subResult = minCoins(coins, amount - coin);
                coinsSolution.remove(coin);
                if(subResult != Integer.MAX_VALUE && 1 + subResult < result){
                    result = 1 + subResult;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MinCoinsSolver force = new BruteForce();
        System.out.println(force.solve(new int[]{1,5,10}, 11));
    }
}
