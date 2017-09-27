package feecalculator;

/**
 * FeeCalculator class has many methods which would accept parameters like 
 * semester ,prerequisites and GPA values to calculate fees and 
 * scholarships of a student.
 * 
 * @author Harish Bondalapati
 */
public class FeeCalculator {
    
    /**
     * Non-Resident Undergraduate Incidental fee value per credit hour in USD.
     */
    public static final double UG_INCIDENTAL_FEE=406.35;

    /**
     * Non-Resident Undergraduate Designated fee value per credit hour in USD.
     */
    public static final double UG_DESIGNATED_FEE=104.80;

    /**
     * Non-Resident Undergraduate Textbook fee value per credit hour in USD.
     */
    public static final double UG_TEXTBOOK_FEE=6;

    /**
     * Non-Resident Undergraduate Technology fee value per credit hour in USD.
     */
    public static final double UG_TECHNOLOGY_FEE=20.70;

    /**
     * Non-Resident Undergraduate Computer Science fee value per 
     * credit hour in USD.
     */
    public static final double UG_COMPUTERSCIENCE_FEE=38;
    
    /**
     * Non-Resident Graduate Incidental fee value per credit hour in USD.
     */
    public static final double G_INCIDENTAL_FEE=505.72;

    /**
     * Non-Resident Graduate Designated fee value per credit hour in USD.
     */
    public static final double G_DESIGNATED_FEE=115.55;

    /**
     * Non-Resident Graduate Textbook fee value per credit hour in USD.
     */
    public static final double G_TEXTBOOK_FEE=0;

    /**
     * Non-Resident Graduate Technology fee value per credit hour in USD.
     */
    public static final double G_TECHNOLOGY_FEE=20.70;

    /**
     * Non-Resident Graduate Computer Science fee value per 
     * credit hour in USD.     
     */
    public static final double G_COMPUTERSCIENCE_FEE=38;
    
    /**
     * International Graduate Achievement Scholarship worth $1,000
     * <br>  This scholarship is only for the first semester.
     */    
    public static final double INTL_GRAD_ACHIEVEMENT_SCHOLARSHIP=1000;

    /**
     * Graduate Non-Resident Waiver 50% of incidental tuition 
     *                          (for all the courses in that semester).
     * <br>  International students achieve this scholarship only in their 2nd , 
     *   3rd and 4th semester if their GPA is greater than or equal to 3.33.
     */
    public static final double GRADUATE_WAIVER_PERCENT=50;
    
    /**
     * Semester 1 : $456.48		
     * <br>              Student insurance for the first semester.
     */
    public static final double INSURENCE_FEE_SEMESTER_I=456.48;

    /**
     * Semester 2 (including semester 3) : $760.2		
     * <br>              Student insurance for both second semester 
     *   and third semester is $760.20. Use this value only while calculating 
     *   the second-semester fee.
     */
    public static final double INSURENCE_FEE_SEMESTER_II=760.2;

    /**
     * Semester 3 : $ 0.0		
     * <br>              Student insurance for semester 3 is already included 
     * in semester 2.
     */
    public static final double INSURENCE_FEE_SEMESTER_III=0;

    /**
     * 3.Semester 4 : $456.48
     * <br>              Student insurance for the fourth semester.
     */
    public static final double INSURENCE_FEE_SEMESTER_IV=456.48;
    
    /**
     * International student service fee : $75
     * <br>      International student service fee is the fee charged towards 
     * every semester for international students.
     */
    public static final double INTL_STUDENT_SERVICE_FEE=75;

    /**
     * Orientation Fee	: $75
     * <br>      Orientation fee is charged only when a new student attends 
     * Northwest and will be applied only to the first-semester fee
     */
    public static final double ORIENTATION_FEE=75;
    
    /**
     * Currency conversion from Dollar to Rupee
     */
    public static final double DOLLAR_TO_RUPEE=64.55;

    /**
     * Currency conversion from Dollar to Euro
     */
    public static final double DOLLAR_TO_EURO=0.83;
    
    private String studentName;
    
    /**
     * Initializes a newly created Student object by accepting student name
     * attribute.
     * 
     * @param studentName Name of the student
     */
    public FeeCalculator(String studentName){
        this.studentName=studentName;
    }

    /**
     * Returns student name
     * 
     * @return Name of the Student
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets student name attribute
     * 
     * @param studentName Name of the Student
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    /**
     * Extracts initials of a student name and returns the value as a string.
     * 
     * @return initials - Initials of Student Name
     */
    public String getNameInitials(){
        String temp=studentName.replaceAll("[^\\S]+", " ").trim();
        int idx=temp.indexOf(" ");
        String initials=""+temp.charAt(0)+".";
        while(idx!=-1){
            initials=initials+temp.charAt(idx+1)+".";
            idx=temp.indexOf(" ", idx+1);
        }
        return initials.toUpperCase();
    }
    
    /**
     * Calculates and returns total cost of prerequisites.
     * 
     * @param prerequisites Number of pre-requisites or under graduate courses.
     * 
     * @return cost Sum of (total cost per credit hour)*(No of credit Hours) 
     * for each prerequisite <br>  i.e (UG_INCIDENTAL_FEE + 
     *      UG_DESIGNATED_FEE + UG_TEXTBOOK_FEE + UG_TECHNOLOGY_FEE + 
     *      UG_COMPUTERSCIENCE_FEE)*3
     */
    private double calcPrereqCost(int prerequisites){
        return prerequisites*(UG_INCIDENTAL_FEE
                + UG_DESIGNATED_FEE+UG_TEXTBOOK_FEE
                + UG_TECHNOLOGY_FEE+UG_COMPUTERSCIENCE_FEE)*3;
    }
    
    /**
     * Calculates and returns number of required courses.
     * 
     * @param prerequisites Number of pre-requisites or under graduate courses.
     * 
     * @return requiredCourses Returns number of required courses.
     */
    private int findReqCoursesForSem1(int prerequisites){
        if(prerequisites>0)
            return 2;
        else
            return 3;
    }
    
    
    /**
     * Calculates and returns total cost per course.
     * 
     * @param prerequisites Number of pre-requisites or under graduate courses.
     * 
     * @return cost Sum of (total cost per credit hour)*(No of credit Hours) 
     * or each prerequisite <br>  i.e (G_INCIDENTAL_FEE + 
     *      G_DESIGNATED_FEE + G_TEXTBOOK_FEE + G_TECHNOLOGY_FEE + 
     *      G_COMPUTERSCIENCE_FEE)*3
     */
    private double calcReqCourseCostForOneCourse(){
        return (G_INCIDENTAL_FEE
                + G_DESIGNATED_FEE+G_TEXTBOOK_FEE
                + G_TECHNOLOGY_FEE+G_COMPUTERSCIENCE_FEE)*3;
    }
    
    /**
     * Calculates and returns total scholarship of a student based on the 
     * semester and GPA earned for a semester.
     * 
     * @param semester Semester of the course of a student.
     * @param prerequisites Number of prerequisites of a student.
     * @param gpa GPA of the student.
     * 
     * @return Total Scholarship earned for a semester based on the 
     * semester, prerequisites and GPA. For semester 2,3 & 4 the amount for 
     * scholarship is calculated on the Incidental Fee for a course over 
     * total credit hours
     * <br> Sem 1 = INTL_GRAD_ACHIEVEMENT_SCHOLARSHIP
     * <br> Sem 2/3/4 = (No of Courses*G_INCIDENTAL_FEE*
     *                  GRADUATE_WAIVER_PERCENT*No of credit Hours)/100
     * 
     */
    private double calcScholarship(int semester,int prerequisites,double gpa){
        if(semester==1){
            return INTL_GRAD_ACHIEVEMENT_SCHOLARSHIP;
        }
        else if(prerequisites==0 && semester==4 && gpa>=3.33){
            return 2*(G_INCIDENTAL_FEE*3*GRADUATE_WAIVER_PERCENT)/100;
        }
        else if(gpa>=3.33 && (semester==2 || semester==3 || semester==4)){
            return 3*(G_INCIDENTAL_FEE*3*GRADUATE_WAIVER_PERCENT)/100;
        }else
        return 0.0;
    }
    
    /**
     * Calculates and returns total scholarship of a student based on the 
     * semester and GPA earned till semester.
     * 
     * @param semester Semester of the course of a student.
     * @param prerequisites Number of prerequisites of a student.
     * @param gpa GPA of the student.
     * 
     * @return Total Scholarship earned till semester
     * <br>Total Scholarship=sum of scholarship for each semester.
     */
    private double calcTotalScholarship(int semester,int prerequisites
            ,double gpa){
        double totScholarship=this.calcScholarship(1, 
                            prerequisites, gpa);
        if(gpa>=3.33){
            switch(semester){
                case 4: 
                    totScholarship+= this.calcScholarship(4, 
                            prerequisites, gpa);
                case 3: 
                    totScholarship+= this.calcScholarship(3, 
                            prerequisites, gpa);
                case 2: 
                    totScholarship+= this.calcScholarship(2, 
                            prerequisites, gpa);
                    break;
                default :
                    totScholarship+=0.0;
                    break;
            }
        }
        return totScholarship;
    }
    
    /**
     * Calculates and returns total cost for that semester.
     * 
     * @param semester Semester of the course of a student.
     * @param prerequisites Number of prerequisites of a student.
     * @param gpa GPA of the student.
     * 
     * @return Total cost for a semester.
     * <br>    i.e = Total cost for each course*no of courses
     *              + INSURENCE_FEE_SEMESTER_I + INTL_STUDENT_SERVICE_FEE
                    + ORIENTATION_FEE - scholarship for that semester
     */
    public double calcSemCost(int semester, int prerequisites, double gpa){
        double semCost;
        switch(semester){
                case 1:  
                    semCost= this.calcReqCourseCostForOneCourse()
                            * this.findReqCoursesForSem1(prerequisites)
                            + this.calcPrereqCost(prerequisites)
                            + INSURENCE_FEE_SEMESTER_I
                            + INTL_STUDENT_SERVICE_FEE
                            + ORIENTATION_FEE
                            - this.calcScholarship(1, prerequisites, gpa);
                    break;
                case 2:  
                    semCost= this.calcReqCourseCostForOneCourse()*3
                            + INSURENCE_FEE_SEMESTER_II
                            + INTL_STUDENT_SERVICE_FEE
                            - this.calcScholarship(2, prerequisites, gpa);
                    break;
                case 3: 
                    semCost= this.calcReqCourseCostForOneCourse()*3
                            + INSURENCE_FEE_SEMESTER_III
                            + INTL_STUDENT_SERVICE_FEE
                            - this.calcScholarship(3, prerequisites, gpa);
                    break;
                case 4:
                    semCost= this.calcReqCourseCostForOneCourse()
                            * (prerequisites==0?2:3)
                            + INSURENCE_FEE_SEMESTER_IV
                            + INTL_STUDENT_SERVICE_FEE
                            - this.calcScholarship(4, prerequisites, gpa);
                    break;  
                default :
                    semCost=0.0;
                    break;
            }
        return semCost;
    }
    
    /**
     * Calculates and returns total cost till semester.
     * <br>Total Cost till semester=Sum of cost of semester till semester 
     * provided
     * <br>     Ex: total cost till sem 2 =(sem 1 total cost)+(sem 2 total cost)
     * 
     * @param semester Semester of the course of a student.
     * @param prerequisites Number of prerequisites of a student.
     * @param gpa GPA of the student.
     * 
     * @return Total cost till semester.
     */
    public double calcTotalCost(int semester,int prerequisites,double gpa){
        double totalCost=0.0;
            switch(semester){
                case 4: 
                    totalCost+= this.calcSemCost(4, 
                            prerequisites, gpa);
                case 3: 
                    totalCost+= this.calcSemCost(3, 
                            prerequisites, gpa);
                case 2: 
                    totalCost+= this.calcSemCost(2, 
                            prerequisites, gpa);
                case 1: 
                    totalCost+= this.calcSemCost(1, 
                            prerequisites, gpa);
                    break;
                default :
                    totalCost=0.0;
                    break;
            }
        return totalCost;
    }
    
    /**
     * Converts and returns amount in rupees for the argument passed in dollar.
     * <br> Amount in Rupee=Amount in Dollar*Conversion rate to dollars.
     * 
     * @param costInDollars Amount in dollar
     * @return Value converted to rupees 
     */
    public double exchUSDToINR(double costInDollars){
        return costInDollars*DOLLAR_TO_RUPEE;
    }
    
    /**
     * Converts and returns amount in euro for the argument passed in dollar.
     * <br> Amount in Euro=Amount in Dollar*Conversion rate to dollars
     * 
     * @param costInDollars Amount in dollar
     * @return Value converted to rupees 
     */
    public double exchUSDToEuro(double costInDollars){
        return costInDollars*DOLLAR_TO_EURO;
    }
    
    @Override
    public String toString(){
        return this.getNameInitials();
    }
    
    /**
     * Prints Receipt of a Student by calculating total scholarship 
     * earned and total fee need to be paid till semester.
     * 
     * @param semester Semester of the course of a student.
     * @param prerequisites Number of prerequisites of a student.
     * @param gpa GPA of the student.
     */
    public void printReceipt(int semester, int prerequisites, double gpa){
        System.out.printf(this.getNameInitials()+", on a whole the total "
                + "scholarship till semester "+semester+" is: $ %.2f"
                , this.calcTotalScholarship(semester, prerequisites, gpa) );
        System.out.printf("\nTotal fee till semester "+semester+" is: $ %.2f\n"
                ,this.calcTotalCost(semester, prerequisites, gpa));
    }
    
}
