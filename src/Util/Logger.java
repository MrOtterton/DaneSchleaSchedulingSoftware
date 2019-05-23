package Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

/**
 *
 * @author Shakla
 */
public class Logger {
    
    private static final String fileName = "log.txt";
    
    public Logger(){}
    
    public static void log(String userName, boolean complete){
        try(FileWriter fWrite = new FileWriter(fileName, true);
                BufferedWriter bWrite = new BufferedWriter(fWrite);
                PrintWriter pWrite = new PrintWriter(bWrite)){
            pWrite.println(ZonedDateTime.now() + " " + userName + (complete ? " logged in" : " failed login"));
        } catch (IOException e) {
            System.out.println("Error entering into the logger: " + e.getMessage());
        }
    }
}
