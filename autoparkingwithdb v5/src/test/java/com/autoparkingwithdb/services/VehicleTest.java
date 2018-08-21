/**
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithdb.services;

//import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.autoparkingwithdb.services.Vehicle;

import java.util.Date;

/**
 *
 * @author Akshaya_Gowri
 *
 */
public class VehicleTest {
    /**Method to test the getLicenseNumber method.
     *
     */
    @Test
    public void testGetLicenseNumber() {
         Vehicle vehicleObjectToTest = new Vehicle("AP16BA4549", 1, new Date());
         assertEquals("AP16BA4549", vehicleObjectToTest.getLicenseNumber());
    }

    /**Method to test the getSlotNumber method.
     *
     */
    @Test
    public void testGetSlotNumber() {
         Vehicle vehicleObjectToTest = new Vehicle("AP16BA4549", 1, new Date());
         assertEquals(1, vehicleObjectToTest.getSlotNumber());
    }
    /**Method to test the getInTime method.
    *
    */
   @Test
   public void testGetInTime() {
        Date d = new Date();
        Vehicle vehicleObjectToTest = new Vehicle("AP16BA4549", 1, d);
        assertEquals(d, vehicleObjectToTest.getInTime());
   }
   /**Method to test the setLicenseNumber method.
   *
   */
  @Test
  public void testSetLicenseNumber() {
       Vehicle vehicleObjectToTest = new Vehicle("AP16BA4549", 1, new Date());
       vehicleObjectToTest.setLicenseNumber("AP16BA6789");
       assertEquals("AP16BA6789", vehicleObjectToTest.getLicenseNumber());
  }

  /**Method to test the setSlotNumber method.
   *
   */
  @Test
  public void testSetSlotNumber() {
       Vehicle vehicleObjectToTest = new Vehicle("AP16BA4549", 1, new Date());
       vehicleObjectToTest.setSlotNumber(2);
       assertEquals(2, vehicleObjectToTest.getSlotNumber());
  }
  /**Method to test the setInTime method.
  *
  */
 @Test
 public void testSetInTime() {

      Vehicle vehicleObjectToTest = new Vehicle("AP16BA4549", 1, new Date());
      Date d = new Date();
      vehicleObjectToTest.setInTime(d);
      assertEquals(d, vehicleObjectToTest.getInTime());
 }
 /**Method to test the setOutTime method.
 *
 */
 @Test
 public void testSetOutTime() {
     Vehicle vehicleObjectToTest = new Vehicle("AP16BA4549", 1, new Date());
     Date d = new Date();
     vehicleObjectToTest.setOutTime(d);
     assertEquals(d, vehicleObjectToTest.getOutTime());
  }

}
