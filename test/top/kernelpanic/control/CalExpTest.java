package top.kernelpanic.control;

import org.junit.Test;
import top.kernelpanic.entity.Command;
import top.kernelpanic.entity.Expression;

import static org.junit.Assert.*;

/**
 * Created by fu on 2016/11/27.
 */
public class CalExpTest {
    void prepareForTest(Command cmd,Expression exp,String cmdLine){
        cmd.cmdLine=exp.expLine;
        cmd.cmdGroup=exp.expLine.split(" ");
        exp.varClear();
        exp.parsedFromCmd(exp.expLine);
        cmd.cmdLine=cmdLine;
        cmd.cmdGroup=cmdLine.split(" ");
        CalExp.expInt=0;
        CalExp.expStr="";
        CalExp.execCalExp(cmd,exp);
    }

    @Test
    public void execCalExp() throws Exception {
        Command cmd=new Command();
        Expression exp=new Expression();
        int resInt;
        String resStr;

        exp.expLine="3*x+4*y+5*z";

        prepareForTest(cmd,exp,"!simplify x=1 y=2 z=3");
        resInt=CalExp.expInt;
        assertEquals("The answer is wrong!",resInt,26);

        prepareForTest(cmd,exp,"!simplify x=1 y=2");
        resStr=CalExp.expStr;
        assertEquals("The answer is wrong!",resStr,"11+5*z");

        prepareForTest(cmd,exp,"!simplify a=3");
        resStr=CalExp.expStr;
        resInt=CalExp.expInt;
        assertEquals("Invalid input can be accepted!",resStr,"");
        assertEquals("Invalid input can be accepted!",resInt,0);

        exp.expLine="6+x*y+z";

        prepareForTest(cmd,exp,"!simplify x=1 y=2 z=3");
        resInt=CalExp.expInt;
        assertEquals("The answer is wrong!",resInt,11);

        prepareForTest(cmd,exp,"!simplify x=1 y=2");
        resStr=CalExp.expStr;
        assertEquals("The answer is wrong!",resStr,"8+z");

        prepareForTest(cmd,exp,"!simplify z=3");
        resStr=CalExp.expStr;
        assertEquals("The answer is wrong!",resStr,"9+x*y");

        prepareForTest(cmd,exp,"!simplify a=3");
        resStr=CalExp.expStr;
        resInt=CalExp.expInt;
        assertEquals("Invalid input can be accepted!",resStr,"");
        assertEquals("Invalid input can be accepted!",resInt,0);
    }
}