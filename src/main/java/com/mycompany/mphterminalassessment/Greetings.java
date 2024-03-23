
package com.mycompany.mphterminalassessment;

/**
 *
 * @author teshia
 */
import java.util.logging.Level;
import java.util.logging.Logger;

// Abstract class for greetings
abstract class Greetings {
    
    // Method to display welcome greeting
    public void welcomeGreet() {
        System.out.println("Welcome to the MotorPH Payroll Portal");
    }
    
     // Abstract method to wait for loading greeting
    void waitLoadingGreet() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

// Subclass implementing loading greeting
class loadingGreet extends Greetings {
    // Implementation of waiting for loading greeting
    public void waitLoadingGreet() {
        
        //Loading with 0.5 seconds delay of '.'
        System.out.print("Please Wait");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(loadingGreet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


