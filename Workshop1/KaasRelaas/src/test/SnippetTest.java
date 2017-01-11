package test;

import java.sql.Date;
import java.time.LocalDate;


public class SnippetTest {

	public static void main(String[] args)  {
		
		LocalDate date = LocalDate.now();
		Date datesql = new java.sql.Date(date.getYear()-1900,date.getMonthValue()-1, date.getDayOfMonth());
		System.out.println(date.getYear());
		System.out.println(date.getMonthValue());
		System.out.println(date.getDayOfMonth());
		System.out.println(datesql.toString());

	}

}
