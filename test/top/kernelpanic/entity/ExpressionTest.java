package top.kernelpanic.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fu on 2016/11/27.
 */
public class ExpressionTest {
    @Test
    public void parsedFromCmd() throws Exception {
        Expression exp=new Expression();
        String cmdLine;
        boolean res;

        cmdLine="x+y";
        res=exp.parsedFromCmd(cmdLine);
        assertEquals("First character is a letter can't be "
                + "accepted!",res,true);

        cmdLine="1+x";
        res=exp.parsedFromCmd(cmdLine);
        assertEquals("First character is a digit can't be "
                + "accepted!",res,true);

        cmdLine="#x+y";
        res=exp.parsedFromCmd(cmdLine);
        assertEquals("First character can be not a letter or"
                + "digit!",res,false);

        cmdLine="x#y";
        res=exp.parsedFromCmd(cmdLine);
        assertEquals("Next character can be invalid!",res,false);

        cmdLine="8*x/2+-y*2+z%5";
        res=exp.parsedFromCmd(cmdLine);
        assertEquals("Invalid input can be accepted!",res,false);

        cmdLine="3*x+4*y+5*z";
        res=exp.parsedFromCmd(cmdLine);
        assertEquals("The expression can't accept some valid input",res,true);
    }

}