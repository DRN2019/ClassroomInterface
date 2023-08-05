package com.company;

import java.io.*;
import java.util.*;
public class FileManager {

    // Returns the given fileName as a String
    public String readFile(String fileName) {
        String fileContents = "";
        File toRead = new File(fileName);
        FileReader fr = null;
        try {
            fr = new FileReader(toRead);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (BufferedReader bfr = new BufferedReader(fr)) {
            String line = bfr.readLine();
            while (line != null) {
                fileContents = fileContents + "\n" + line;
                line = bfr.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContents;
    }

    // Writes a new File with the newFileContents
    public void writeNewFile(String newFileName, String newFileContents) {
        File f = new File(newFileName);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fos);
        pw.println(newFileContents);
        pw.close();
    }

    // Adds a line to an existing File
    public void addLine(String fileName, String addedLine) {
        File f = new File(fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fos);
        pw.println(addedLine);
        pw.close();
    }

}
