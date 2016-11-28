package top.kernelpanic.old;

import org.junit.Test;

import static org.junit.Assert.*;
import top.kernelpanic.old.calculator.Command;

/**
 * Created by fu on 2016/11/27.
 */
public class CommandTest {
    @Test
    public void read_exp() throws Exception {
        Command cmd=new Command();
        String cmd_line;
        boolean res;

        cmd_line="x+y";
        res=cmd.read_exp(cmd_line);
        assertEquals("First character is a letter can't be "
                + "accepted!",res,true);

        cmd_line="1+x";
        res=cmd.read_exp(cmd_line);
        assertEquals("First character is a digit can't be "
                + "accepted!",res,true);

        cmd_line="#x+y";
        res=cmd.read_exp(cmd_line);
        assertEquals("First character can be not a letter or"
                + "digit!",res,false);

        cmd_line="x#y";
        res=cmd.read_exp(cmd_line);
        assertEquals("Next character can be invalid!",res,false);

        cmd_line="8*x/2+-y*2+z%5";
        res=cmd.read_exp(cmd_line);
        assertEquals("Invalid input can be accepted!",res,false);

        cmd_line="3*x+4*y+5*z";
        res=cmd.read_exp(cmd_line);
        assertEquals("The expression can't accept some valid input",res,true);
    }

    void test_Calc(String exp,String val,Command cmd){
        cmd.cmd_line=exp;
        cmd.cmd_group=exp.split(" ");
        cmd.exec_cmd();
        cmd.cmd_line=val;
        cmd.cmd_group=val.split(" ");
        cmd.exec_cmd();
    }

    @Test
    public void cal_exp() throws Exception {
        Command cmd=new Command();
        String exp;
        String res_str;
        int res_int;

        exp="3*x+4*y+5*z";

        test_Calc(exp,"!simplify x=1 y=2 z=3",cmd);
        res_int=cmd.exp_int;
        assertEquals("The answer is wrong!",res_int,26);

        test_Calc(exp,"!simplify x=1 y=2",cmd);
        res_str=cmd.exp_str;
        assertEquals("The answer is wrong!",res_str,"11+5*z");

        test_Calc(exp,"!simplify a=3",cmd);
        res_str=cmd.exp_str;
        res_int=cmd.exp_int;
        assertEquals("Invalid input can be accepted!",res_str,"");
        assertEquals("Invalid input can be accepted!",res_int,0);

        exp="6+x*y+z";

        test_Calc(exp,"!simplify x=1 y=2 z=3",cmd);
        res_int=cmd.exp_int;
        assertEquals("The answer is wrong!",res_int,11);

        test_Calc(exp,"!simplify x=1 y=2",cmd);
        res_str=cmd.exp_str;
        assertEquals("The answer is wrong!",res_str,"8+z");

        test_Calc(exp,"!simplify z=3",cmd);
        res_str=cmd.exp_str;
        assertEquals("The answer is wrong!",res_str,"9+x*y");

        test_Calc(exp,"!simplify a=3",cmd);
        res_str=cmd.exp_str;
        res_int=cmd.exp_int;
        assertEquals("Invalid input can be accepted!",res_str,"");
        assertEquals("Invalid input can be accepted!",res_int,0);
    }

}