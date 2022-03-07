package time;

import java.util.Scanner;

/**
 * @modfied by Liz Dancy
 * Used to show how to catch multiple Exceptions and then as a base class
 * for the testing exercise with JUnit
 *
 */


import javax.swing.JOptionPane;

/**
 * Taken from Wendi Jollymore :http://www-acad.sheridanc.on.ca/~jollymor/prog24178/oop2.html
 * @modified by Liz Dancy
 * Used  as a base class
 * for the testing exercise with JUnit
 * Winter 2021
 * 
 * @modified by Han (Thao) Nguyen - ID 991575791
 * Date: Monday, Jan 24, 2022 at 10:45 AM
 * Purpose: ICE-3 - TDD with JUnit 
 * Changes made: 
 * Wrote a new test for getMilliseconds(), created correspondent method, refactored code
 * Modified other methods to support/utilize new method, enhanced user input handling
 * Re-run app & new tests
 * 
 */
public class Time
{
	public static void main(String[] args) 
	{
		double totalSeconds = getTotalSeconds("10:10:10.999");
		System.out.println("Github Total Seconds = " + totalSeconds);
		
		/*
		try
		{
		//hh:mm:ss.SSS based on Java date time format for hour:mimute:second.millisecond
		String time = JOptionPane.showInputDialog(null,
			"Enter a time in the format hh:mm:ss.SSS", "Enter Time",
			JOptionPane.QUESTION_MESSAGE);
		
		//*** Change type int totalSeconds to double to support milliseconds (1ms = 1/1000s)
		double totalSeconds = getTotalSeconds(time);
		JOptionPane.showMessageDialog(null, totalSeconds, "Total Seconds",
			JOptionPane.INFORMATION_MESSAGE);
		}
		catch(StringIndexOutOfBoundsException e)
		{
			JOptionPane.showMessageDialog(null,
					"You entered the time in the wrong format.\n" +
					"Please enter the time in the form hh:mm:ss.SSS",
					"Invalid Time", JOptionPane.ERROR_MESSAGE);
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null,
					"You entered an invalid time.\nPlease enter valid numbers only.",
					"Invalid Time",	JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e)
		{
			System.out.println("An unexpected Exception occurred");
		}
		*/
	}

	//***Change return type from int to double to support milliseconds
	public static double getTotalSeconds(String time)throws NumberFormatException, StringIndexOutOfBoundsException 
	{
		/* Since getTotalSeconds() calls below methods and take the same String time parameter
		 * => Input length condition moved from getTotalMinutes to getTotalSeconds
		 * Change time.length() > 8 to time.length() > 12 to support milliseconds
		 * => No need to repeat time.length() in getMilliseconds() or in other methods */
		
		if (time.length()>12)
		{
			throw new NumberFormatException("Your time was too long!");
		}
		
		// Add more user input control to enforce format hh:mm:ss.SSS
		// prevent input such as "123456789012" whose time.length() <= 12
		if (!time.substring(2,3).equals(":") || !time.substring(5,6).equals(":")
				|| !time.substring(8,9).equals("."))
		{
			throw new NumberFormatException("Please use correct format hh:mm:ss.SSS!");
		}
		
		int hours = getTotalHours(time);
		// we will eventually multiply the hours by 3600 + the minutes by 60 + the seconds
		// milliseconds divided by 1000 are also added
		int minutes = getTotalMinutes (time);
		int seconds = getSeconds(time);
		double milliseconds = getMilliseconds(time)/1000.0;
		return hours * 3600 + minutes * 60 + seconds + milliseconds;
	}
	
	public static int getSeconds(String time) throws NumberFormatException, StringIndexOutOfBoundsException 
	{
		
		return Integer.parseInt(time.substring(6,8));
	}

	public static int getTotalMinutes(String time) throws NumberFormatException, StringIndexOutOfBoundsException
	{
		return Integer.parseInt(time.substring(3,5));
	}

	public static int getTotalHours(String time) throws NumberFormatException, StringIndexOutOfBoundsException
	{
		return Integer.parseInt(time.substring(0,2));
	}

	// Add getMilliseconds method
	public static int getMilliseconds(String time) throws NumberFormatException, StringIndexOutOfBoundsException
	{	
		return Integer.parseInt(time.substring(9,12));
	}
	
}



