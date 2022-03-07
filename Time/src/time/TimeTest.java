package time;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	//-------------------Testing the getTotalSeconds() method
	@Test
	void testGetTotalSecondsGood() {
		//***change double to int seconds to support milliseconds
		double seconds = Time.getTotalSeconds("05:05:05.010");
		assertTrue("The seconds were not calculated properly", seconds==18305.010);
	}
	
	@Test
	void testGetTotalSecondsBad() {
		//Checking for index out of bounds
		assertThrows(
			StringIndexOutOfBoundsException.class,
			()->{Time.getTotalSeconds("10:10");});
		assertThrows(
			StringIndexOutOfBoundsException.class,
			()->{Time.getTotalSeconds(" ");});
		
		//Checking for number format 
		assertThrows(
				NumberFormatException.class,
				()->{Time.getTotalSeconds("151005");});
		assertThrows(
				NumberFormatException.class,
				()->{Time.getTotalSeconds("123456789012");});
		assertThrows(
				NumberFormatException.class,
				()->{Time.getTotalSeconds("0-:**:aa.99?");});
	}
	
	@Test
	void testGetTotalSecondsBoundary() {
		//test upper bound, with milliseconds added
		double seconds = Time.getTotalSeconds("59:59:59.999");
		assertTrue("The seconds were not calculated properly", seconds==215999.999);
		//test lower bound
		seconds = Time.getTotalSeconds("00:00:00.000");
		assertTrue("The seconds were not calculated properly", seconds==0.000);
	}
	
	
	//--------------------Testing the getSeconds() method
	@ParameterizedTest
	@ValueSource(strings = { "00:00:10.000", "12:15:10.505", "59:59:10.999"})
	void testGetSecondsGood(String candidate) {
		int seconds = Time.getSeconds(candidate);
		assertTrue("The seconds were not parsed properly", seconds==10);
	}
	
	@Test
	void testGetSecondsBad() {
		assertThrows(
			StringIndexOutOfBoundsException.class,
			()->{Time.getSeconds("05:05");});
		assertThrows(
				NumberFormatException.class,
				()->{Time.getTotalSeconds("05:00:??.100");});
	}
	
	@Test
	void testGetSecondsBoundary() {
		int seconds = Time.getTotalMinutes("00:00:59.000");
		assertTrue("The minutes were not parsed properly", seconds==0);
	    seconds = Time.getTotalMinutes("59:59:00.999");
		assertTrue("The minutes were not parsed properly", seconds==59);
	}
	
	//---------------------Testing the getTotalMinutes() method
	@ParameterizedTest
	@ValueSource(strings = { "00:15:00.000", "12:15:30.800", "59:15:59.999"})
	void testGetTotalMinutesGood(String candidate) {
		int minutes = Time.getTotalMinutes(candidate);
		assertTrue("The minutes were not parsed properly", minutes==15);
	}

	@Test
	void testGetTotalMinutesBad() {
		//Checking for index out of bounds
		assertThrows(
			StringIndexOutOfBoundsException.class,
			()->{Time.getTotalMinutes("  ");});
		assertThrows(
				StringIndexOutOfBoundsException.class,
				()->{Time.getTotalMinutes("15");});
		//Checking for number format 
		assertThrows(
				NumberFormatException.class,
				()->{Time.getTotalMinutes("05:%%:10.300");});
	}
	
	@Test
	void testGetTotalMinutesBoundary() {
		int minutes = Time.getTotalMinutes("00:00:59.000");
		assertTrue("The minutes were not parsed properly", minutes==0);
	    minutes = Time.getTotalMinutes("59:59:00.999");
		assertTrue("The minutes were not parsed properly", minutes==59);
	}
	
	//---------------------Testing the getTotalHours() method
	@ParameterizedTest
	@ValueSource(strings = { "30:59:59.999", "30:00:59.999", "30:00:00.000"})
	void testGetTotalHoursGood(String candidate) {
		int hours = Time.getTotalHours(candidate);
		assertTrue("The minutes were not parsed properly", hours==30);
	}

	@Test
	void testGetTotalHoursBad() {
		//Checking for index out of bounds
		assertThrows(
			StringIndexOutOfBoundsException.class,
			()->{Time.getTotalHours("");});
		assertThrows(
				StringIndexOutOfBoundsException.class,
				()->{Time.getTotalHours("3");});
		//Checking for number format 
		assertThrows(
				NumberFormatException.class,
				()->{Time.getTotalHours("aa:10:10.255");});
	}
	
	@Test
	void testGetTotalHoursBoundary() {
		int hours = Time.getTotalHours("00:30:50:000");
		assertTrue("The hours were not parsed properly", hours==0);
		hours = Time.getTotalHours("59:59:59:999");
		assertTrue("The hours were not parsed properly", hours==59);
	}
	
	//---------------------Testing the getTotalHours() method
	@Test
	void testGetMillisecondsGood() {
		int msecs = Time.getMilliseconds("10:30:15:025");
		assertTrue("The milliseconds were not parsed properly", msecs==25);
	}
	
	@Test
	void testGetMillisecondsBoundary() {
		int msec = Time.getMilliseconds("10:30:50:000");
		assertTrue("The hours were not parsed properly", msec==0);
		msec = Time.getMilliseconds("59:59:59:999");
		assertTrue("The hours were not parsed properly", msec==999);
	}
	
	@Test
	void testGetMillisecondsBad() {
		//Checking for index out of bounds
		assertThrows(
			StringIndexOutOfBoundsException.class,
			()->{Time.getMilliseconds("10:20:30.");});
		assertThrows(
				StringIndexOutOfBoundsException.class,
				()->{Time.getMilliseconds("10:20:30.09");});
				//correct form should be 10:20:30.090
		
		//Checking for number format 
		assertThrows(
				NumberFormatException.class,
				()->{Time.getMilliseconds("10:10:10.SSS");});
		assertThrows(
				NumberFormatException.class,
				()->{Time.getMilliseconds("10:10:10.55???");});
	}
}
