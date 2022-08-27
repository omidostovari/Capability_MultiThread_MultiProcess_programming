import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main_Thread {
    static ArrayList<Long> m = new ArrayList<>();
    public static void main(String [] args){
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


        BlockingQueue<Long> max = new ArrayBlockingQueue(total_Thread_Number);
        BlockingQueue<Long> min = new ArrayBlockingQueue<Long>(total_Thread_Number);
        BlockingQueue<Long> sum = new ArrayBlockingQueue<Long>(total_Thread_Number);
        BlockingQueue<Long> time = new ArrayBlockingQueue<Long>(total_Thread_Number);

        ExecutorService executor = Executors.newFixedThreadPool(total_Thread_Number);

        int pro_number;

        for (pro_number = 0; pro_number < total_Thread_Number; pro_number++) {
            Runnable child = new Child_Thread(total_Thread_Number,pro_number,max ,min,sum,time,arraylist1);
            try {
                executor.execute(child);//calling execute method of ExecutorService
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
             }
        executor.shutdown();

        System.out.println("passed argument");
        while (!executor.isTerminated()) {}

        long max1 = max.poll();
        long min1 = min.poll();
        long time1 = time.poll();
        long sum1 = sum.poll();
        long temp ;

        for(int i=1;i<total_Thread_Number;i++){
            temp = max.poll();
            if(temp>max1)
               max1 = temp;
            temp = min.poll();
            if(temp<min1)
                min1 = temp;
            temp = time.poll();
            if(temp>time1)
                time1 = temp;
            sum1 += sum.poll();
        }
        System.out.println("final max = "+max1);
        System.out.println("final min = "+min1);
        System.out.println("final sum = "+sum1);
        System.out.println("final time = "+time1);
    }
}
//gyfgvy -f C:\Users\Omid\IdeaProjects\os1_test1\data.txt -p 20