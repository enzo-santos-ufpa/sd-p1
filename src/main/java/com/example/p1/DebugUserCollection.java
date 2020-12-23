package com.example.p1;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Decorador que mostra dados de tempo de execução para operações de uma {@link UserCollection}.
 */
public class DebugUserCollection implements UserCollection {
    /**
     * Coleção cujo tempo de execução de métodos serão mostrados na tela.
     */
    private final UserCollection collection;

    /**
     * Constrói um decorador.
     *
     * @param collection a coleção cujo tempo de execução de métodos serão mostrados na tela.
     */
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
