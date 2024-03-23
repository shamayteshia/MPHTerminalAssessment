

package com.mycompany.mphterminalassessment;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 *
 * @author teshia
 */
public class MPHTerminalAssessment {
    
     // DecimalFormat object for formatting decimal numbers
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) throws IOException {
        
        // Display greetings and main menu
        // Create an Instance object to call the welcome file and display
        Greetings displayGreeting = new loadingGreet();
        displayGreeting.welcomeGreet();
        displayGreeting.waitLoadingGreet();
        displayMainChoices();
    }
    
    private static void displayMainChoices() throws IOException {
        
        // Display main menu options
        System.out.print("\n");
        System.out.println("=================================");
        System.out.println("Please select from the menu..");
        System.out.println("=================================");
        System.out.println("1. Employee Information");
        System.out.println("2. View Gross Earnings");
        System.out.println("3. View Net Earnings");
        Scanner scan = new Scanner(System.in);
        System.out.println("=================================");
        System.out.print("Enter your selection: ");
        
        /*
        * This will get the selected item in choices and call the function to 
        * pass the variable and process the selected item.
        */
        String select = scan.nextLine();
        selectedOption(select);
        scan.close();
    }
    
    private static void selectedOption(String select) throws IOException {
        
        // Switch statement to determine the action based on user's selection
        switch (select) {
            case "1":
                // Display employee information
                employeeInfo(); 
                break;
            case "2":
                // View gross earnings
                employeeGrossSalary();
                break;
            case "3":
                // View net earnings
                employeeNetSalary();
                break;
            default:
                // This will show if the user input is not in the choices
                System.out.println("You entered an incorrect option.");
                System.out.println("Exiting now.......Goodbye");
                break;
        }
    }
    
    private static void employeeInfo() throws FileNotFoundException, IOException {
        // Maximum attempts for entering employee ID
        int maxAttempts = 3;
        // Attempt Counter
        int attempts = 0;
        
        // Scanner object for user input
        Scanner scan = new Scanner(System.in);
        
        while (attempts < maxAttempts) {
            
            System.out.print("Enter Employee ID: ");
            String lookup = scan.nextLine();
            
            //This should be change depends on where the file locate
           try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\teshia\\Downloads\\MotorPH Employee Datav4.txt1"))) {
                String str;
                boolean found = false;
                
                // Read file line by line to find employee information
                while ((str = br.readLine()) != null) {
                    if (str.contains(lookup)) {
                        String[] values = str.split(",");
                        System.out.println("\n*=================*EMPLOYEE INFO*=================*");
                        System.out.println("\nEmployee Number: " + values[0]);
                        System.out.print("\nEmployee Name: " + values[2] + " "+ values[1]); System.out.println("     " + "Birthdate: " +  values[3]); 
                        System.out.print("\nPosition: " + values[11]); System.out.println("     " + "Status: " + values[10]);
                        System.out.println("\nAddress: " + values[4]);
                        System.out.println("\n*=================*=============*=================*");
                        System.out.println("\nThank you for using the MotorPH Payroll System!");
                        found = true;
                        break;
                    }
                }
                
                // If employee is found, exit the loop
                if (found) {
                    break; // Exit the loop if employee found
                }   
                // If there is no employee found, This will display and increment the Attempts
                else {
                    attempts++;
                    System.out.println("Employee ID not found. Please try again.");
                }
            //This will also to prompt that if the file was not found
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
                return;
            }
            // This will output if the maximum attempt have been reached
            if (attempts == maxAttempts) {
                System.out.println("You have reached the maximum attempts. Exiting now...");
            }
            //Closing the Scanner
            scan.close();
        }
    }
    
    
    /**
 * This method calculates the gross weekly salary for an employee based on the hourly rate,
 * hours worked, and additional benefits such as rice subsidy, clothing allowance, and phone allowance.
 * It prompts the user to enter an employee ID and retrieves the corresponding data from a CSV file.
 * It then calculates the gross weekly salary using the hourly rate, hours worked, and benefits,
 * and displays the result with proper formatting.
 * If the employee ID is not found after the maximum attempts, it exits the method.
 * @throws FileNotFoundException If the specified file is not found.
 * @throws IOException           If an I/O error occurs while reading the file.
 */
    private static void employeeGrossSalary() throws FileNotFoundException, IOException {
        // Maximum attempts allowed for entering the employee ID
        int maxAttempts = 3;
        // Number of attempts made
        int attempts = 0;
        
        // Scanner object for user input
        Scanner scan = new Scanner (System.in);
        
        // Loop until the maximum attempts are reached
        while (attempts < maxAttempts) {
            
            // Create a SalaryData object to store employee data
            SalaryData employeeGross = new SalaryData();
            
            // Prompt the user to enter the employee ID
            System.out.print("Enter Employee ID: ");
            String lookup = scan.nextLine();
            
            try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\teshia\\Downloads\\MotorPH Employee Datav4.txt"))) {
                
                 // String to store each line of the file
                String str;
                // Flag to indicate if the employee is found
                boolean found = false;
                
                // Read each line of the file
                while ((str = br.readLine()) != null) {
                    // Check if the line contains the entered employee ID
                    if (str.contains(lookup)) {
                        // Split the line into an array of values
                        String[] values = str.split(",");
                        // Hourly rate of the employee
                        String empHourlyRate = values[18];
                        // Rice subsidy
                        String strRice = values[14];
                        // Clothing allowance
                        String strCloth = values[15];
                        // Phone allowance
                        String strPhone = values[16];
                        // Hours worked in a week
                        String strHrsWeekWorked = values[19];
                        
                        // Display employee information
                        System.out.println("\n*==============*EMPLOYEE INFO / GROSS EARNINGS*==============*");
                        System.out.println("Employee Number: " + values[0]);
                        System.out.println("Employee Name: " + values[2] + " "+ values[1]);
                        System.out.println("Birthdate: " +  values[3]);
                        System.out.println("Hourly Rate: " +  values[18]);
                        System.out.println("Number of Hours Worked in a Week: " + values[19]);
                        System.out.print("Gross Weekly Salary: ");
                        
                        try {
                            // Parse the hourly rate, rice subsidy, clothing allowance, phone allowance,
                            // and hours worked into double values
                            double dbHours = Double.parseDouble(empHourlyRate);
                            double dbRice = Double.parseDouble(strRice);
                            double dbCloth = Double.parseDouble(strCloth);
                            double dbPhone = Double.parseDouble(strPhone);
                            double dbHrsWeekWorked = Double.parseDouble(strHrsWeekWorked);
                            
                            // Set the hourly rate for the employee
                            employeeGross.setHourlyRate(dbHours);
                            // Calculate the gross weekly salary
                            double empGross = employeeGross.grossWeeklySalary(dbHrsWeekWorked);
                            // Calculate the sum of benefits per day
                            double benefitsSum = (dbRice + dbCloth + dbPhone) / 7;
                            // Calculate the total weekly gross salary including benefits
                            double empWeeklyGross = empGross + benefitsSum;
                            // Display the total weekly gross salary with proper formatting
                            System.out.println(df.format(empWeeklyGross));
                            
                        } catch (NumberFormatException ex) {
                            // Handle NumberFormatException
                            System.out.println(ex);
                        }
                        
                        // Display employee benefits
                        System.out.println("\n*=================*EMPLOYEE BENEFITS*=================*");
                        System.out.println("Rice Subsidy: " + values[14]);
                        System.out.println("Clothing Allowance: " + values[15]);
                        System.out.println("Phone Allowance: " + values[16]);
                        System.out.println("==================*=================*===================");
                        System.out.println("Thank you for using the MotorPH Payroll System!");
                        // Set the flag to true as the employee is found    
                        found = true;   
                        //Exit the loop
                        break;  
                    }
                }
                // If the employee is found, break out of the loop
                if (found) {
                    break; // Exit the loop if employee found
                }
                // If the employee is not found, increment the attempts counter
                else {
                    attempts++;
                    System.out.println("Employee ID not found. Please try again.");
                }
            //If the file was not found
            } catch (FileNotFoundException e) {
                System.err.println("File not Found: " + e.getMessage());
                return;
            }
        }
        //Closing the Scanner
        scan.close();
    }
    
    
    /**
 * Calculates the net weekly salary of an employee based on their hourly rate,
 * hours worked, and total deductions.
 * 
 * @throws IOException if there is an error reading the file
 */
    private static void employeeNetSalary() throws IOException {
        // Maximum attempts to enter employee ID
        int maxAttempts = 3;
        // Number of attempts made
        int attempts = 0;
        
        // Scanner for user input
        Scanner scan = new Scanner (System.in);
        
        // Loop until max attempts are reached
        while (attempts < maxAttempts) {
            
            // Instance of SalaryData class to handle salary data
            SalaryData employeeGross = new SalaryData();
            
            // Prompt user to enter employee ID
            System.out.print("Enter Employee ID: ");
            String lookup = scan.nextLine();
            
            // Read each line of the file
            try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\teshia\\Downloads\\MotorPH Employee Datav4.txt"))) {
                String str;
                boolean found = false;
                // Check if the line contains the entered employee ID
                while ((str = br.readLine()) != null) {
                    if (str.contains(lookup)) {
                        String[] values = str.split(",");
                        String empHourlyRate = values[18];
                        String strRice = values[14];
                        String strCloth = values[15];
                        String strPhone = values[16];
                        String strHrsWeekWorked = values[19];
                        String strTotalDeduction = values[29];
                        
                        // Print employee information and headings
                        System.out.println("\n*==============*EMPLOYEE INFO / NET EARNINGS*==============*");
                        System.out.println("Employee Number: " + values[0]);
                        System.out.println("Employee Name: " + values[2] + " "+ values[1]);
                        System.out.println("Birthdate: " +  values[3]);
                        System.out.println("Hourly Rate: " +  values[18]);
                        System.out.println("Number of Hours Worked in a Week: " + values[19]);
                        System.out.println("Total Deduction: " + values[29]);
                        System.out.print("Gross Weekly Salary: ");
                        System.out.print("Net Weekly Salary: ");
                        
                        // Parse relevant values to double
                        try {
                            double dbHours = Double.parseDouble(empHourlyRate);
                            double dbRice = Double.parseDouble(strRice);
                            double dbCloth = Double.parseDouble(strCloth);
                            double dbPhone = Double.parseDouble(strPhone);
                            double dbHrsWeekWorked = Double.parseDouble(strHrsWeekWorked);
                            double dbTotalDeduction = Double.parseDouble(strTotalDeduction);
                            
                            // Set hourly rate in SalaryData object
                            employeeGross.setHourlyRate(dbHours);
                            // Calculate gross weekly salary
                            double empGross = employeeGross.grossWeeklySalary(dbHrsWeekWorked);
                            // Calculate total benefits sum per day
                            double benefitsSum = (dbRice + dbCloth + dbPhone) / 7;
                            // Calculate total weekly gross salary
                            double empWeeklyGross = empGross + benefitsSum;
                            // Calculate net weekly salary
                            double empNetSalary = empWeeklyGross - dbTotalDeduction;
                            // Print formatted net weekly salary
                            System.out.println(df.format(empNetSalary));
                            
                        } catch (NumberFormatException ex) {
                            System.out.println(ex);
                        }
                        
                        // Print employee benefits
                        System.out.println("\n*=================*EMPLOYEE BENEFITS*=================*");
                        System.out.println("Rice Subsidy: " + values[14]);
                        System.out.println("Clothing Allowance: " + values[15]);
                        System.out.println("Phone Allowance: " + values[16]);
                        System.out.println("==================*=================*===================");
                        System.out.println("Thank you for using the MotorPH Payroll System!");
                        found = true;
                        break;  
                    }
                }
                
                // Exit loop if employee found
                if (found) {
                    break; // Exit the loop if employee found
                }   
                // Increment attempts if employee not found
                else {
                    attempts++;
                    System.out.println("Employee ID not found. Please try again.");
                }
                
            } catch (FileNotFoundException e) {
                System.err.println("File not Found: " + e.getMessage());
                return;
            }
        }
        // Close scanner after use
        scan.close();
    }
}