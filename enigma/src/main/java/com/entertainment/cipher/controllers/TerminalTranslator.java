package com.entertainment.cipher.controllers;

import com.entertainment.cipher.exceptions.WrongKeyException;
import com.entertainment.cipher.interfaces.EnigmaService;
import com.entertainment.cipher.interfaces.Module;
import com.entertainment.cipher.interfaces.ServiceProvider;

import java.util.Scanner;

public class TerminalTranslator implements Module {

    private EnigmaService enigma;
    private ServiceProvider serviceProvider;
    private String enigmaName;
    private String key;
    private boolean isListCmd;
    private boolean isEncipherCmd;
    private boolean isDecipherCmd;

    public TerminalTranslator(String enigmaName, String key, boolean isListCmd, boolean isEncipherCmd, boolean isDecipherCmd) {
        this.enigmaName = enigmaName;
        this.key = key;
        this.isListCmd = isListCmd;
        this.isEncipherCmd = isEncipherCmd;
        this.isDecipherCmd = isDecipherCmd;
    }

    @Override
    public void initialize(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void start() {
        Scanner input = new Scanner(System.in);
        this.resolver();
        if (isListCmd) {
            System.out.println("\nEnigmas list\n");
            this.serviceProvider.listAll().forEach(System.out::printf);
        } else if (isEncipherCmd) {
            System.out.println("Type txt to encipher:");
            System.out.println(enigma.encipher(input.nextLine()));
        } else if (isDecipherCmd) {
            System.out.println("Type txt to decipher:");
            System.out.println(enigma.decipher(input.nextLine()));
        }
    }

    private void resolver() {
        this.enigma = this.serviceProvider.getByName(enigmaName);
        if (enigma.isKeyRequired()) {
            try {
                enigma.setKey(this.key);
            } catch (WrongKeyException e) {
                e.printStackTrace();
            }
        }
    }
}
