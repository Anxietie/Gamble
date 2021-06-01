package com.me;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Scanner;


public class User {
	
	// Amount of money the user has
	public double money;
	// Directory of user's money file
	private String path = System.getProperty("user.home")+File.separator+"Documents\\game";
	// Did the file get created successfully? (defaults to false if an error occurred)
	private boolean created = false;
	
	// Creates user money file
	public boolean createFile() {
		File dir = new File(path);
		try {
			// Checks if directory doesn't exists and if the directory has been created
			if (!dir.exists() && dir.mkdirs()) {
				// User file added to path
				path+="\\user.txt";
				File uf = new File(path);
				// True if the file was created successfully
				if (uf.createNewFile()) {
					created = true;
					// Writes user's money
					FileWriter fw = new FileWriter(path);
					fw.write("100.0");
					fw.close();
					System.out.println("User file created at: "+uf.getAbsolutePath());
				}
			}
			// Checks if the directory already exists
			else if (dir.exists()) {
				path+="\\user.txt";
				File uf = new File(path);
				// Checks if the file already exists
				if (uf.exists())
					created = true;
				// Creates user file if directory exists without file
				else if (uf.createNewFile()) {
					created = true;
					FileWriter fw = new FileWriter(path);
					fw.write("100.0");
					fw.close();
					System.out.println("User file created at: "+uf.getAbsolutePath());
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		// Returns true if file was created successfully
		return(created);
	}
	
	public double getMoney() {
		// Path will always be \"user.home\\Documents\\game\\user.txt\"
		File uf = new File(path);
		// Was the file created in the previous method?
		if (created) {
			try {
				// Gets the amount of money of the user
				Scanner reader = new Scanner(uf);
				while (reader.hasNextLine()) {
					money = reader.nextDouble();
				}
				reader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		// If the file wasn't created in the previous method, this happens
		else
			System.out.println("No user file.");
		return(money);
	}
	// Saves the user's earnings/losings after each play
	public void saveMoney() {
		try {
			PrintWriter pw = new PrintWriter(path);
			pw.write("");
			pw.close();
			FileWriter fw = new FileWriter(path);
			fw.write(""+money);
			fw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Gets the guess of the user
	public short getGuess() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		byte guess;
		System.out.print("Place your guess on a dice number (1-6): ");
		while (true) {
			while (true) {
				try {
					guess = Byte.parseByte(scanner.next());
					break;
				}
				catch (NumberFormatException ignore) {
					System.out.print("Please enter a valid value! ");
				}
			}
			if (guess<=6 && guess>=1)
				break;
			else
				System.out.print("Please enter a valid value! ");
		}
		return(guess);
	}
	// Gets the bet of the user
	public double getBet() {
		NumberFormat usd = NumberFormat.getCurrencyInstance();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		double bet;
		System.out.print("How much would you like to bet? You have: "+usd.format(money)+" ");
		while (true) {
			while (true) {
				try {
					bet = Double.parseDouble(scanner.next());
					break;
				}
				catch (NumberFormatException ignore) {
					System.out.print("Please enter a valid value! ");
				}
			}
			if (bet>0 && bet<=money)
				break;
			else if (bet>money)
				System.out.print("You don't have enough money! Please bet a valid value! ");
			else
				System.out.print("Please enter a valid value! ");
		}
		return(bet);
	}
}
