package org.side.generatePassword;

import java.util.Random;

public class generate {
    public static String Password(int len, boolean specialChars, boolean caseSensitive) {
        String generatedPassKey = generatePassKey(len);

        if (specialChars && caseSensitive) {

        }
        else if (caseSensitive) {
            generatedPassKey = replaceCases(generatedPassKey, len);
        }
        return generatedPassKey;
    }

    private static String generatePassKey(int len) {
        StringBuilder temptPassKey = new StringBuilder();

        for (int i = 0; i < len; i++) {
            Random rand = new Random();
            char tempt = (char) rand.nextInt(26);
            temptPassKey.append(tempt);
        }
        return temptPassKey.toString();
    }

    private static String replaceCases(String generatedPassKey, int len) {
        float number = len / 2f;
        int lenPos = Math.round(number/2);
        String[] parts = generatedPassKey.split("");
        StringBuilder temptPassKey = new StringBuilder();

        for (int c = 0; c < lenPos; c++) {
            Random rand = new Random();
            int pos = rand.nextInt(lenPos);

            for (int i=0; i<parts.length; i++) {
                if (i==pos){

                    Random rand2 = new Random();
                    int pos2 = rand2.nextInt(2);

                    if (pos2==0)
                        temptPassKey.append(parts[i].toLowerCase());
                    else
                        temptPassKey.append(parts[i].toUpperCase());
                }else{
                    temptPassKey.append(parts[i]);
                }
            }

        }
        return temptPassKey.toString();
    }
}
