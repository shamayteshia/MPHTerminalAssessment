
package com.mycompany.mphterminalassessment;

/**
 *
 * @author teshia
 */
public class SalaryData {
    
    // Member variables to store hourly rate and employee ID
    private double hourlyRate;
    private String employeeID;
    
    // Getter method for hourly rate
    public double getHourlyRate() {
        return hourlyRate;
    }
    
    // Setter method for hourly rate
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    // Getter method for employee ID
    public String getEmployeeID() {
        return employeeID;
    }
    
    // Setter method for employee ID
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    
    // Method to calculate gross weekly salary
    public double grossWeeklySalary(double dbHrsWeekWorked) {
        return hourlyRate * dbHrsWeekWorked;
    }
    

}
