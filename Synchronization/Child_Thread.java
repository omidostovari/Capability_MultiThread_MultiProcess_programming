import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Child_Thread implements Runnable{
    private int child_total_Thread_number;
    private int child_Thread_number;
    ArrayList<Long> sum_child;
    ArrayList<String> arraylist1;
    public Child_Thread(int totalt, int numbert , ArrayList sum, ArrayList<String> array){
        child_total_Thread_number =totalt;
        child_Thread_number =numbert;
        sum_child = sum;
        arraylist1 = array;

        }
    public void run() {
        ///
        long sumTotal=0;
        ArrayList arrayFin = new ArrayList();
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            Long sum = Long.valueOf(arraylist1.get(child_Thread_number));
            for (int i=child_total_Thread_number+child_Thread_number  ;i < arraylist1.size() ;i+=child_total_Thread_number)
            {
                Long temp = Long.valueOf(arraylist1.get(i));
                sum += temp;
            }

                arrayFin.set(child_Thread_number, sum);
        }
        finally {
            lock.unlock();
        }



    }

}

