import java.io.Console;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class myException extends Exception{
    public myException(String s)
    {
        super(s);
    }
}


public class Program {

    static int Count(int a, int b, String exp) throws myException
    {
        int res = 0;
        if (exp.equals("+")) {
            res = a + b;
        }
        else if (exp.equals("-")){
            res = a - b;
        }
        else if (exp.equals("*")){
            res = a * b;

        }
        else if (exp.equals("/")){
            if (b == 0)
            {
                throw new myException("divide on zero is not allowed");
            }
            res = a / b;
        }
        else {
            res = 0;
        }

        return res;
    }

    static boolean isValidOperation(String op)
    {
        List<String> ops = Arrays.asList("*", "/", "+", "-");
        if (ops.contains(op)){
            return true;
        }
        else
        {
            return false;
        }
    }

    static boolean isValidNumber(int number)
    {
        if (-10 <= number && number <= 10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main(String args[]) throws IOException, myException
    {


        System.out.print("Enter expression: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int nums[] = new int[3];
        String exps[] = new String[2];
        int n_cnt = 0, e_cnt = 0, cnt = 0, result = 0;
        //int res_t[] = new int[3];




        Scanner scan = new Scanner(s);
        while(scan.hasNext())
        {
            if (scan.hasNextInt() & cnt % 2 == 0)
            {
                nums[n_cnt] = scan.nextInt();
                if (isValidNumber(nums[n_cnt]))
                {
                    n_cnt++;
                }
                else {
                    throw new myException("Incorrect numbers - You can only use numbers from -10 to 10");
                }
            }
            else if (scan.hasNext() & cnt % 2 == 1)
            {
                if (cnt >= 5)
                {
                    throw new myException("Does not support more than 3 numbers");
                }
                exps[e_cnt] = scan.next();
                if (isValidOperation(exps[e_cnt]))
                {
                    e_cnt++;
                }
                else {
                    throw new myException("incorrect operation");
                }
            }
            else
            {
                throw new myException("incorrect expression");
            }

            cnt++;
        }
        scan.close();



        //System.out.println(Arrays.toString(exps));
        ArrayList<String> l_exps = new ArrayList<>();

        ArrayList<Integer> res_t = new ArrayList<>();

        for (int num : nums)
        {
            res_t.add(num);
        }

        for (String exp : exps)
        {
            l_exps.add(exp);
        }

/*        for (int i = 0; i < res_t.size(); i++) {
            System.out.println("l_num " + res_t.get(i));
        }*/


            for (String op : exps) {
                if (l_exps.contains("*")) {
                    int pos = l_exps.indexOf("*");
                    res_t.set(pos, Count(res_t.get(pos), res_t.get(pos + 1), l_exps.get(pos)));
                    res_t.remove(pos + 1);
                    l_exps.remove(pos);
                    result = res_t.get(pos);
                    System.out.printf("Result of *: %d \n", result);
                }

                if (l_exps.contains("/")) {
                    int pos = l_exps.indexOf("/");
                    res_t.set(pos, Count(res_t.get(pos), res_t.get(pos + 1), l_exps.get(pos)));
                    res_t.remove(pos + 1);
                    l_exps.remove(pos);
                    result = res_t.get(pos);
                    System.out.printf("Result of /: %d \n", result);
                }

                if (l_exps.contains("+")) {
                    int pos = l_exps.indexOf("+");
                    res_t.set(pos, Count(res_t.get(pos), res_t.get(pos + 1), l_exps.get(pos)));
                    res_t.remove(pos + 1);
                    l_exps.remove(pos);
                    result = res_t.get(pos);
                    System.out.printf("Result of +: %d \n", result);
                }

                if (l_exps.contains("-")) {
                    int pos = l_exps.indexOf("-");
                    res_t.set(pos, Count(res_t.get(pos), res_t.get(pos + 1), l_exps.get(pos)));
                    res_t.remove(pos + 1);
                    l_exps.remove(pos);
                    result = res_t.get(pos);
                    System.out.printf("Result of -: %d \n", result);
                }


            }


        System.out.printf("Result of expression: %d", result);

    }
}
