package TestPlanner;
public class TestInfo {
    // data members
    private String className;
    private int day;
    private int month;
    private int year;
    private int daysPerWeek;//number of days desired to study for this test
    private int weeksBeforeTest;
    /* private String professorE;
    private String officeHours;
    private String location;*/

    public TestInfo(String className, int day, int month, int year, int daysPerWeek, int weeksBeforeTest){
        this.className = className;
        this.day = day;
        this.month = month;
        this.year = year;
        this.daysPerWeek = daysPerWeek;
        this.weeksBeforeTest = weeksBeforeTest;
    }

    public String getClassName(){
        return className;
    }

    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    public int getDaysPerWeek(){
        return daysPerWeek;
    }

    public int weeksBeforeTest(){
        return weeksBeforeTest;
    }

    public String toString() {
        String out;
        out = String.format("%s,%d,%d,%d,%d,%d",
                         className, day, month, year, daysPerWeek, weeksBeforeTest);
	    return out;
    }
}