package com.example.p1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java com.example.p1.Server host [debug=true,false]");
            return;
        }
        final boolean debug = args.length >= 2 && Boolean.parseBoolean(args[1]);

        final String hostname = args[0];
        System.setProperty("java.rmi.server.hostname", hostname);

        UserCollection obj = new UserDatabase();
        if (debug) {
            obj = new DebugUserCollection(obj);
        }

        try {
            final UserCollection stub = (UserCollection) UnicastRemoteObject.exportObject(obj, 0);

            final Registry registry = LocateRegistry.getRegistry();
            registry.bind("UserManager", stub);

            System.out.println("Pronto.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
