package com.beyondbell.bugisoft.Utilities.MessageUtilities;

import java.io.*;
import java.nio.Buffer;

public class AddProfanityWords {

    public AddProfanityWords(String word) {
        BufferedWriter bufferedWriter;
        {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter("BadWords"));
                bufferedWriter.write("\n" + word);
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new SortBadWords();

    }
}
