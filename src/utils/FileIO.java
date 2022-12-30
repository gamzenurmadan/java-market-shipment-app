package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FileIO {

    public static List<List<String>> readCommandsFromCSVFile(String filePath) {
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            List<List<String>> commandList = new ArrayList<List<String>>();
            while((line = br.readLine()) != null) {
                List<String> tokenList = getTokenList(line);
                commandList.add(tokenList);
            }

            br.close();
            return commandList;
        } catch (IOException e) {
            System.out.println(e.getMessage() + " Returning an empty list of commands!");
            return new ArrayList<List<String>>();
        }



    }
    
    private static List<String> getTokenList(String strLine) {

        StringTokenizer st = new StringTokenizer(strLine, ",");
        List<String> stringList = new ArrayList<String>();
        while(st.hasMoreTokens()) {
            stringList.add(st.nextToken());
        }
        return stringList;
    }
}
