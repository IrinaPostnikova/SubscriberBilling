package org.example;

import java.io.*;
import java.util.ArrayList;

public class CdrReader {

    public ArrayList<String> getCdrsFromFile(String path) {
        ArrayList<String> fileLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                fileLines.add(str);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileLines;
    }

}
