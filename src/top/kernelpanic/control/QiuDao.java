package top.kernelpanic.control;

import top.kernelpanic.entity.Expression;
import top.kernelpanic.util.CharJudger;
import top.kernelpanic.util.Other;

/**
 * Created by fu on 2016/11/27.
 */
public class QiuDao {
    public static int daoInt;
    public static String daoStr;

    private static boolean isIn(char ch,String str){
        int len=str.length();

        for(int i=0;i<len;i++)
            if(str.charAt(i)==ch)
                return true;
        return false;
    }

    public static void execQiuDao(char ch, Expression exp){
        int expGrpLen=exp.expGroup.length;

        for(int i=0;i<expGrpLen;i++){
            String item=exp.expGroup[i];
            int itemInt=1;
            String itemStr="";
            int itemLen=item.length();

            if(!isIn(ch,item))
                continue;
            for(int j=0;j<itemLen;j++){
                int num=0;
                while(j<itemLen&& CharJudger.isNum(item.charAt(j))){
                    num=num*10+(item.charAt(j)-48);
                    j++;
                }
                if(num!=0)
                    itemInt*=num;
                if(j<itemLen&&CharJudger.isLetter(item.charAt(j))&&item.charAt(j)!=ch)
                    itemStr=itemStr.concat(String.valueOf(item.charAt(j))).concat("*");
            }
            if(itemStr.equals(""))
                daoInt+=itemInt;
            else{
                itemStr= Other.checkStr(itemStr);
                if(itemInt!=1)
                    itemStr=String.valueOf(itemInt).concat("*").concat(itemStr);
                daoStr=daoStr.concat(itemStr).concat("+");
            }
        }
        if(!daoStr.equals("")){
            daoStr=Other.checkStr(daoStr);
            if(daoInt!=0)
                daoStr=String.valueOf(daoInt).concat("+").concat(daoStr);
        }
    }
}
