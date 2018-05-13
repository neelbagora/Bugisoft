package com.beyondbell.bugisoft.Utilities.MessageUtilities;

import java.io.*;
import java.util.ArrayList;

public class SortBadWords {
    ArrayList<String> words;


    public SortBadWords() {
        FileInputStream BadWords = null;
        try {
            BadWords = new FileInputStream("BadWords");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(BadWords));

        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                words.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        words.sort(String::compareToIgnoreCase);

        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("BadWords"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for(int i = 0; i < words.size(); i++) {
            try {
                writer.write(words.get(i) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
