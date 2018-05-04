package TaskThree;

import java.util.Arrays;
import java.util.Stack;

public class DynamicProgramming extends MinCoinsSolver{
    int[][] solution;

    @Override
    protected void beforeSolve() {
    }

    @Override
    int minCoins(int[] coins, int amount) {
        this.coins = coins;
        this.solution = new int[amount + 1][coins.length];
        for (int i = 0; i < solution.length; i++) {
            for(int j = 0; j < coins.length; j++) {
                solution[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < coins.length; i++){
            solution[0][i] = 0;
        }
        for (int i = 1; i <=amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    int subResult = solutionSum(i - coins[j]);
                    if(subResult != Integer.MAX_VALUE && subResult + 1 < solutionSum(i))
                    {
                        for(int k = 0; k < coins.length; k++){
                            solution[i][k] = solution[i-coins[j]][k];
                        }
                        solution[i][j] = solution[i][j] + 1;
                    }
                }
            }
        }
        coinsSolution = new int[solutionSum(amount)];
        int k = 0;
        for(int i = 0; i < coins.length; i++) {
            for(int j = 0; j < solution[amount][i]; j++){
                coinsSolution[k++] = coins[i];
            }
        }
        return solutionSum(amount);
    }


    private int solutionSum(int i){
        int subResult = 0;
        for(int j = 0; j < coins.length; j++){
            subResult += solution[i][j];
        }
        return subResult;
    }

    public static void main(String[] args) {
        DynamicProgramming dy = new DynamicProgramming();
        System.out.println(dy.solve(new int[]{1, 6 ,10},12 ));
    }
}
