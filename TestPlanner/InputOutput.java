package TestPlanner;
//CSBA hackathon Fall 2022
import java.util.Scanner;
public class InputOutput {
    
    public static void runUI() {
        boolean programRunning = true;
        int menuOption = 0;
        Scanner scan = new Scanner(System.in); 
        while (programRunning){

            System.out.println("Welcome to the program. Select a number:");
            System.out.println("1 - Add a test date");
            System.out.println("2 - Remove a test date");
            System.out.println("3 - View your study schedule");
            System.out.println("4 - Exit");

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

                        System.out.println("Insert the month and day of your exam"); 
                        String date = scan.next();
                        
                    }
            if (menuOption == 2) {//all you need to do is insert the name of the class
                        System.out.println("Insert the month and day of the test date you want to remove");
                        String date = scan.next();
                        System.out.println("Insert the class you want to remove"); 
                        String className = scan.next();
                       
                    }
            if (menuOption == 3) {//view your study schedule
                        System.out.println("Choose a month to view your schedule");
                        String month = scan.next();
                        
                    }
            }
             

        

        scan.close();
    }

}
