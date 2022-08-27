import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Child_Thread implements Runnable{
    private int child_total_Thread_number;
    private int child_Thread_number;
    BlockingQueue<Long> max;
    BlockingQueue<Long> min;
    BlockingQueue<Long> sum;
    BlockingQueue<Long> time;
    ArrayList<String> arraylist1;
    public Child_Thread(int totalt, int numbert , BlockingQueue max, BlockingQueue min, BlockingQueue sum , BlockingQueue time, ArrayList<String> array){

        child_total_Thread_number =totalt;
        child_Thread_number =numbert;
        this.max= max;
        this.min= min;
        this.sum = sum;
        this.time = time;
        arraylist1 = array;
    }
    int counter = 1 ;
    public void run() {
        ///
            long[] out = max(arraylist1, child_Thread_number , child_total_Thread_number );
            max.add(out[0]);
            min.add(out[1]);
            sum.add(out[2]);
            time.add(out[3]);

    }
    public static long[] max(ArrayList<String> arrayList2 , int child_Thread_number_O , int child_total_process_number ){
        long[] l = new long[4];

        long  max_in_pro = Long.valueOf(arrayList2.get(child_Thread_number_O));
        long min = Long.valueOf(arrayList2.get(child_Thread_number_O));
        Long sum = Long.valueOf(arrayList2.get(child_Thread_number_O));
        long startTime = System.currentTimeMillis();

        for (int i=child_total_process_number+child_Thread_number_O  ;i < arrayList2.size() ;i+=child_total_process_number)
        {
            Long temp = Long.valueOf(arrayList2.get(i));
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
        l[3] = EndTime-startTime;
        System.out.println("Tread( "+child_Thread_number_O+" )\t Max =  "+max_in_pro+"\t Min = "+min+"\t Sum = "+sum+"\t Time Of calculate = " + l[3]);
        return l ;
    }
}
