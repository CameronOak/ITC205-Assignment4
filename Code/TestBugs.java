import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TestBugs {

	@Test
	public void TestIncorrectBalance() {
		int bet = 5;
		int totalWins = 0;
		int totalLoss = 0;
		int balance = 15;
		int limit = 0;
		int match = 0;
		int winningsControlled = 0;
		String name = "Fred";
		DiceValue pick = DiceValue.ANCHOR;

		Dice d1 = new Dice();
		Dice d2 = new Dice();
		Dice d3 = new Dice();

		Player player = new Player(name, balance);
		Game game = new Game(d1, d2, d3);
		List<DiceValue> cdv = game.getDiceValues();

		// Number of games played
		for (int i = 0; i < 1; i++) {
			totalWins = 0;
			totalLoss = 0;
			balance = 15;
			player = new Player(name, balance);
			player.setLimit(limit);

			int turn = 0;

			turn++;

			int winnings = game.playRound(player, pick, bet);
			
			System.out.println("Initial balance: " + balance);
			System.out.println("player dice value choice: " + pick);

			cdv = game.getDiceValues();

			// Controlled winnings
			for (int a = 0; a < cdv.size(); a++) {
				if (pick.equals(cdv.get(a))) {
					match++;
				}
			}
			winningsControlled = bet * match;

			balance = balance + winningsControlled;

			if (winningsControlled == 0) {
				balance = balance - bet;
			}
			System.out.println("dice values: " + cdv);
			System.out.println("Expected balance: " + balance + " actual balance: "  + player.getBalance());
			assertEquals(balance, player.getBalance());

			/*
			 * if (winnings > 0) {
			 * 
			 * totalWins++; } else {
			 * 
			 * totalLoss++; }
			 */

		}

	}

}
