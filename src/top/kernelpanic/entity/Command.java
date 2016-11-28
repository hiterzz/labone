package top.kernelpanic.entity;

import java.util.Scanner;

/**
 * Created by fu on 2016/11/27.
 */
public class Command {
    public String cmdLine;
    public String cmdGroup[];

    public void input(Scanner scanner){
        cmdLine=scanner.nextLine();
        if(cmdLine.equals("quit"))
            System.exit(0);
        cmdGroup=cmdLine.split(" ");
    }
}
