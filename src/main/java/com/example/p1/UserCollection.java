package com.example.p1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserCollection extends Remote {
    String UPDATE_KEY_EXPERIENCE = "experience";

    String UPDATE_KEY_ABILITY = "ability";

    boolean create(final User user) throws RemoteException;

    List<User> read() throws RemoteException;

    boolean update(final String email, final String key, final String value) throws RemoteException;
}
