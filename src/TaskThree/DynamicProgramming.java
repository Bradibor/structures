package TaskThree;

import java.util.Arrays;
import java.util.Stack;

public class DynamicProgramming extends MinCoinsSolver{
    private int coinsSolutionSize;
    private Stack<Integer> coinsStack;

    @Override
    protected void beforeSolve() {
        coinsSolutionSize = Integer.MAX_VALUE;
        coinsStack = new Stack<>();
    }

    @Override
    int minCoins(int[] coins, int amount) {
        int[] solution = new int[amount + 1];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = Integer.MAX_VALUE;
        }

        solution[0] = 0;
        for (int i = 1; i <=amount; i++) {
            for (int coin: coins) {
                if (coin <= i) {
                    int subResult = solution[i - coin];
                    if(subResult != Integer.MAX_VALUE && subResult + 1 < solution[i])
                    {
                        coinsStack.push(coin);
                        if(subResult + 1 < solution[i])
                        solution[i] = subResult + 1;
                    }
                    else coinsStack.pop();
                }
            }
        }
        System.out.println(coinsStack);
        System.out.println(Arrays.toString(solution));
        return solution[amount];
    }

    public static void main(String[] args) {
        DynamicProgramming dy = new DynamicProgramming();
        dy.solve(new int[]{1, 6 ,10},12 );
    }
}
