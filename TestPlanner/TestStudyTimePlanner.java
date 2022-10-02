package TestPlanner;

import java.util.ArrayList;

public class TestStudyTimePlanner {

    //month is 1-12, day is 1-31, year is 2000-2099
    //should return what day of the week the given date is on
    public static int getDayOfWeek(int month, int dayinmonth, int year) {
        int last2YearDigits = (year % 100);
       int yearCode = (last2YearDigits + (last2YearDigits / 4)) % 7;
       int monthCode = 0;
       switch(month){
       case 1:
           monthCode = 0;
           break;
       case 2:
           monthCode = 3;
           break;
       case 3:
           monthCode = 3;
           break;
       case 4:
           monthCode = 6;
           break;
       case 5:
           monthCode = 1;
           break;
       case 6:
           monthCode = 4;
           break;
       case 7:
           monthCode = 6;
           break;
       case 8:
           monthCode = 2;
           break;
       case 9:
           monthCode = 5;
           break;
       case 10:
           monthCode = 0;
           break;
       case 11:
           monthCode = 3;
           break;
       case 12:
           monthCode = 5;
           break;
        }
       
       int yearFirstTwoDigits = year/100;
       int centuryCode = 0;
       switch(yearFirstTwoDigits){
       case 17:
           centuryCode = 4;
           break;
       case 18:
           centuryCode = 2;
           break;
       case 19:
           centuryCode = 0;
           break;
       case 20:
           centuryCode = 6;
           break;
       case 21:
           centuryCode = 4;
           break;
       case 22:
           centuryCode = 2;
           break;
       case 23:
           centuryCode = 0;
           break;
          
       }
 
       int leapYear = 0;
       if ((month <= 2) && ((year % 4 == 0) && ((year % 100 != 0 ) || (year % 400 == 0)))){
           leapYear = 1;
       }
       else {
           leapYear = 0;
       }
 
 
       int dayOfWeekNumber = (yearCode + monthCode + centuryCode + dayinmonth - leapYear) % 7;
       return dayOfWeekNumber;
    }

    public static int getDaysInMonth(int month, int year) {
        
        boolean thirtyOneDaysInMonth = (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12);
        boolean thirtyDaysInMonth = (month == 4 || month == 6 || month == 9 || month == 11);
        boolean twentyEightDaysInMonth = (month == 2);

        if (thirtyOneDaysInMonth) {
            return 31;
        }
        else if (thirtyDaysInMonth) {
            return 30;
        }
        else if (twentyEightDaysInMonth) {
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                return 29;
            }
            else {
                return 28;
            }
        }
        else {
            return 0;
        }
    }

    public static String getDayOfWeekString(int dayOfWeekNumber) {
        //dayOfWeekNumber is 0-6, 0 is Sunday, 1 is Monday, etc.
        String dayOfWeekString = "";
        switch(dayOfWeekNumber){
        case 6:
            dayOfWeekString = "Sunday";
            break;
        case 0:
            dayOfWeekString = "Monday";
            break;
        case 1:
            dayOfWeekString = "Tuesday";
            break;
        case 2:
            dayOfWeekString = "Wednesday";
            break;
        case 3:
            dayOfWeekString = "Thursday";
            break;
        case 4:
            dayOfWeekString = "Friday";
            break;
        case 5:
            dayOfWeekString = "Saturday";
            break;
        }
        return dayOfWeekString;
    }

    //index1 is the index of the day of the week
    private static int index1ForDayInMonth(int dayInMonth, int startingDayOfWeek) {
        //startingDayOfWeek is 0-6, 0 is Sunday, 1 is Monday, etc. and is the day of the week that the first day of the month is on
        //dayInMonth is 1-31
        //index1 is 0-6, 0 is the first day of the week, 1 is the second day of the week, etc.
        int index1 = (dayInMonth + startingDayOfWeek - 2) % 7;
        return index1;
    }
    //index2 is the index of the week in the month
    private static int index2ForDayInMonth(int dayInMonth, int startingDayOfWeek) {
         //startingDayOfWeek is 0-6, 0 is Sunday, 1 is Monday, etc. and is the day of the week that the first day of the month is on
        //dayInMonth is 1-31
        //index2 is 0-4, 0 is the first week of the month, 1 is the second week of the month, etc.
        int index2 = (dayInMonth + startingDayOfWeek - 2) / 7;
        return index2;
    }

    //TODO: return the day of week the month starts on
    //this will figure out which days in the week to study for each test for the given month (1 = January, 2 = February, etc.)
    public static ArrayList<String>[][] plan(int forMonth, int forYear) {
        
        ArrayList<TestInfo> tests = ManageTest.shared.getTests();
        //sort tests by the daysPerWeek peropetry in deceasing order (use tests.sort)
        tests.sort((t1, t2) -> t2.getDaysPerWeek() - t1.getDaysPerWeek());

        //figure out what day of the week the first day of the month is (use the getDayOfWeek method)
        int startingDayOfWeek = getDayOfWeek(forMonth, 1, forYear);

        //create a 2d array of strings, where the first dimension is the day of the week (0 = Sunday, 1 = Monday, etc.) and the second dimension is the numbers of weeks in a month (0 = first week, 1 = second week, etc.)
        ArrayList<String>[][] studyPlan = new ArrayList[5][7];

        ;
        //loop through the each test in the tests array

        for (TestInfo test : tests) {
            //for each test, start planning studing by starting on the day of the test and working backwards by the number of weeksBeforeTestToStartStudying
            //for each day that you are planning to study, figure out which index it is in the 2d array (use the index1ForDayInMonth and index2ForDayInMonth methods)
            //add the test name to the 2d array at the index you just figured out
            int dayOfTest = test.getDay();
            int monthOfTest = test.getMonth();
            int yearOfTest = test.getYear();
            int startingDayOfWeekOfTest = getDayOfWeek(monthOfTest, 1, yearOfTest);
            int index1 = index1ForDayInMonth(dayOfTest, startingDayOfWeekOfTest);
            int index2 = index2ForDayInMonth(dayOfTest, startingDayOfWeekOfTest);
            if (monthOfTest == forMonth && yearOfTest == forYear) {
                studyPlan[index2][index1] = new ArrayList<String>();
                studyPlan[index2][index1].add("*" + test.getClassName());
            }

            for (int i = 1; i <= test.weeksBeforeTest(); i++) {
                int dayToStudy = dayOfTest - (i * 7);

                //if the day to study is before the first day of the month, then you need to figure out the day of the week of the last day of the previous month
                // make sure to skip any days that are not in the month you are planning for
                boolean inMonthPLanningFor = true;
                if (dayToStudy < 1) {
                    int previousMonth = monthOfTest - 1;
                    int previousYear = yearOfTest;
                    if (previousMonth < 1) {
                        previousMonth = 12;
                        previousYear = yearOfTest - 1;
                    }
                    int daysInPreviousMonth = getDaysInMonth(previousMonth, previousYear);
                    dayToStudy = daysInPreviousMonth + dayToStudy;
                    if (previousMonth != forMonth || previousYear != forYear) {
                        inMonthPLanningFor = false;
                    }
                } else {
                    if (monthOfTest != forMonth || yearOfTest != forYear) {
                        inMonthPLanningFor = false;
                    }
                }

                if (!inMonthPLanningFor) {
                    continue;
                }
                

                //if day to study is over the number of days in the month, then continue to the loop iteration
                if (dayToStudy > getDaysInMonth(forMonth, forYear)) {continue;}

                int index1ToStudy = index1ForDayInMonth(dayToStudy, startingDayOfWeekOfTest);
                int index2ToStudy = index2ForDayInMonth(dayToStudy, startingDayOfWeekOfTest);
                studyPlan[index2ToStudy][index1ToStudy] = new ArrayList<String>();
                studyPlan[index2ToStudy][index1ToStudy].add(test.getClassName());
            }

        }

        return studyPlan;
    }

}
