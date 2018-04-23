package TaskThree;

public class DynamicProgramming extends MinCoinsSolver{

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
                        solution[i] = subResult + 1;
                    }
                }
            }
        }
        return solution[amount];
    }

    public static void main(String[] args) {
        DynamicProgramming dy = new DynamicProgramming();
        dy.solve(new int[]{1, 5 ,10}, 11);
    }
}
