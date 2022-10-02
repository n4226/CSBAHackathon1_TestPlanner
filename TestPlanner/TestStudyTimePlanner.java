package TestPlanner;

import java.util.ArrayList;

public class TestStudyTimePlanner {

    //this will figure out which days in the week to study for each test for the given month (1 = January, 2 = February, etc.)
    public static void plan(int forMonth) {
        ArrayList<TestInfo> tests = ManageTest.shared.getTests();
        //sort tests by the daysPerWeek peropetry in deceasing order (use tests.sort)
        tests.sort((t1, t2) -> t2.getDaysPerWeek() - t1.getDaysPerWeek());

        //create a 2D array of size 7 by the nubmer of weeks in the month
        //have to figure out how many weeks are in the month and which day of the week the month starts with
        //use the java.time package to figure out the number of weeks in the month and which day of the week the month starts with
        

        //figure out first day of week for the month




        
    }

}
