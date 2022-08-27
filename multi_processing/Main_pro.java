//import sun.tools.jar.CommandLine;

import java.io.*;
import java.io.BufferedReader;
import java.lang.Process;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class Main_pro {

    public static void main(String [] args) throws Exception{
        try {
            System.out.println(" pleas inter instruction !!");
            Scanner sc = new Scanner(System.in);
            String string0 = sc.nextLine();
            String string1[] = string0.split(" ");
            String main_test_file = string1[2];
            String path = "C:\\Users\\Omid\\IdeaProjects\\os1_test1\\src\\";
            int total_Process_Number = Integer.parseInt(string1[4]);

            Process[] process = new Process[total_Process_Number];

            System.out.println("created process");
            int pro_number;
            BufferedReader[] out = new BufferedReader[total_Process_Number];
            BufferedReader[] err = new BufferedReader[total_Process_Number];
            for (pro_number = 0; pro_number < total_Process_Number; pro_number++) {
                String[] command = {"java", "-cp", path, "child", main_test_file, String.valueOf(pro_number), String.valueOf(total_Process_Number)};
                try {
                    process[pro_number] = Runtime.getRuntime().exec(command);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                out[pro_number] = new BufferedReader(new InputStreamReader(process[pro_number].getInputStream()));
                err[pro_number] = new BufferedReader(new InputStreamReader(process[pro_number].getErrorStream()));
            }


            System.out.println("passed argument");

            String str;
            for (pro_number = 0; pro_number < total_Process_Number; pro_number++) {
                if (process[pro_number ].waitFor() == 0) {
                    System.out.println(out[pro_number ].readLine());
                    System.out.println("ERROR OF PROCESS " + pro_number + "\t" + err[pro_number ].readLine());
                }
            }


            String line;
            LinkedList<String> linkedList_final = new LinkedList<>();
            for (pro_number = 0; pro_number < total_Process_Number; pro_number++) {
                BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Omid\\IdeaProjects\\os1_test1\\src\\"+"max" + pro_number + ".txt"));
                while ((line = in.readLine()) != null) {
                    linkedList_final.add(line);
                }

            }
            long max_numbers, min, sum ,max_time;
            max_numbers = Long.MIN_VALUE;
            max_time = Long.MIN_VALUE;
            min = Long.MAX_VALUE;
            sum = 0;
            long temp0 ,temp1,temp2,temp3;
            for (int j = 0; j < 4; j++)
            {
                for (int i = 0; i < linkedList_final.size(); i += 4) {

                    if (j ==0){
                        temp0 = Long.valueOf(linkedList_final.get(i));
                        if (temp0 > max_numbers)
                            max_numbers = temp0;

                    }
                    if (j==1){
                        temp1 = Long.valueOf(linkedList_final.get(i + 1));
                        if (temp1 < min)
                            min = temp1;
                    }
                    if (j==2){
                        temp2 = Long.valueOf(linkedList_final.get(i + 2));
                        sum += temp2;
                    }
                    if (j==3){
                        temp3 = Long.valueOf(linkedList_final.get(i + 3));
                        if (temp3 > max_time)
                            max_time = temp3;
                    }


                }

        }
            System.out.println("\n\t\t\t Final Max = "+max_numbers+"\t Final Min = " + min + "\t Final Sum = " + sum + "\t Total Times = " + max_time);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    }
//eaveu -f C:\Users\Omid\IdeaProjects\os1_test1\data.txt -p 20