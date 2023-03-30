package mypack;

import java.util.ArrayList;

public class OperationPosition{
    private String operand;
    private int position;

    public OperationPosition(){

    }

    //determining position of multiply and divide
    public void MultiAndDivide(ArrayList<String> exp_list){
        for (int i = 0; i < exp_list.size(); i ++){
            if (exp_list.get(i).contains("*") || exp_list.get(i).contains("/"))
            {
                operand = exp_list.get(i);
                position = i;
                break;
            }
        }
    }

    //determining position of plus and minus
    public void PlusAndMinus(ArrayList<String> exp_list){
        for (int i = 0; i < exp_list.size(); i ++){
            if (exp_list.get(i).contains("+") || exp_list.get(i).contains("-"))
            {
                operand = exp_list.get(i);
                position = i;
                break;
            }
        }
    }

    public String getOperation(){
        return operand;
    }

    public int getPosition(){
        return position;
    }

}
