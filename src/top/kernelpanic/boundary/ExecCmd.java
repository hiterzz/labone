package top.kernelpanic.boundary;

import top.kernelpanic.control.CalExp;
import top.kernelpanic.control.QiuDao;
import top.kernelpanic.entity.Command;
import top.kernelpanic.entity.Expression;
import top.kernelpanic.util.CharJudger;

import java.util.Scanner;

/**
 * Created by fu on 2016/11/27.
 */
public class ExecCmd {
    public static boolean exec(Command cmd, Expression exp){
        char cmdFlag=cmd.cmdLine.charAt(0);

        if(cmdFlag!='!'){
            exp.varClear();
            if(exp.parsedFromCmd(cmd.cmdLine)){
                System.out.println(cmd.cmdLine);
            }else{
                exp.varClear();
                exp.parsedFromCmd(exp.expLine);
                return false;
            }
        }else if(cmd.cmdGroup[0].equals("!simplify")){
            CalExp.expInt=0;
            CalExp.expStr="";
            exp.varClear();
            exp.parsedFromCmd(exp.expLine);
            if(!CalExp.execCalExp(cmd,exp)) {
                exp.varClear();
                exp.parsedFromCmd(exp.expLine);
                return false;
            }else{
                if(CalExp.expStr.equals(""))
                    System.out.println(CalExp.expInt);
                else
                    System.out.println(CalExp.expStr);
            }
        }else if(cmd.cmdGroup[0].startsWith("!d/d")&&cmd.cmdGroup[0].length()==5&&
                CharJudger.isLetter(cmd.cmdGroup[0].charAt(4))&&exp.var[cmd.cmdGroup[0].charAt(4)]!=0) {
            QiuDao.daoInt=0;
            QiuDao.daoStr="";
            QiuDao.execQiuDao(cmd.cmdGroup[0].charAt(4),exp);
            if(QiuDao.daoStr.equals(""))
                System.out.println(QiuDao.daoInt);
            else
                System.out.println(QiuDao.daoStr);
        }else
            return false;
        return true;
    }

    public static void main(String[] args){
        Command cmd=new Command();
        Expression exp=new Expression();
        Scanner scanner=new Scanner(System.in);

        while(true){
            System.out.print('>');
            try {
                cmd.input(scanner);
                if (!exec(cmd, exp)) {
                    System.out.println("输入错误！");
                }
            }catch (Exception e){
                System.out.println("输入非法！");
            }
        }
    }
}
