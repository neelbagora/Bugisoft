package com.beyondbell.bugisoft.Utilities.MessageUtilities;

import java.io.*;
import java.nio.Buffer;

public class AddProfanityWords {

    public AddProfanityWords(String word) {
        BufferedWriter bis;
        {
            try {
                bis = new BufferedWriter(new FileWriter("BadWords"));
                bis.write("\n" + word);
                bis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new SortBadWords();

    }
}
