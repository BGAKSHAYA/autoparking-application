/**
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithdb.services;

//import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;

import com.autoparkingwithdb.services.UnPark;
import com.autoparkingwithdb.services.Vehicle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Akshaya_BinduGowri
 *
 */
public class UnParkTest {
    /** vehicleList contains the list of all the vehicles.*/
    private HashMap<String, Vehicle> vehicleList = new HashMap<>();
    /** avaiableSlots contains a list of all the available slots.*/
    private LinkedList<Integer> availableSlots = new LinkedList<Integer>();
    /** transactionFilePath. */
    private String transactionFilePath;
    /** logFilePath. */
    private static String logFilePath;
    /** Mocking file */
    private UnPark unPark = spy(UnPark.class);
    
    private PrintWriter out = new PrintWriter(System.out);

    /**Initailization.
     * @throws Exception */
    @Before
    public void init() throws Exception {
    vehicleList.put("AP16BA1231", new Vehicle("AP16BA1231", 1, new Date()));
    vehicleList.put("AP16BA1232", new Vehicle("AP16BA1232", 2, new Date()));
    vehicleList.put("AP16BA1233", new Vehicle("AP16BA1233", 1 + 2, new Date()));
    availableSlots.add(2 + 2);
    availableSlots.add(1 + 2 + 2);
    transactionFilePath = "transactionalFile.txt";
    doNothing().when(unPark).appendTransaction(anyString(), anyString());
    logFilePath = "logFile.txt";
    }

    /** Testing UnPark method.
     * @throws Exception */
    @Test
    public void testUnPark() throws Exception {
        assertEquals(true, unPark.unPark(vehicleList, availableSlots,
               "AP16BA1231", transactionFilePath, logFilePath, out));
        assertEquals(true, unPark.unPark(vehicleList, availableSlots,
                "AP16BA1233", transactionFilePath, logFilePath, out));
        assertEquals(false, unPark.unPark(vehicleList, availableSlots,
                "AP16BA1239", transactionFilePath, logFilePath, out));
        assertEquals(false, unPark.unPark(vehicleList, availableSlots,
                "AP16BA1239", transactionFilePath, logFilePath, out));

      //incorrect plate format
        assertEquals(false, unPark.unPark(vehicleList, availableSlots,
                "AP16BA129", transactionFilePath, logFilePath, out));
    }

    /** Testing AppendTransaction method.
     * @throws Exception */
    @Test
    public void testAppendTransaction() throws Exception {    
         UnPark unPark = new UnPark();
         String path = "testFile.tmp";
         File temp = File.createTempFile("testFile", ".tmp");          
         unPark.appendTransaction(path, "A new line");
         BufferedReader tf = new BufferedReader(new FileReader(path));
         int count = 0;
         while (tf.readLine() != null) count++;
         assertEquals(1, count);
         tf.close();
         temp.deleteOnExit();
         
         //creating IOException.
         unPark.appendTransaction("con.", "A new line");

         
    }
    

}
