package org.example;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        simulateLootBoxes(100000);
        simulateSlotMachine(1000000);
    }

    public static void simulateLootBoxes(int numBoxes) {
        Random random = new Random();
        int totalWins = 0;
        double[] probabilities = {0.0001, 0.3, 0.3, 0.15, 0.15, 0.05, 0.04, 0.005, 0.004, 0.0009};

        for (int i = 0; i < numBoxes; i++) {
            double randomNumber = random.nextDouble();
            double cumulativeProbability = 0;

            for (double probability : probabilities) {
                cumulativeProbability += probability;
                if (randomNumber <= cumulativeProbability) {
                    totalWins++;
                    break;
                }
            }
        }

        System.out.println("Total wins in loot boxes: " + totalWins);
    }

    public static void simulateSlotMachine(int numSpins) {
        Random random = new Random();
        int totalBet = 0;
        int totalWin = 0;
        int totalScatterWins = 0;

        for (int i = 0; i < numSpins; i++) {
            int bet = (random.nextInt(4) + 1) * 2; // Bet values: 2, 4, 6, 8, 10
            totalBet += bet;

            if (random.nextInt(243) < 3) { // 3/243 chance of winning
                int multiplier = random.nextInt(4) + 1; // Multipliers: 1, 2, 3, 4
                totalWin += bet * multiplier;
            }

            int scatterCount = 0;
            for (int j = 0; j < 15; j++) {
                if (random.nextInt(243) < 3) { // Check for Scatter Symbols
                    scatterCount++;
                }
            }

            if (scatterCount >= 3) {
                totalScatterWins += 10;
                totalBet -= bet; // Free spin doesn't cost a bet
            }
        }

        // Calculate RTP
        double rtp = (double) totalWin / totalBet * 100;

        System.out.println("Total Spins: " + numSpins);
        System.out.println("Total Bet: " + totalBet);
        System.out.println("Total Win: " + totalWin);
        System.out.println("Total Scatter Wins: " + totalScatterWins);
        System.out.println("RTP: " + rtp + "%");
    }
}
