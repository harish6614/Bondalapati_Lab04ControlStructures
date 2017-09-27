package feecalculator;

import java.util.Scanner;

/**
 * FeeCalculatorDriver class is used to accept values from console and invoke 
 * FeeCalculator methods to summarize and print student details.
 * 
 * @author Harish Bondalapati
 */
public class FeeCalculatorDriver {
    
    /**
     *
     * @param args Arguments for method main
     */
    public static void main(String args[]){
        
        System.out.println("*******************************\n" 
                + "***Northwest Cost Calculator***\n" 
                + "*******************************");
        char stopFlag;
        Scanner scannerObj=new Scanner(System.in);
        
        do{
            System.out.print("\nPlease enter the full name (Firstname Lastname): ");
            String name=acceptName(scannerObj);
            System.out.print("Please enter the number of pre-requisites: ");
            int prerequisites=acceptPrerequisites(scannerObj);
            System.out.print("Please enter the number of semesters you want to "
					+ "calculate the cumulative fee: ");
            int semesters=acceptSemester(scannerObj);
            double gpa=0.0;
            if(semesters!=1){
                System.out.print("Enter the cumulative GPA: ");
                gpa=acceptGPA(scannerObj);
            }
            assignAndPrintStudentReport(name,semesters,prerequisites,gpa);
        
            System.out.print("\nDo you want to calculate again?(Y/N): ");
            stopFlag=scannerObj.next().charAt(0);
            scannerObj.nextLine();
        }while(Character.toUpperCase(stopFlag)=='Y');
        
        scannerObj.close();
        System.out.println("\nThank You! All the best.");
    }
    
    /**
     * Method to pass a scanner object as parameter to accept semester from 
     * console and return back the value provided as integer.
     *  
     * Semester value should be between 1-4 only.Any other value provided will 
     * not be accepted and would request to provide valid value from 
     * console again.
     * 
     * @param scannerObj Scanner object which is used to allow input 
     * from console
     * @return semester Semester of the Student provided in console
     */
    public static int acceptSemester(Scanner scannerObj){
        int semester=scannerObj.nextInt();
        switch(semester){
            case 1 :
            case 2 :
            case 3 :
            case 4 :
                return semester;
            default :
                System.out.print("You have entered invalid number, "
                        + "please re-enter either 1 or 2 or 3 or 4: ");
                return acceptSemester(scannerObj);
        }
    }
    
    /**
     * Method to pass a scanner object as parameter to accept prerequisites 
     * from console and return back the value provided as integer.
     *  
     * Number of prerequisites should be between 0-2 only.Any other value 
     * provided will not be accepted and would request to provide valid value 
     * from console again.
     * 
     * @param scannerObj Scanner object which is used to allow input 
     * from console
     * @return prerequisites Prerequisites of the Student provided in console
     */
    public static int acceptPrerequisites(Scanner scannerObj){
        int prerequisites=scannerObj.nextInt();
        switch(prerequisites){
            case 0 :
            case 1 :
            case 2 :
                return prerequisites;
            default :
                System.out.print("You have entered invalid number, "
                        + "please re-enter either 0 or 1 or 2: ");
                return acceptPrerequisites(scannerObj);
        }
    }
    
    /**
     * Method to pass a scanner object as parameter to accept student name 
     * from console and return back the value provided as string.
     *  
     * If spaces/empty name has been entered it would  request to provide 
     * input again.
     * 
     * @param scannerObj Scanner object which is used to allow input 
     * from console
     * @return Name Name of the Student provided in console
     */
    public static String acceptName(Scanner scannerObj){
        String name=scannerObj.nextLine();
        if(!(name.trim()).equals(""))
                return name;
        else{
            System.out.print("No student name have been entered, "
                        + "please re-enter: ");
                return acceptName(scannerObj);
        }
    }
    
    /**
     * Method to pass a scanner object as parameter to accept GPA 
     * from console and return back the value provided as double.
     *  
     * GPA should be between 0-4 only.Any other value provided will not be
     * accepted and would request to provide valid value from console again.
     * 
     * @param scannerObj Scanner object which is used to allow input 
     * from console
     * @return gpa - Grade Point Average of the Student provided in console
     */
    public static double acceptGPA(Scanner scannerObj){
        double gpa=scannerObj.nextDouble();
        if(gpa>=0 && gpa<=4)
                return gpa;
        else{
            System.out.print("The GPA value should be in between 0 and 4, "
                    + "please re-enter: ");
                return acceptGPA(scannerObj);
        }
    }
    
    /**
     * Method to initialize a FeeCalculator object with the student name 
     * from the parameter passed and prints the summary of the fee,
     * scholarship(individual semester and total till semester) by invoking
     * the required methods of FeeCalculator using semester prerequisites 
     * and GPA values passed. 
     * 
     * @param name Name of the student
     * @param semester Semester of the course of the student.
     * @param prerequisites Number of prerequisites of the student.
     * @param gpa GPA of the student.
     */
    public static void assignAndPrintStudentReport(String name,int semester,
            int prerequisites,double gpa){
        FeeCalculator feeCalcObj=new FeeCalculator(name);
        System.out.println("\n****************************************\n" 
                + "* Hello, "+feeCalcObj.getNameInitials());
        System.out.println("*------------------------------------\n" 
                + "* Your Account Summary\n" 
                + "*------------------------------------\n" 
                + "*__________________________________");
        
        for(int i=1;i<=semester;i++){
            System.out.printf("* Semester "+ i +" fee is:    $ %.2f * \n",
                    feeCalcObj.calcSemCost(i, prerequisites, gpa));
        }
        double totalFee=feeCalcObj.calcTotalCost(semester, prerequisites, gpa);
        System.out.printf("*----------------------------------    \n"
                + "* Total cost:           $ %.2f"
                + "\n*----------------------------------    \n*\n",totalFee);
        
        System.out.printf("* USD to INR: Rs. %.2f\n"
                ,feeCalcObj.exchUSDToINR(totalFee));
        System.out.printf("* USD to Euro: € %.2f\n"
                ,(feeCalcObj.exchUSDToEuro(totalFee)));
        
        System.out.println("****************************************");
        feeCalcObj.printReceipt(semester, prerequisites, gpa);
    }
}