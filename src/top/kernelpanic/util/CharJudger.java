package top.kernelpanic.util;

/**
 * Created by fu on 2016/11/27.
 */
public class CharJudger {
    public static boolean isNum(char ch){
        if(ch>=48&&ch<=57)
            return true;
        else
            return false;
    }

    public static boolean isLetter(char ch){
        if((ch>=65 && ch<=90)||(ch>=97 && ch<=122))
            return true;
        else
            return false;
    }

    public static boolean isCal(char ch){
        if(ch=='+'||ch=='*')
            return true;
        else
            return false;
    }

    public static boolean isNextValid(char ch,char next_ch){
        if(isNum(ch))
            if(isNum(next_ch)||isCal(next_ch))
                return true;
            else
                return false;
        else if(isLetter(ch))
            if(isCal(next_ch))
                return true;
            else
                return false;
        else if(isCal(ch))
            if(isNum(next_ch)||isLetter(next_ch))
                return true;
            else
                return false;
        else
            return false;
    }
}
