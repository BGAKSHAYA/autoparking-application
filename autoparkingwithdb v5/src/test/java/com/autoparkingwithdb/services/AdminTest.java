/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithdb.services;

//import junit.framework.TestCase;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import com.autoparkingwithdb.services.AdminSingleton;

/**
 *
 * @author Akshaya_Gowri
 *
 */
public class AdminTest {
    /**Method to test the credentials.
     *
     */
    @Test
    public void testCheckCredentials() {
         AdminSingleton admin = AdminSingleton.getInstance();
         assertTrue(admin.checkCredentials("admin", "java"));
         assertFalse(admin.checkCredentials("admin", "javas"));
         assertFalse(admin.checkCredentials("admifgn", "javas"));
    }
}
