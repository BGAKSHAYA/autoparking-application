/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithdb.services;

/**
 *
 * @author Akshaya_Bindu_Gowri
 *
 */
 public final class AdminSingleton {
   /** username of admin. */
   private String username;
   /** password of admin. */
   private String password;
   /** the single instance of class. */
   private static final AdminSingleton ADMIN_INSTANCE = new  AdminSingleton();
   /** This private constructor overloads default constructor.
    * and won't be called
   */
   private AdminSingleton() {
       username = "admin";
       password = "java";
   }
   /**
    * @return oneInstance Return oneInstance that is created.
    */
    public static AdminSingleton getInstance() {
       return ADMIN_INSTANCE;
    }
   /**
    * @param user , username obtained from user
    * @param pass , password obtained from user
    * @return the authorization result
    */
   public boolean checkCredentials(final String user, final String pass) {
       return this.username.equals(user) && this.password.equals(pass);
   }

}
