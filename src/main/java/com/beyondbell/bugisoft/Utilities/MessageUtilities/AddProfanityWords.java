package com.beyondbell.bugisoft.Utilities.MessageUtilities;

import java.io.*;
import java.nio.Buffer;

public class AddProfanityWords {

    public AddProfanityWords(String word) {
        BufferedWriter bufferedWriter;
        {
            try {

                FileWriter writer = new FileWriter("BadWords", true);
                writer.append("\n" + word);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
