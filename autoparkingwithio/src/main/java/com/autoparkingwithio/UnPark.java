/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithio;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 *
 * @author Akshaya_Bindu_Gowri
 *
 */
 public class UnPark {
    /** The format in which the date has to be printed. */
    private DateFormat dateFormat = new SimpleDateFormat(
            "yyyy-mm-dd_hh:mm:ss");
    private Logger logger = Logger.getLogger("com.autoparkingwithio.Park");


    /** A method to park the vehicle.
     * @param vehicleList List of vehicles.
     * @param availableSlots List of available slots.
     * @param plate license plate.
     * @param transactionFilePath Path to transaction file.
     * @param logFilePath Path to log file.
     * @return true if unparking is successful.
     * @throws Exception i.e. IOException, FileNotFoundException.
     */

    boolean unPark(final HashMap<String, Vehicle> vehicleList,
            final LinkedList<Integer> availableSlots,
            final String plate,
            final String transactionFilePath,
            final String logFilePath) throws Exception {

     boolean isUnParked = false;
     try {
	     FileOperations file = new FileOperations();
	     if (Vehicle.checkFormatOfLicensePlate(plate)) {
	         if (vehicleList.get(plate) != null) {
	              Vehicle unparked = vehicleList.get(plate);
	              availableSlots.add(unparked.getSlotNumber());
	              unparked.setOutTime(new Date());
	              this.appendTransaction(logFilePath,
	                      plate + " "
	                      + dateFormat.format(unparked.getInTime())
	                      + " "
	                      + dateFormat.format(unparked.getOutTime()));
	              BufferedReader tf =
	                      file.openFileInReadMode(transactionFilePath);
	              LinkedList<String> fileContents = new LinkedList<>();
	              String newLine;
	              while ((newLine = tf.readLine()) != null) {
	                  if (Integer.parseInt(newLine.split(" ")[0])
	                          != unparked.getSlotNumber()) {
	                          fileContents.add(newLine);
	                  }
	              }
	              tf.close();
	              BufferedWriter tfw =
	                     file.openFileInWriteMode(transactionFilePath);
	              for (String s : fileContents) {
	                  tfw.write(s);
	              }
	              tfw.close();
	              System.out.println("Vehicle successfully exited");
	              isUnParked = true;
	         } else {
	        	 throw new ExceptionHandler("Sorry, vehicle not been occupied");
	         }
	     } else {
	         throw new ExceptionHandler("Incorrect license plate format");
	     }
     }
     
     catch(ExceptionHandler e) {
    	  System.out.println(e.getMessage());
     }
     
     return isUnParked;
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
