package com.example.p1;

import java.io.Serializable;
import java.util.*;

/**
 * Representa uma base de dados de múltiplos usuários.
 * <p>
 * Um usuário só pode ser adicionado nessa base se não existe um usuário com seu e-mail já presente.
 */
public class UserDatabase implements UserCollection, Serializable {
    /**
     * Campo de serialização.
     */
    private static final long serialVersionUID = -1222122104L;

    /**
     * Usuários dessa base de dados.
     */
    private final Set<User> users = new HashSet<>();

    @Override
    public boolean create(final User user) {
        return users.add(user);
    }

    @Override
    public List<User> read() {
        return new ArrayList<>(users);
    }

    @Override
    public boolean update(final String email, final String key, final String value) {
        final Optional<User> optional = users.stream().filter(user -> user.email.equalsIgnoreCase(email)).findFirst();
        if (optional.isEmpty()) {
            return false;
        }

        final User user = optional.get();
        switch (key) {
            case UserCollection.UPDATE_KEY_ABILITY: {
                user.addAbility(value);
                return true;
            }
            case UserCollection.UPDATE_KEY_EXPERIENCE: {
                user.addExperience(value);
                return true;
            }
            default:
                throw new IllegalArgumentException("chave de atualização inválida: " + key);
        }
    }
}
