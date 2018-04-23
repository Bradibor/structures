package TaskThree;

import java.util.ArrayList;
import java.util.List;

public class BruteTest {

    public void printCombos(int[] denominations, int desiredValue) {

        List<Integer> coins = new ArrayList<>();
        printComboHelper(desiredValue, denominations, coins, 0, 0);

    }

    private void printComboHelper(int desiredAmount, int[] coinDenominations, List<Integer> coinList, int sum, int lastCoinIndex) {


        if (sum > desiredAmount) {
            return;
        }

        if (sum == desiredAmount) {
            for (Integer coin : coinList) {
                System.out.print(coin + " ");
            }
            System.out.println("");

            return;
        } else {
            for (int coinIndex = lastCoinIndex; coinIndex < coinDenominations.length; coinIndex++) {
                int coinValue = coinDenominations[coinIndex];
                coinList.add(coinValue);
                printComboHelper(desiredAmount, coinDenominations, coinList, sum + coinValue, coinIndex);
                coinList.remove(coinList.size() - 1);
            }
        }
    }
}