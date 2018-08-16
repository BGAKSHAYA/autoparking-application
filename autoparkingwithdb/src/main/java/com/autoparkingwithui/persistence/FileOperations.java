/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithui.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *  @author Akshaya_Bindugowri
 */
public class FileOperations {
    /**
     * @param fileName file path
     * @return the file pointer to file
     * @throws Exception i.e. IOException, FileNotFoundException.
     */
    public BufferedReader openFileInReadMode(final String fileName)
            throws Exception {
            return new BufferedReader(new FileReader(fileName));
    }
    /**
     * @param fileName file path
     * @return the file pointer to file
     * @throws Exception i.e. IOException, FileNotFoundException.
     */
    public BufferedWriter openFileInWriteMode(final String fileName)
            throws Exception {
            return new BufferedWriter(new FileWriter(fileName));
    }

    /**
     * @param path Path to the file.
     * @param newLine the parking details
     * @throws Exception i.e. IOException, FileNotFoundException
     */
    public void appendTransaction(final String path,
            final String newLine) throws Exception {
       try (FileWriter file = new FileWriter(path, true)) {
          file.append(newLine + "\n");
       }
    }
}
