package TestPlanner;
import java.util.ArrayList;
//CSBA hackathon Fall 2022
import java.util.Scanner;
public class InputOutput {
    
    public static void runUI() {
        boolean programRunning = true;
        int menuOption = 0;
        Scanner scan = new Scanner(System.in); 
        //make all calls to scan.next() after this line will stop at the \n (new line) character instead of space
        scan.useDelimiter("\n");
        System.out.println("Welcome to the program.");
        while (programRunning){
            System.out.println("Main Menu. Please select a number:");
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

                System.out.println("How many weeks are there before your exam? Insert a number between 1-4");
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
                System.out.println("Choose a month to view your schedule (in integers)");
                int month = scan.nextInt();
                System.out.println("Choose a year to view your schedule (YYYY)");
                int year = scan.nextInt();

                //code that shows the schedule of the user on th particular month
                ArrayList<String>[][] studyPlan = TestStudyTimePlanner.plan(month, year);

                //print out header for callander
                System.out.println("");
                System.out.println("Study plan for " + month + "-" + year);
                for (int i = 0; i < 7; i++) {
                    String term = "|";
                    if (i<7) {
                        term = "\t";
                    }
                    System.out.print("\t" + TestStudyTimePlanner.getDayOfWeekString(i) + term);
                }
                System.out.println("\n--------------------------------------------------------------------------------------");

                //print out this is week and value of i 
                for(int i = 0; i < studyPlan.length; i++){//looping over each week; studyPlan.length= # weeks in month
                    System.out.print("|");
                    for(int j = 0; j<studyPlan[i].length; j++){//for each week, loop for each day in that week; j = day of the week
                        String term = "";
                        if (i<studyPlan[i].length) {
                            term = "\t";
                        }
                        ArrayList<String> classesToStudyToday = studyPlan[i][j];
                        if (classesToStudyToday != null) {
                            for(int k = 0; k < classesToStudyToday.size(); k++){
                                String term2 = "";
                                if (i<classesToStudyToday.size() - 1) {
                                    term2 = ", ";
                                }
                                System.out.print("\t" + classesToStudyToday.get(k) + term2);
                            }
                            System.out.print(term);
                        } else {
                            System.out.print("\t-\t");
                        }
                        System.out.print("|");
                    }
                    System.out.println("");//new line after each week
                }

                
            }
            else if(menuOption == 4){
                System.out.println("Insert the name of the class you want to view");
                String className = scan.next();

                TestInfo test = ManageTest.shared.findTestInfo(className);

                if (test != null) {
                    System.out.println(test.toString());
                } else {
                    System.out.println("The name of the class does not exist");
                }

            }else if(menuOption == 5){
                System.out.println("Are you sure you want to remove all classes?");
                String confirm = scan.next();
               boolean confirmCorrectly = confirm.compareTo("yes") == 0;
                if(confirmCorrectly){//user wants to remove all classes
                    boolean yes = true;
                    ManageTest.shared.clearAll();
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
