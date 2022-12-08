package test;

import java.util.Date;
//Date(int year, int month, int day)
//year minus 1900
//month between 0-11
//day between 1-31

import main.Borrowing;

public class MainBorrowing {
	public static void main(String[] args) {
		int id = 1;
		String user = "Soufiane";
		String book = "Les Miserables";
		@SuppressWarnings("deprecation")
		Date date1 = new Date(2022-1900,4-1,10);
		//System.out.println(date1);
		
		@SuppressWarnings("deprecation")
		Date date2 = new Date(2022-1900,4-1,13);
		//System.out.println(date2);
		//System.out.println(date2.after(date1));
		
		@SuppressWarnings("deprecation")
		Date date3 = new Date(2022-1900,4-1,15);
		
		Borrowing b1 = new Borrowing(id, user, book, date1, date2, date3);
		System.out.println(b1);
		System.out.println(b1.isDelayed());
		
	}
}

