package com.entertainment.cipher.controllers;

import com.entertainment.cipher.interfaces.Module;
import com.entertainment.cipher.interfaces.ServiceProvider;

public class TerminalTranslator implements Module {

    @Override
    public void initialize(ServiceProvider serviceProvider) {

    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void start() {

    }
}
