package com.entertainment.cipher.interfaces;

import com.entertainment.cipher.exceptions.WrongKeyException;

public interface EnigmaService {
    String encipher(String input);
    String decipher(String ciphered);
    boolean isKeyRequired();
    void setKey(String key) throws WrongKeyException;

    default String getName() {
        return getClass().getSimpleName();
    }
}
