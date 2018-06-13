package com.entertainment.cipher;

import com.entertainment.cipher.controllers.Command;
import com.entertainment.cipher.exceptions.InvalidCommandException;
import com.entertainment.cipher.interfaces.Module;
import com.entertainment.cipher.models.CesarEnigma;
import com.entertainment.cipher.models.MorseEnigma;
import com.entertainment.cipher.models.ServiceRepository;

public class App {

    private Module terminal;

    public static void main(String[] args) {
        ServiceRepository repo = ServiceRepository.getInstance();
        repo.register(new CesarEnigma());
        repo.register(new MorseEnigma());

        Command cmd = new Command(args);
        try {
            cmd.inputResolver(repo.listAll());
        } catch (InvalidCommandException e) {
            e.printStackTrace();
        }




    }
}
