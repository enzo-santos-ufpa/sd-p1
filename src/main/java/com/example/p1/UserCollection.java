package com.example.p1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Representa ações que manipulam uma coleção de usuários.
 * <p>
 * Um objeto dessa interface pode ser utilizado para uma conexão RMI.
 */
public interface UserCollection extends Remote {
    /**
     * Uma chave que pode ser utilizada para atualizar a experiência do usuário no método {@link #update(String, String, String)}.
     */
    String UPDATE_KEY_EXPERIENCE = "experience";

    /**
     * Uma chave que pode ser utilizada para atualizar a habilidade do usuário no método {@link #update(String, String, String)}.
     */
    String UPDATE_KEY_ABILITY = "ability";

    /**
     * Adiciona um usuário a essa coleção.
     *
     * @param user o usuário a ser adicionado.
     * @return se o usuário conseguiu ser adicionado.
     * @throws RemoteException caso ocorra uma exceção durante a conexão RMI.
     */
    boolean create(final User user) throws RemoteException;

    /**
     * Lê os usuários dessa coleção.
     *
     * @return os usuários dessa coleção.
     * @throws RemoteException caso ocorra uma exceção durante a conexão RMI.
     */
    List<User> read() throws RemoteException;

    /**
     * Atualiza o perfil de um usuário dessa coleção.
     *
     * @param email o e-mail do usuário a ser adicionado.
     * @param key   o campo a ser atualizado, podendo ser "experience" para atualizar uma experiência profissional
     *              do usuário ou "ability" para atualizar uma habilidade do usuário. Os campos
     *              {@link #UPDATE_KEY_ABILITY} e {@link #UPDATE_KEY_EXPERIENCE} também podem ser utilizados.
     * @param value o valor a ser atualizado, podendo ser a experiência ou a habilidade nova.
     * @return se o usuário conseguiu ser atualizado.
     * @throws RemoteException caso ocorra uma exceção durante a conexão RMI.
     */
    boolean update(final String email, final String key, final String value) throws RemoteException;
}
