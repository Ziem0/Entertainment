package com.entertainment.cipher.interfaces;

import java.util.List;

public interface ServiceProvider {
    List<String> listAll();
    EnigmaService getByName(String name);
}
