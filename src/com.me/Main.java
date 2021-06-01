package com.me;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean again = false;
		while (true) {
			// Initializes some necessary variables
			NumberFormat usd = NumberFormat.getCurrencyInstance();
			Scanner scanner = new Scanner(System.in);
			boolean correct = false;
			// If they say yes to play again it prints "Ready?" instead of "Welcome!" every time to make it look more appealing
			if (!again)
				System.out.println("Welcome!");
			else
				System.out.println("Ready?");
			// Dice and user objects
			Dice die = new Dice();
			User user = new User();
			// Creates the user file
			user.createFile();
			if (user.getGuess()==die.getNextRoll())
				correct=true;
			if (user.getMoney()<=0) {
				System.out.println("Lmao you're broke. Here's $50");
				user.money=50.0;
			}
			double bet = user.getBet();
			// If they won or lost
			if (correct) {
				System.out.println("Congratulations! You won: "+usd.format(bet*6));
				user.money+=bet*6;
				user.saveMoney();
			}
			else {
				user.money-=bet;
				user.saveMoney();
				System.out.println("You lost. You now have: "+usd.format(user.money));
			}
			// Play again?
			System.out.print("Would you like to play again? y/n ");
			while (true) {
				String pAgain = scanner.next();
				if (pAgain.equalsIgnoreCase("yes") || pAgain.equalsIgnoreCase("y")) {
					again = true;
					break;
				}
				else if (pAgain.equalsIgnoreCase("no") || pAgain.equalsIgnoreCase("n")) {
					System.out.println("Byebye!");
					scanner.close();
					System.exit(0);
				}
				System.out.print("Please type y or n! (Yes and No also work). ");
			}
		}
	}
}
