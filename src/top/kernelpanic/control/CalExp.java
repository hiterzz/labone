package top.kernelpanic.control;

import top.kernelpanic.entity.Command;
import top.kernelpanic.entity.Expression;
import top.kernelpanic.util.CharJudger;
import top.kernelpanic.util.Other;

/**
 * Created by fu on 2016/11/27.
 */
public class CalExp {
    public static int expInt;
    public static String expStr;

    public static boolean execCalExp(Command cmd, Expression exp) {
        int cmdGrpLen = cmd.cmdGroup.length;
        int expGrpLen = exp.expGroup.length;

        for (int i = 1; i < cmdGrpLen; i++) {
            String item = cmd.cmdGroup[i];
            int itemLen = item.length();
            int itemValue = 0;

            if (exp.var[item.charAt(0)] == 0 || item.charAt(1) != '=')
                return false;
            for (int j = 2; j < itemLen; j++) {
                if (CharJudger.isNum(item.charAt(j)))
                    itemValue = 10 * itemValue + (item.charAt(j) - 48);
                else
                    return false;
            }
            exp.var[item.charAt(0)] = itemValue;
        }

        for (int i = 0; i < expGrpLen; i++) {
            String item = exp.expGroup[i];
            int itemInt = 1;
            int itemLen = item.length();
            String itemStr = "";

            for (int j = 0; j < itemLen; j++) {
                int num = 0;
                while (j < itemLen && CharJudger.isNum(item.charAt(j))) {
                    num = num * 10 + (item.charAt(j) - 48);
                    j++;
                }
                if (num != 0)
                    itemInt *= num;
                if (j < itemLen) {
                    if (CharJudger.isLetter(item.charAt(j)))
                        if (exp.var[item.charAt(j)] != -1)
                            itemInt *= exp.var[item.charAt(j)];
                        else
                            itemStr = itemStr.concat(String.valueOf(item.charAt(j)).concat("*"));
                }
            }
            if (itemStr.equals(""))
                expInt += itemInt;
            else {
                itemStr = Other.checkStr(itemStr);
                if (itemInt != 1)
                    itemStr = String.valueOf(itemInt).concat("*").concat(itemStr);
                expStr = expStr.concat(itemStr).concat("+");
            }
        }
        if(!expStr.equals("")){
            expStr=Other.checkStr(expStr);
            if(expInt!=0)
                expStr=String.valueOf(expInt).concat("+").concat(expStr);
        }
        return true;
    }
}
