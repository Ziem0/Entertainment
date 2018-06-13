package com.entertainment.cipher.models;

import com.entertainment.cipher.exceptions.WrongKeyException;
import com.entertainment.cipher.interfaces.EnigmaService;
import com.google.common.base.Strings;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;

import java.util.List;

public class MorseEnigma implements EnigmaService{

    private static final List<String> MORSE = Lists.newArrayList("-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."
            ,".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-."
            ,"...", "-", "..-", ".--", "-..-", "-.--", "--..", "...-");

    private BiMap<String, String> morseTranslator = HashBiMap.create();

    public MorseEnigma() {
        int id = 47;
        int counter = 0;
        while (id++ < 122) {
            if (!(id > 57 && id < 65) && !(id > 90)) {
                String s = String.valueOf((char) id);
                this.morseTranslator.put(MORSE.get(counter),s);
                counter++;
            } else if (id > 96) {
                String s = String.valueOf((char) id);
                this.morseTranslator.put((MORSE.get(counter - 32) + "__"), s);
                counter++;
            }
        }
    }

    @Override
    public String encipher(String input) {
        String result = "";
        for (String s : input.split("")) {
            result += morseTranslator.inverse().get(s)+" ";
        }
        return result;
    }

    @Override
    public String decipher(String ciphered) {
        String result = "";
        for (String s : ciphered.split(" ")) {
            result += morseTranslator.get(s);
        }
        return result;
    }

    @Override
    public boolean isKeyRequired() {
        return false;
    }

    @Override
    public void setKey(String key) throws WrongKeyException {
    }

//    public static void main(String[] args) {
//        MorseEnigma m = new MorseEnigma();
//        String r = m.encipher("Ziemo2137583hejo");
//        System.out.println(r);
//        System.out.println(m.decipher(r));
//    }

}
