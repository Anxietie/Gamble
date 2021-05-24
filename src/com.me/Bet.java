package com.me;

import java.util.Scanner;

public class Bet {
	public double getBid() {
		Scanner scanner = new Scanner(System.in);
		double bidding;
		System.out.print("How much would you like to bid? You have: "+Main.usd.format(Main.money)+" ");
		while (true) {
			while (true) {
				try {
					bidding = Double.parseDouble(scanner.next());
					break;
				}
				catch (NumberFormatException ignore) {
					System.out.print("Please enter a valid value! ");
				}
			}
			if (bidding>0 && bidding<=Main.money)
				break;
			else if (bidding>Main.money)
				System.out.print("You don't have enough money! Please bet a valid value! ");
			else
				System.out.print("Please enter a valid value! ");
		}
		return(bidding);
	}
}
