/**
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithui.services;

//import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.autoparkingwithui.services.Park;
import com.autoparkingwithui.services.Vehicle;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import static org.mockito.Mockito.*;


/**
 *
 * @author Akshaya_BinduGowri
 *
 */
public class ParkTest {
    /** vehicleList contains the list of all the vehicles.*/
    private HashMap<String, Vehicle> vehicleList = new HashMap<>();
    /** avaiableSlots contains a list of all the available slots.*/
    private LinkedList<Integer> availableSlots = new LinkedList<Integer>();
    /** transactionFilePath. */
    private String transactionFilePath;
    /** Mocking file */
    private Park parkVeh = spy(Park.class);
    
    private PrintWriter out = new PrintWriter(System.out);
    /**Initialization.*/
    @Before
    public void init() throws Exception {
    vehicleList.put("AP16BA1231", new Vehicle("AP16BA1231", 1, new Date()));
    vehicleList.put("AP16BA1232", new Vehicle("AP16BA1232", 2, new Date()));
    vehicleList.put("AP16BA1233", new Vehicle("AP16BA1233", 1 + 2, new Date()));
    availableSlots.add(2 + 2);
    availableSlots.add(1 + 2 + 2);
    doNothing().when(parkVeh).appendTransaction(anyString(), anyString());
    transactionFilePath = "transactionalFile.txt";
    }

    /** Testing Park method.
     * @throws Exception */
    @Test
    public void testPark() throws Exception {
        
        assertEquals(true, parkVeh.park(vehicleList, availableSlots,
               "AP16BA1234", transactionFilePath, out));
        //already parked
        assertEquals(false, parkVeh.park(vehicleList, availableSlots,
                 "AP16BA1234", transactionFilePath, out));
        assertEquals(true, parkVeh.park(vehicleList, availableSlots,
                "AP16BA1235", transactionFilePath, out));
        assertEquals(false, parkVeh.park(vehicleList, availableSlots,
                "AP16BA1236", transactionFilePath, out));
        assertEquals(false, parkVeh.park(vehicleList, availableSlots,
                "AP16BA1237", transactionFilePath, out));
        //incorrect plate format
        assertEquals(false, parkVeh.park(vehicleList, availableSlots,
                 "AP16BA129", transactionFilePath, out));

    }
    
    /** Testing AppendTransaction method.
     * @throws Exception */
    @Test
    public void testAppendTransaction() throws Exception {    
         Park park = new Park();
         String path = "testFile2.tmp";
         File temp = File.createTempFile("testFile2", ".tmp");          
         park.appendTransaction(path, "A new line");
         BufferedReader tf = new BufferedReader(new FileReader(path));
         int count = 0;
         while (tf.readLine() != null) count++;
         assertEquals(1, count);
         tf.close();
         temp.deleteOnExit();
         //creating IOException.
         park.appendTransaction("con.", "A new line");

    }
}
