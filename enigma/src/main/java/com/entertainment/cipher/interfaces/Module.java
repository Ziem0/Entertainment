package com.entertainment.cipher.interfaces;

public interface Module {
    void initialize(ServiceProvider serviceProvider);
    String getName();
    void start();
}
