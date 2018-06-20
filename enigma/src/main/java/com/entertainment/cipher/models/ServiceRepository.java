package com.entertainment.cipher.models;

import com.entertainment.cipher.interfaces.EnigmaService;
import com.entertainment.cipher.interfaces.ServiceProvider;
import com.entertainment.cipher.interfaces.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ServiceRepository implements ServiceRegistry, ServiceProvider {

    private static ServiceRepository instance = null;
    private List<EnigmaService> services = null;   // static

    private ServiceRepository() {
        this.services = new ArrayList<>();
    }

    public static ServiceRepository getInstance() {
        if (instance == null) {
            synchronized (ServiceRepository.class) {
                if (instance == null) {
                    instance = new ServiceRepository();
                }
            }
        }
        return instance;
    }

    /*
    ServiceRegistry interface
     */
    @Override
    public void register(EnigmaService enigma) {
        this.services.add(enigma);
    }

    /*
    ServiceProvider interface
     */
    @Override
    public List<String> listAll() {
        return services.stream().map(EnigmaService::getName).collect(Collectors.toList());
    }

    /*
    ServiceProvider interface
     */
    @Override
    public EnigmaService getByName(String name) {
        return services.stream().filter(a -> a.getName().equalsIgnoreCase(name)).findFirst().get();
    }

//    public static void main(String[] args) {
//        ServiceRepository s = new ServiceRepository();
//        s.register(new MorseEnigma());
//        s.register(new CesarEnigma());
//        s.listAll().forEach(System.out::println);
//        System.out.println(s.getByName("MorseEnigma").isKeyRequired());
//    }
}
