package com.me;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
	static User user = new User();
	static double money = user.getMoney();
	static NumberFormat usd = NumberFormat.getCurrencyInstance();
	public static void main(String[] args) {
		Bet bid = new Bet();
		Dice dice = new Dice();
		Scanner scanner = new Scanner(System.in);
		short guess = user.getGuess();
		double bidding = bid.getBid();
		short landing = dice.getNextRoll();
		String again;
		if (guess==landing) {
			System.out.println("Congratulations!\nYou won "+usd.format(bidding*3)+"!");
			money+=bidding*3.0;
			user.saveMoney();
			System.out.println("You now have: "+usd.format(money));
		}
		else {
			System.out.println("You lost.");
			money-=bidding;
			user.saveMoney();
			System.out.println("You now have: "+usd.format(money));
		}
		while (true) {
			System.out.print("Would you like to play again? Yes or no? ");
			again = scanner.next().toLowerCase().trim();
			if (again.equals("y") || again.equals("yes"))
				main(args);
			else if (again.equals("n") || again.equals("no")) {
				System.out.println("Thank you for playing!");
				scanner.close();
				System.exit(0);
			}
			else
				System.out.println("Please type yes or no!");
		}
	}

}
