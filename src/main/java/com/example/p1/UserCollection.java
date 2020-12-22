package com.example.p1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserCollection extends Remote {
    boolean add(final User user) throws RemoteException;

    List<User> getUsers() throws RemoteException;
}
