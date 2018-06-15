package com.entertainment.cipher.models;

import com.entertainment.cipher.exceptions.WrongKeyException;
import com.entertainment.cipher.interfaces.EnigmaService;

public class CesarEnigma implements EnigmaService {

    private Integer key = 0;

    private String calculate(String input){
        String cipher = "";
            for (char ch : input.toCharArray()) {
                ch += key;
//                if (ch > 'z') {
//                    int diff = ch % 122;
//                    ch = 47;
//                    ch += diff;
//                } else if (ch < '0') {
//                    int diff = 48 % ch;
//                    ch = 123;
//                    ch -= diff;
//                }
                cipher += String.valueOf(ch);
        }
        return cipher;
    }

    @Override
    public String encipher(String input) {
        return this.calculate(input);
    }

    @Override
    public String decipher(String input) {
        this.key = -this.key;
        return this.calculate(input);
    }

    @Override
    public boolean isKeyRequired() {
        return true;
    }

    @Override
    public void setKey(String key) throws WrongKeyException {
        try {
            this.key = Integer.valueOf(key);
        } catch (NumberFormatException e) {
            throw new WrongKeyException("Invalid key!");
        }
    }

//    public static void main(String[] args) throws WrongKeyException {
//        CesarEnigma c = new CesarEnigma();
//        c.setKey("2");
//        String r = c.encipher("9nia");
//        System.out.println(r);
//        System.out.println(c.decipher(r));
//        System.out.printf("ciphered: %s\n",c.encipher("0nia"));
//        System.out.printf("deciphered: %s\n", c.decipher("wlg_"));
//        System.out.println(c.getName());
//        System.out.println((char)65);
//    }
}
