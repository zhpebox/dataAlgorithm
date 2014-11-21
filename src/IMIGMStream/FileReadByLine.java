package IMIGMStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReadByLine {

    public static List<List<String>> readFile(String fileName1, String fileName2, String fileName3,String fileName4, int windowid,int timepoint) {
        List<List<String>> s = new ArrayList<List<String>>();  
        s.add(readitemset(fileName1, timepoint));
        s.add(readitemset(fileName2, timepoint));
        s.add(readitemset(fileName3, timepoint));
        s.add(readitemset(fileName4, timepoint));
        return s;
    }
    
    //读一个项集
    
    public static List<String> readitemset(String fileName, int m)
    {  
        int count=0;
        BufferedReader reader = null;
        File file = new File(fileName);
        List<String> itemset = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            tempString = reader.readLine();
            while (tempString != null) {
                int linelen = 0;
                int itemsetlen = 0;
                String data[] = tempString.trim().split(" ");//一个空格          
                while (linelen < data.length) {
                    itemsetlen = Integer.parseInt(data[linelen]);
                    if(count==m){
                    itemset = new ArrayList<String>();
                    for(int i = 0; i < itemsetlen; i++) {
                        String item = data[linelen + 1 + i];
                        itemset.add(item);
                    }
                    break;
                    }
                    linelen = linelen + itemsetlen + 1;
                    count++;
                  }
                tempString = reader.readLine();
            }
            reader.close();
            return itemset;
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                	e1.printStackTrace();
                }
            }
        } 
        return null;
    }
}

