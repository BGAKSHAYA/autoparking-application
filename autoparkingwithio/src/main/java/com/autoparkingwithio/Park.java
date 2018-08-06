/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithio;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author Akshaya_Bindu_Gowri
 *
 */
 public class Park {
    /** The format in which the date has to be printed. */
    private DateFormat dateFormat = new SimpleDateFormat(
            "yyyy-mm-dd_hh:mm:ss");
    private Logger logger = Logger.getLogger("com.autoparkingwithio.Park");

    /** A method to park the vehicle.
     * @param vehicleList List of vehicles.
     * @param availableSlots List of available slots.
     * @param plate license plate.
     * @param path Path to transaction file.
     * @throws Exception.
     * @return true if parking is successful.
     */
    boolean park(final HashMap<String, Vehicle> vehicleList,
            final LinkedList<Integer> availableSlots,
            final String plate, final String path) throws Exception{
             boolean isParked = false;
      try {
		  if (Vehicle.checkFormatOfLicensePlate(plate)) {
		      if (availableSlots.isEmpty()) {
		          System.out.println("Sorry couldn't park the vehicle"
		                  + " No empty slots found");
		      } else {
		          Vehicle newVehicle = new Vehicle(plate,
		                  availableSlots.get(0), new Date());
		          vehicleList.put(plate, newVehicle);
		          availableSlots.remove(0);
		          System.out.println("Your car been parked at "
		          + newVehicle.getSlotNumber());
		          this.appendTransaction(path,
		                  Integer.toString(newVehicle.getSlotNumber())
		                  + " " + newVehicle.getLicenseNumber() + " "
		                  + this.dateFormat.format(newVehicle.getInTime()));
		          isParked = true;
		      }
		  } else {
		      throw new ExceptionHandler("Incorrect license plate format");
		  }
      }
      catch(ExceptionHandler e) {
    	  System.out.println(e.getMessage());
      }
    
      return isParked;
    }
    
    void appendTransaction(String path, String newLine) throws Exception{
      try {
        FileOperations file = new FileOperations();
        file.appendTransaction(path, newLine);
      }
      catch(IOException e) {
    	  logger.log(Level.SEVERE, "exception occured is", e);
      }
    }
}
