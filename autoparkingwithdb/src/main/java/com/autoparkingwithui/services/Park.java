/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithui.services;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import com.autoparkingwithui.Exceptions.*;
import com.autoparkingwithui.persistence.FileOperations;

/**
 *
 * @author Akshaya_Bindu_Gowri
 *
 */
 public class Park {
    /** The format in which the date has to be printed. */
    private DateFormat dateFormat = new SimpleDateFormat(
            "yyyy-mm-dd_hh:mm:ss");
    private static Logger logger = Logger.getLogger("com.autoparkingwithio.Park");

    /** A method to park the vehicle.
     * @param vehicleList List of vehicles.
     * @param availableSlots List of available slots.
     * @param plate license plate.
     * @param path Path to transaction file.
     * @throws Exception.
     * @return true if parking is successful.
     */
    public boolean park(final HashMap<String, Vehicle> vehicleList,
            final LinkedList<Integer> availableSlots,
            final String plate, final String path, final java.io.PrintWriter outServlet) throws Exception{
             boolean isParked = false;
     try {
	  if (Vehicle.checkFormatOfLicensePlate(plate)) {
	      if (availableSlots.isEmpty()) {
			  outServlet.print("Sorry couldn't park the vehicle"
	                  + " No empty slots found");
	          throw new EmptyAvailableSlotsException("Sorry couldn't park the vehicle"
	                  + " No empty slots found");
	      
	      } else if(vehicleList.get(plate)!=null) {
	    	  outServlet.print("You have alread parked your vehicle at "+ vehicleList.get(plate).getSlotNumber());
	          throw new AlreadyParkedException("You have alread parked your vehicle at "
	    	     + vehicleList.get(plate).getSlotNumber());
	      }
	      else {
	          Vehicle newVehicle = new Vehicle(plate,
	                  availableSlots.get(0), new Date());
	          vehicleList.put(plate, newVehicle);
	          availableSlots.remove(0);
	          System.out.println("Your car has been parked at "
	          + newVehicle.getSlotNumber());
	          outServlet.println("Your car has been parked at "
	          + newVehicle.getSlotNumber());
	          this.appendTransaction(path,
	                  Integer.toString(newVehicle.getSlotNumber())
	                  + " " + newVehicle.getLicenseNumber() + " "
	                  + this.dateFormat.format(newVehicle.getInTime()));
	          isParked = true;
	      }
	  } else {
		  outServlet.print("Incorrect license plate format");
	      throw new IncorrectPlateFormatException("Incorrect license plate format");
	  }
     }
     catch(Exception e) {
    	  logger.log(Level.SEVERE, "exception occured is", e);
     }
      return isParked;
    }
    
    void appendTransaction(String path, String newLine) throws Exception{
    	try {
            FileOperations file = new FileOperations();
            file.appendTransaction(path, newLine);
    	}
        catch(Exception e) {
      	  logger.log(Level.SEVERE, "exception occured is", e);
       }
    }
}
