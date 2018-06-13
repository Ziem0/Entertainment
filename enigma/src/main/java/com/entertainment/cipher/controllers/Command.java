package com.entertainment.cipher.controllers;

import com.entertainment.cipher.exceptions.InvalidCommandException;

import java.util.List;

public class Command {

    private static final String ENCIPHER_CMD = "-e";
    private static final String DECIPHER_CMD = "-d";
    private static final String LIST_CMD = "-l";

    private static final int TYPE_ID = 0;
    private static final int NAME_ID = 1;
    private static final int KEY_ID = 2;

    private String[] args;
    private String enigmaName;
    private String key;
    private boolean isListCmd;
    private boolean isEncipherCmd;
    private boolean isDecipherCmd;


    public Command(String[] args) {
        this.args = args;
    }

    public boolean getIsDecipherCmd() {
        return isDecipherCmd;
    }

    public boolean getIsEncipherCmd() {
        return isEncipherCmd;
    }

    public boolean getIsListCmd() {
        return isListCmd;
    }

    public String getEnigmaName() {
        return enigmaName;
    }

    public String getKey() {
        return key;
    }

    public void inputResolver(List<String> repo) throws InvalidCommandException {
        if (isListCmd()) {
            this.isListCmd = true;
            this.isEncipherCmd = false;
            this.isDecipherCmd = false;
        } else if (isEncipherCmd()) {
            this.isListCmd = false;
            this.isEncipherCmd = true;
            this.isDecipherCmd = false;
            this.assignKey();
            this.assignModuleName(repo);
        } else if (isDecipherCmd()) {
            this.isListCmd = false;
            this.isEncipherCmd = false;
            this.isDecipherCmd = true;
            this.assignKey();
            this.assignModuleName(repo);
        } else {
            throw new InvalidCommandException("Invalid command. Try --> -e|-d NAME [KEY}");

        }
    }

    private boolean isListCmd() {
        return args.length == 1 && args[TYPE_ID].equalsIgnoreCase(LIST_CMD);
    }

    private boolean isEncipherCmd() {
        return args.length > 1 && args[TYPE_ID].equalsIgnoreCase(ENCIPHER_CMD);
    }

    private boolean isDecipherCmd() {
        return args.length > 1 && args[TYPE_ID].equalsIgnoreCase(DECIPHER_CMD);
    }

    private void assignKey() {
        if (isKeyCorrect()) {
            this.key = args[KEY_ID];
        }
    }

    private void assignModuleName(List<String> repo) throws com.entertainment.cipher.exceptions.InvalidCommandException {
        for (String name : repo) {
            if (name.equalsIgnoreCase(args[NAME_ID])) {
                this.enigmaName = args[NAME_ID];
            }
        }
        if (this.enigmaName == null) {
            throw new com.entertainment.cipher.exceptions.InvalidCommandException("There is no enigma like that");
        }
    }

    private boolean isKeyCorrect() {
        return args.length == 3 && args[KEY_ID].matches("[1-9]{1,}");
    }
}