import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class ManageTest{
    private ArrayList<TestInfo> tests = new ArrayList<TestInfo>();
    private String filename;

    public ManageTest(String filename){
        this.filename = filename;
        readTests(filename);
    }

    private void readTests(String filename){
        File file = new File(filename);        

        try{
            Scanner s = new Scanner(file);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] tokens = line.split("\\|");
                String className = tokens[0];
                int day = Integer.parseInt(tokens[1]);
                int month = Integer.parseInt(tokens[2]);
                int year = Integer.parseInt(tokens[3]);
                int daysPerWeek = Integer.parseInt(tokens[4]);
                tests.add(new TestInfo(className, day, month, year, daysPerWeek));
            }
            s.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }
    }

    public static int findTest(ArrayList<TestInfo> list, String className){
        for (int i = 0; i < list.size(); i++){
            String c = list.get(i).getClassName();
            if(c.equals(className)){
                return i;
            }
        }
        return -1;
    }

    public static void addTest(ArrayList<TestInfo> list, TestInfo ti){
        if (findTest(list, ti.getClassName()) < 0) {
            list.add(ti);
            System.out.println("Test successfully added.");
        } else {
            System.out.println("There is already a test for this class.");
        }
    }

    public static void removeTest(ArrayList<TestInfo> list, TestInfo ti){
        if (findTest(list, ti.getClassName()) < 0){
            System.out.println("Test not found.");
        } else {
            list.remove(ti);
            System.out.println("Test successfully removed.");
        }
    }
}