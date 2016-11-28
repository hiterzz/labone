package top.kernelpanic.util;

/**
 * Created by fu on 2016/11/27.
 */
public class Other {
    public static String checkStr(String str){
        int len=str.length();
        char ch=str.charAt(len-1);

        if(ch=='+'||ch=='*')
            str=str.substring(0,len-1);
        return str;
    }
}
