package com.example.p1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDatabase implements UserCollection, Serializable {
    private static final long serialVersionUID = -1222122104L;
    private final Set<User> users = new HashSet<>();

    @Override
    public void add(final User user) {
        users.add(user);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }
}
