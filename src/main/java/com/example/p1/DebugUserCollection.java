package com.example.p1;

import java.rmi.RemoteException;
import java.util.List;

public class DebugUserCollection implements UserCollection {
    private final UserCollection collection;

    public DebugUserCollection(final UserCollection collection) {
        this.collection = collection;
    }

    @Override
    public boolean create(User user) throws RemoteException {
        System.out.println("[DEBUG] `add` Tempo de entrada: " + System.currentTimeMillis());
        final boolean result = collection.create(user);
        System.out.println("[DEBUG] `add` Tempo de saída: " + System.currentTimeMillis());
        return result;
    }

    @Override
    public List<User> read() throws RemoteException {
        System.out.println("[DEBUG] `getUsers` Tempo de entrada: " + System.currentTimeMillis());
        final List<User> result = collection.read();
        System.out.println("[DEBUG] `getUsers` Tempo de saída: " + System.currentTimeMillis());
        return result;
    }

    @Override
    public boolean update(final String email, final String key, final String value) throws RemoteException {
        System.out.println("[DEBUG] `update` Tempo de entrada: " + System.currentTimeMillis());
        final boolean result = collection.update(email, key, value);
        System.out.println("[DEBUG] `update` Tempo de saída: " + System.currentTimeMillis());
        return result;
    }
}
