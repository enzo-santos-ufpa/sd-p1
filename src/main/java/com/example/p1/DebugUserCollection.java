package com.example.p1;

import java.rmi.RemoteException;
import java.util.List;

public class DebugUserCollection implements UserCollection {
    private final UserCollection collection;

    public DebugUserCollection(final UserCollection collection) {
        this.collection = collection;
    }

    @Override
    public boolean add(User user) throws RemoteException {
        System.out.println("[DEBUG] `add` Tempo de entrada: " + System.currentTimeMillis());
        final boolean result = collection.add(user);
        System.out.println("[DEBUG] `add` Tempo de saída: " + System.currentTimeMillis());
        return result;
    }

    @Override
    public List<User> getUsers() throws RemoteException {
        System.out.println("[DEBUG] `getUsers` Tempo de entrada: " + System.currentTimeMillis());
        final List<User> result = collection.getUsers();
        System.out.println("[DEBUG] `getUsers` Tempo de saída: " + System.currentTimeMillis());
        return result;
    }
}
