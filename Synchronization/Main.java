import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
    static ArrayList<Long> m = new ArrayList<>();
    public static void main(String[]args){
        System.out.println(" pleas inter instruction !!");
        Scanner sc = new Scanner(System.in);
        String string0 = sc.nextLine();
        String string1[] = string0.split(" ");
        String main_test_file = string1[2];
        String path = "C:\\Users\\Omid\\IdeaProjects\\os1_test1\\src\\";
        int total_Thread_Number = Integer.parseInt(string1[4]);

        BufferedReader in = null;
        ArrayList<String> arraylist1 = new ArrayList<>();
        try {
            in = new BufferedReader(new FileReader(main_test_file));
            String line;
            while ((line = in.readLine()) != null) {
                arraylist1.add(line);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExecutorService executor = Executors.newFixedThreadPool(total_Thread_Number);
        ArrayList <String> arr_sume = new ArrayList<>();

        int pro_number;

        for (pro_number = 0; pro_number < total_Thread_Number; pro_number++) {
            Runnable child = new Child_Thread(total_Thread_Number,pro_number,arr_sume,arraylist1);
            try {
                executor.execute(child);//calling execute method of ExecutorService
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        executor.shutdown();

    }
}
