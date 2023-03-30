import java.io.Console;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import mypack.*;


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
        ArrayList<Integer> nums = new ArrayList<>();
        ArrayList<String> exps = new ArrayList<>();
        int n_cnt = 0, e_cnt = 0, cnt = 0, result = 0;


        Scanner scan = new Scanner(s);
        while(scan.hasNext())
        {
            if (scan.hasNextInt() & cnt % 2 == 0)
            {
                nums.add(scan.nextInt());
                if (isValidNumber(nums.get(n_cnt)))
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
                exps.add(scan.next());
                if (isValidOperation(exps.get(e_cnt)))
                {
                    e_cnt++;
                }
                else {
                    throw new myException("incorrect operation, please use only: *, /, + or -");
                }
            }
            else
            {
                throw new myException("incorrect expression, use only numbers like -3, -2, -1, 0, 1, 2 ,3 and etc.");
            }

            cnt++;
        }
        scan.close();



        ArrayList<String> l_exps = new ArrayList<>();

        ArrayList<Integer> res_t = new ArrayList<>();


        res_t = (ArrayList<Integer>)nums.clone();
        l_exps = (ArrayList<String>)exps.clone();

        if (res_t.size() == l_exps.size())
        {
            throw new myException("Wrong expression. Check expression, please.");
        }

        OperationPosition determineOpPos = new OperationPosition();

            for (String op : exps) {
                if (l_exps.contains("*") | l_exps.contains("/")) {

                    determineOpPos.MultiAndDivide(l_exps);
                    int pos = determineOpPos.getPosition();
                    String oper = determineOpPos.getOperation();
                    res_t.set(pos, Count(res_t.get(pos), res_t.get(pos + 1), oper));
                    res_t.remove(pos + 1);
                    l_exps.remove(pos);
                    result = res_t.get(pos);

                }

                if (l_exps.contains("+") | l_exps.contains("-")) {

                    determineOpPos.PlusAndMinus(l_exps);
                    int pos = determineOpPos.getPosition();
                    String oper = determineOpPos.getOperation();
                    res_t.set(pos, Count(res_t.get(pos), res_t.get(pos + 1), oper));
                    res_t.remove(pos + 1);
                    l_exps.remove(pos);
                    result = res_t.get(pos);

                }



            }


        System.out.printf("Result of expression: %d", result);

    }
}
