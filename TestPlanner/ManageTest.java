package TestPlanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class ManageTest{
    private ArrayList<TestInfo> tests = new ArrayList<TestInfo>();
    private String filename;

    private ManageTest(String filename){
        this.filename = filename;
        readTests();
    }

    public static ManageTest shared = new ManageTest("data.csv");

    private void readTests(){
        File file = new File(filename);        
        if (!file.exists()) {return;}
        try{
            Scanner s = new Scanner(file);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] tokens = line.split("\\,");
                String className = tokens[0];
                int day = Integer.parseInt(tokens[1]);
                int month = Integer.parseInt(tokens[2]);
                int year = Integer.parseInt(tokens[3]);
                int daysPerWeek = Integer.parseInt(tokens[4]);
                tests.add(new TestInfo(className, day, month, year, daysPerWeek));
            }
            s.close();
        }
        catch(Exception e){
            System.out.println("Error reading file");
            System.exit(-1);
        }
    }

    public void saveTests(){
        try{
            PrintWriter writeFile = new PrintWriter(filename);
            for(int i = 0; i < tests.size(); i++){
                writeFile.print(tests.get(i).toString());
                writeFile.println(); // new line after one row
            }
                writeFile.close();
            }
        catch(Exception e){
                System.out.println("Cannot write to " + filename);
            }
    }

    public int findTest(String className){
        for (int i = 0; i < tests.size(); i++){
            String c = tests.get(i).getClassName();
            if(c.equals(className)){
                return i;
            }
        }
        return -1;
    }

    public boolean addTest(TestInfo ti){
        if (findTest(ti.getClassName()) < 0) {
            tests.add(ti);
            saveTests();
            return true; // test successfully added
        } else {
            return false; // test name already existed
        }
    }

    public boolean removeTest(String ti){
        int index = findTest(ti);
        if (index < 0){
            saveTests();
            return false; // test not found
        } else {
            tests.remove(index);
            saveTests();
            return true; // test successfully removed
        }
    }

    public ArrayList<TestInfo> getTests() {
        return tests;
    }
}