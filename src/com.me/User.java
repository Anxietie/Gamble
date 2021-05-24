package com.me;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
	public boolean createUserFile() {
		String path = System.getProperty("user.home")+File.separator+"Documents\\game";
		boolean created = false;
		File dir = new File(path);
		try {
			if (dir.mkdirs()) {
				path+="\\user.txt";
				File f = new File(path);
				if (f.createNewFile()) {
					created = true;
					FileWriter fw = new FileWriter(path);
					fw.write("100.0");
					fw.close();
					System.out.println("User file created at: "+f.getAbsolutePath());
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return(created);
	}
	public double getMoney() {
		String path = System.getProperty("user.home")+File.separator+"Documents\\game\\user.txt";
		File f = new File(path);
		double money = 0;
		if (Main.user.createUserFile()==false) {
			try {
				Scanner reader = new Scanner(f);
				while (reader.hasNextLine()) {
					money = reader.nextDouble();
				}
				reader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
			money = 100.0;
		return(money);
	}
	public void saveMoney() {
		String path = System.getProperty("user.home")+File.separator+"Documents\\game\\user.txt";
		try {
			PrintWriter pw = new PrintWriter(path);
			pw.write("");
			pw.close();
			FileWriter fw = new FileWriter(path);
			fw.write(""+Main.money);
			fw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public short getGuess() {
		Scanner scanner = new Scanner(System.in);
		short bet;
		System.out.print("Place your guess on a dice number (1-6): ");
		while (true) {
			while (true) {
				try {
					bet = Short.parseShort(scanner.next());
					break;
				}
				catch (NumberFormatException ignore) {
					System.out.print("Please enter a valid value! ");
				}
			}
			if (bet<=6 && bet>=1)
				break;
			else
				System.out.print("Please enter a valid value! ");
		}
		return(bet);
	}
}
