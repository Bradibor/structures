package TaskThree;

import TaskOne.LinkedList;

import java.util.*;

public class BruteForce extends MinCoinsSolver {
    private int coinsSolutionSize;
    private Stack<Integer> coinsStack;

    @Override
    protected void beforeSolve() {
        coinsSolutionSize = Integer.MAX_VALUE;
        coinsStack = new Stack<>();
    }

    @Override
    int minCoins(int[] coins, int amount) {
        if(Thread.interrupted()) {
            System.out.println("retuen -1");
            return -1;
        }
        if (amount == 0) {
            if(coinsStack.size() < coinsSolutionSize) {
                System.out.println(coinsStack);
                coinsSolutionSize = coinsStack.size();
                coinsSolution = new int[coinsStack.size()];
                for(int i = 0; i < coinsStack.size(); i++) {
                    coinsSolution[i] = coinsStack.get(i);
                }
            }
            return 0;
        }
        int result = Integer.MAX_VALUE;
        for (int coin: coins) {
            if (coin <= amount) {
                coinsStack.push(coin);
                int subResult = minCoins(coins, amount - coin);
                coinsStack.pop();
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
