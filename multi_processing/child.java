
//import org.jetbrains.annotations.Contract;
//import sun.security.util.Length;
import java.io.*;
import java.lang.Object;
import java.lang.Long;
import java.util.ArrayList;
import java.util.LinkedList;
import static java.lang.Long.*;


public class child {

    public static void main(String[] child_input) throws Exception{

        String child_test_file = child_input[0];
        int child_pro_number = Integer.parseInt(child_input[1]);
        int child_total_process_number = Integer.parseInt(child_input[2]);

        try {
            BufferedReader in = new BufferedReader(new FileReader(child_test_file));
            ArrayList<String> arrayList = new ArrayList<>();

            String line;


            while ((line =in.readLine()) != null) {
                arrayList.add(line);
            }

            in.close();

            long[] out = max(arrayList, child_pro_number , child_total_process_number );

            FileWriter fw = new FileWriter("C:\\Users\\Omid\\IdeaProjects\\os1_test1\\src\\" +"max"+child_pro_number+".txt" );
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(out[0]));
            bw.newLine();
            bw.write(String.valueOf(out[1]));
            bw.newLine();
            bw.write(String.valueOf(out[2]));
            bw.newLine();
            bw.write(String.valueOf(out[3]));
            bw.flush();
            bw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static long[] max(ArrayList<String> arraylist_method , int child_pro_number , int child_total_process_number ){
        long[] l = new long[4];

        long  max_in_pro = Long.valueOf(arraylist_method.get(child_pro_number));
        long min = Long.valueOf(arraylist_method.get(child_pro_number));
        Long sum = Long.valueOf(arraylist_method.get(child_pro_number));
        long startTime = System.currentTimeMillis();

        for (int i=child_total_process_number+child_pro_number  ;i < arraylist_method.size() ;i+=child_total_process_number)
        {
            Long temp = Long.valueOf(arraylist_method.get(i));
            sum += temp;
            if ( temp > max_in_pro ) {
                max_in_pro = temp;
            }
            if(temp < min){
                min = temp;
            }
        }
        long EndTime = System.currentTimeMillis();
        l[0] = max_in_pro;
        l[1] = min;
        l[2] = sum;
        l[3] = EndTime - startTime;
        System.out.println("process( "+child_pro_number+" )\t Max =  "+max_in_pro+"\t Min = "+min+"\t Sum = "+sum+"\t Time Of calculate = " + l[3]);
        return l;
    }
    }

