package com.me;

import java.util.Random;
import java.util.Scanner;

public class Dice {
	public short getNextRoll() {
		Scanner scanner = new Scanner(System.in);
		Random rand = new Random();
		short roll = (short)(rand.nextInt(6)+1);
		return(roll);
	}
}
