package TestPlanner;
//CSBA hackathon Fall 2022
import java.util.Scanner;
public class InputOutput {
    
    public static void runUI() {
        boolean programRunning = true;
        int menuOption = 0;
        Scanner scan = new Scanner(System.in); 
        //make all calls to scan.next() after this line will stop at the \n (new line) character instead of space
        scan.useDelimiter("\n");
        while (programRunning){

            System.out.println("Welcome to the program. Please select a number:");
            System.out.println("1 - Add a test date");
            System.out.println("2 - Remove a test date");
            System.out.println("3 - View your study schedule");
            System.out.println("4 - View a current class");
            System.out.println("5 - Remove all classes");//have confirmation
            System.out.println("6 - Exit");

            System.out.println("Select a menu option ");
            if(scan.hasNextInt()){//checks if user input is an integer
                menuOption = scan.nextInt(); 
            }else{
                System.out.println("You entered an invalid input");
                scan.nextLine();//clears the line
            }
            
            if (menuOption == 1) {//create name of class
                System.out.println("Insert the class name."); 
                String className = scan.next();//let user enter value

                System.out.println("Insert the day of your exam (1-31)"); 
                int day = scan.nextInt();
                System.out.println("Insert the month of your exam (1-12)"); 
                int month = scan.nextInt();
                System.out.println("Insert the year of your exam (YYYY)"); 
                int year = scan.nextInt();

                System.out.println("How many times per week do you want to study for?");
                int studyTimePerWeek = scan.nextInt();

                System.out.println("How many weeks are there before your exam?");
                int weekBeforeExam = scan.nextInt();

                TestInfo newTest = new TestInfo(className, day, month, year, studyTimePerWeek, weekBeforeExam);
                if (ManageTest.shared.addTest(newTest)){
                    System.out.println("Class added successfully");
                }else{
                    System.out.println("A class with the same name already exists, please try again with a different name");
                }
            }
            else if (menuOption == 2) {//all you need to do is insert the name of the class
                System.out.println("Insert the class you want to remove"); 
                String className = scan.next();
                if (ManageTest.shared.removeTest(className)) {
                    System.out.println("Class removed successfully");
                }else{
                    System.out.println("Your requested class doesn't exist, please enter another class");
                }
            }
            else if (menuOption == 3) {//view your study schedule
                System.out.println("Choose a month to view your schedule");
                String month = scan.next();
                //code that shows the schedule of the user on th particular month
                
            }
            else if(menuOption == 4){
                System.out.println("Insert the name of the class you want to view");
                String className = scan.next();

            }else if(menuOption == 5){
                System.out.println("Are you sure you want to remove all classes?");
                String confirm = scan.next();
               boolean confirmCorrectly = confirm.compareTo("yes") == 0;
                if(confirmCorrectly){//user wants to remove all classes
                    boolean yes = true;
                    System.out.println("You successfully removed all your classes");
                }else{//user doesn't want to remove all classes
                    System.out.println("You will be directed to main menu");
                }
            }else{
                System.out.println("Thank you");
                programRunning = false;
            }
        }
             

        

        scan.close();
    }

}
