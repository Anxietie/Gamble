package com.me;

import java.util.Random;

public class Dice {
	public int getNextRoll() {
		Random rand = new Random();
		int roll = rand.nextInt(6)+1;
		return(roll);
	}
}
