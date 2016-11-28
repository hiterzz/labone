package top.kernelpanic.entity;

import top.kernelpanic.util.CharJudger;

/**
 * Created by fu on 2016/11/27.
 */
public class Expression {
    public String expLine;
    public String expGroup[];
    public int[] var=new int[256];

    public void varClear(){
        for(int i=0;i<256;i++)
            var[i]=0;
    }

    public boolean parsedFromCmd(String cmdLine){
        expLine=cmdLine;
        expGroup=expLine.split("\\+");
        int expLen=expLine.length();
        char expFirst=expLine.charAt(0);

        if(CharJudger.isLetter(expFirst))
            var[expFirst]=-1;
        else if(!CharJudger.isNum(expFirst))
            return false;

        for(int i=0;i<expLen-1;i++){
            char cur=expLine.charAt(i);
            char next=expLine.charAt(i+1);

            if(CharJudger.isNextValid(cur,next)){
                if(CharJudger.isLetter(next)&&var[next]==0)
                    var[next]=-1;
            }else
                return false;
        }
        return true;
    }
}
