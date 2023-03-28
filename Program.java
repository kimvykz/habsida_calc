import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;
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

    static int Count(int a, int b, String exp){
        int res;
        if (exp == "+") {
            res = a + b;
        }
        else if (exp == "-"){
            res = a - b;
        }
        else if (exp == "*"){
            res = a * b;
        }
        else if (exp == "/"){
            res = a / b;
        }
        else {
            res = 0;
        }
        return res;
    }
    public static void main(String args[]) throws IOException, myException
    {


        System.out.print("Enter expression: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int nums[] = new int[3];
        String exps[] = new String[2];
        int n_cnt = 0, e_cnt = 0, cnt = 0;

        Scanner scan = new Scanner(s);
        while(scan.hasNext())
        {
            if (scan.hasNextInt() & cnt % 2 == 0)
            {
                nums[n_cnt++] = scan.nextInt();
            }
            else if (scan.hasNext() & cnt % 2 == 1)
            {
                exps[e_cnt++] = scan.next();
            }
            else
            {
                throw new myException("incorrect expression");
            }
            cnt++;
        }

        for (int num : nums)
        {
            System.out.println(num);
        }

        System.out.println(Arrays.toString(exps));

        scan.close();

    }
}
