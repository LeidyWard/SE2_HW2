/* 
 *  Author ......: Leidy Ward Buescher 
 *  Target ......: JUnit Test
 *  Description..: Tests for Parking Garage application
 *  Course.......: Software Engineering II - SP 2020
 */

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import org.junit.Test;

public class ParkingTest {

	@Test
	//Test ID: 3
	//validate that 5 hours results in a charge of $3.00, with correct running total
	public void validIntegerTest() {
		ParkingCharge test = new ParkingCharge(0);
        int charge = (int) ParkingCharge.calculateCharges(5);
        test.setRunTotal(charge);
		
		assertTrue(charge == 3.00 && test.getRunTotal() == 3.00);
	}

	@Test
	//Test ID: 4
	//validate that 10.2 hours results in a charge of $6.00, with correct running total
	public void validDoubleTest() {
		ParkingCharge test = new ParkingCharge(0.0);
        double charge = ParkingCharge.calculateCharges(10.2);
        test.setRunTotal(charge);
		
		assertTrue(charge == 6.00 && test.getRunTotal() == 6.00);
	}
	
	@Test
	//Test ID: 15
	//validate that 2.2 hours is charged the MINCHARGE of $2.00 because it is under the MINHOURS (3.0)
	public void underMinHoursTest() {
		ParkingCharge test = new ParkingCharge(0);
        double charge = ParkingCharge.calculateCharges(2.2);
        test.setRunTotal(charge);
		
		assertTrue(charge == 2.00 && test.getRunTotal() == 2.00);
	}
	
	
	@Test
	//Test ID: 16
	//validate that 24.5 hours is charged the MAXCHARGE of $10.00 because it is over the MAXHOURS (24)
	public void overMaxHoursTest() {
		ParkingCharge test = new ParkingCharge(0);
        double charge = ParkingCharge.calculateCharges(24.5);
        test.setRunTotal(charge);
		
		assertTrue(charge == 10.00 && test.getRunTotal() == 10.00);
	}
	
	@Test (expected = InputMismatchException.class)
	//Test ID: 5
	//validate that nonnumeric hours is not accepted, program ends with InputMismatchException
	public void nonnumericTest() {
		InputStream original = System.in; // backup System.in to restore it later
		ByteArrayInputStream in = new ByteArrayInputStream("h".getBytes());
		System.setIn(in);		//set 'h' into system
		
		ParkingChargeDriver test = new ParkingChargeDriver();
		test.main(null);			//start main method

        System.setIn(original);	//reset System.in to original
	}
	
	@Test (expected = NoSuchElementException.class)
	//Test ID: 1
	//validate that an exception is thrown when the input value is too large (over max double value)
	public void greaterThanMaxDoubleTest() {
		InputStream sysInBackup = System.in; // backup System.in to restore it later
		double d = Double.MAX_VALUE + 10;
		ByteArrayInputStream in = new ByteArrayInputStream(Double.toString(d).getBytes());
		System.setIn(in);
		
		ParkingChargeDriver test = new ParkingChargeDriver();
		test.main(null);

        System.setIn(sysInBackup);
	}
	
}
