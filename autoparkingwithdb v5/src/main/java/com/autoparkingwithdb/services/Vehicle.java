/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithdb.services;

import java.util.Date;
import java.util.regex.Pattern;
import javax.persistence.Entity;  
import javax.persistence.Id;  
import javax.persistence.Table;  
import javax.persistence.Column;  


/**
 * @author Akshaya_Bindu_Gowri
 *
 */
@Entity
@Table(name= "transaction") 
public class Vehicle {
  /** licenseNumber of the Vehicle.*/
  @Id
  @Column(name="license_number", nullable=false, unique=true, length=20)
  private String licenseNumber;
  
  @Column(name="slot_number", nullable=false, unique=false, length=11)
  /** slotNumber of the Vehicle.*/
  private int slotNumber;
  
  @Column(name="intime", nullable=false)
  /** intime  of the Vehicle.*/
  private Date inTime;
  
  /** outtime of the Vehicle.*/
  private Date outTime;
  
  public Vehicle() {
	  
  }
  /**Constructor to set the licenseNumber and slotNumber of Vehicle.
   * @param licensenumber Sets the licenseNumber
   * @param slotnumber Sets the slotNumber
   * @param intime of the vehicle
   * @return
   */
  public Vehicle(final String licensenumber, final int slotnumber, final Date intime) {
    this.licenseNumber = licensenumber;
    this.slotNumber = slotnumber;
    this.inTime = intime;
  }

  /**.
   * Method to get the slotNumber
   * @return slotNumber
   */
  public int getSlotNumber() {
    return this.slotNumber;
  }
  /**.
   * Method to get the licenseNumber
   * @return licenseNumber
   */
  public String getLicenseNumber() {
    return this.licenseNumber;
  }
  /**.
   * @param slotnumber set the slot number
   */
  public void setSlotNumber(final int slotnumber) {
    this.slotNumber = slotnumber;
  }
  /**.
   * @param licensenumber set the license number
   */
  public void setLicenseNumber(final String licensenumber) {
    this.licenseNumber = licensenumber;
  }
  /**
   * @param licensePlate Accepts license plate and check its format
   * @return true/false based on the format
  */
  static boolean checkFormatOfLicensePlate(final String licensePlate) {
    return Pattern.matches("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$", licensePlate);
  }
  /**.
   * Method to get the intime of vehicle
   * @return inTime
   */
  Date getInTime() {
    return this.inTime;
  }
  /**.
   * @param intime set the intime
   */
  public void setInTime(final Date intime) {
    this.inTime = intime;
  }
  /**.
   * Method to get the outtime of vehicle
   * @return outTime
   */
  Date getOutTime() {
    return this.outTime;
  }
  /**.
   * @param outtime set the slot number
   */
  void setOutTime(final Date outtime) {
    this.outTime = outtime;
  }

}
