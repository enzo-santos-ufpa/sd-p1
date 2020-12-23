package com.example.p1;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Representa o perfil de um usuário.
 */
public final class User implements Serializable {
    /**
     * Campo de serialização.
     */
    private static final long serialVersionUID = -530948457L;

    /**
     * E-mail do usuário.
     */
    public final String email;

    /**
     * Nome do usuário.
     */
    public final String name;

    /**
     * Conteúdo do arquivo da foto de perfil do usuário.
     * <p>
     * Caso o usuário não possua foto, esse vetor tem comprimento zero.
     */
    public final byte[] pictureData;

    /**
     * Residência do usuário.
     */
    public final String address;

    /**
     * Formação acadêmica do usuário.
     */
    public final String formation;

    /**
     * Habilidades do usuário.
     * <p>
     * Exemplos de habilidades seriam análise de dados, Internet das Coisas, computação em nuvem, etc.
     */
    private final List<String> abilities;

    /**
     * Experiências profissionais do usuário.
     * <p>
     * Algumas experiências seriam "Estágio de 1 ano na Empresa X, onde trabalhei como analista de dados" ou
     * "Trabalhei com IoT e Computação em Nuvem por 5 anos na Empresa Y".
     */
    private final List<String> experiences;

    /**
     * Constrói um usuário.
     *
     * @param email       o e-mail do usuário.
     * @param name        o nome do usuário.
     * @param pictureData o conteúdo do arquivo da foto de perfil do usuário.
     * @param address     a residência do usuário.
     * @param formation   a formação acadêmica do usuário.
     * @param abilities   as habilidades do usuário.
     * @param experiences as experiências profissional do usuário.
     */
    private User(String email, String name, byte[] pictureData, String address, String formation, List<String> abilities, List<String> experiences) {
        this.email = email;
        this.name = name;
        this.pictureData = pictureData;
        this.address = address;
        this.formation = formation;
        this.abilities = abilities;
        this.experiences = experiences;
    }

    /**
     * @return as habilidades do usuário.
     */
    public List<String> getAbilities() {
        return Collections.unmodifiableList(abilities);
    }

    /**
     * @return as experiências profissionais do usuário.
     */
    public List<String> getExperiences() {
        return Collections.unmodifiableList(experiences);
    }

    /**
     * Adiciona uma abilidade ao.perfil do usuário.
     *
     * @param ability a habilidade a ser adicionada.
     */
    public void addAbility(final String ability) {
        abilities.add(ability);
    }

    /**
     * Adiciona uma experiência ao perfil do usuário.
     *
     * @param experience a experiência a ser adicionada.
     */
    public void addExperience(final String experience) {
        experiences.add(experience);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Constrói um usuário de forma <i>lazy</i>.
     */
    public static class Builder {
        private String email;
        private String name;
        private String address;
        private String formation;
        private byte[] pictureData = new byte[0];
        private final List<String> abilities = new LinkedList<>();
        private final List<String> experience = new LinkedList<>();

        /**
         * Define o e-mail a ser utilizado para construir o usuário.
         * <p>
         * Esse campo é obrigatório.
         *
         * @param email o campo de e-mail.
         * @return esse construtor.
         */
        public Builder setEmail(final String email) {
            this.email = email;
            return this;
        }

        /**
         * Define o nome a ser utilizado para construir o usuário.
         * <p>
         * Esse campo é obrigatório.
         *
         * @param name o campo de nome.
         * @return esse construtor.
         */
        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        /**
         * Define o endereço a ser utilizado para construir o usuário.
         * <p>
         * Esse campo é obrigatório.
         *
         * @param address o campo de endereço.
         * @return esse construtor.
         */
        public Builder setAddress(final String address) {
            this.address = address;
            return this;
        }

        /**
         * Define a formação acadêmica a ser utilizada para construir o usuário.
         * <p>
         * Esse campo é obrigatório.
         *
         * @param formation campo de formação acadêmica.
         * @return esse construtor.
         */
        public Builder setFormation(final String formation) {
            this.formation = formation;
            return this;
        }

        /**
         * Define a foto de perfil a ser utilizado para construir o usuário.
         * <p>
         * Esse campo é opcional.
         *
         * @param data o campo de foto de perfil.
         * @return esse construtor.
         */
        public Builder setPictureData(final byte[] data) {
            this.pictureData = data;
            return this;
        }

        /**
         * Define as habilidades a serem utilizadas para construir o usuário.
         * <p>
         * Esse campo é opcional.
         *
         * @param abilities o campo de habilidades.
         * @return esse construtor.
         */
        public Builder setAbilities(final List<String> abilities) {
            this.abilities.addAll(abilities);
            return this;
        }

        /**
         * Define as experiências profissionais a serem utilizadas para construir o usuário.
         * <p>
         * Esse campo é opcional.
         *
         * @param experiences o campo de experiências profissionais.
         * @return esse construtor.
         */
        public Builder setExperience(final List<String> experiences) {
            this.experience.addAll(experiences);
            return this;
        }

        /**
         * Constrói um usuário.
         *
         * @return o usuário com as configurações passadas para esse construtor.
         */
        public User build() {
            if (email == null) {
                throw new IllegalStateException("o e-mail do usuário deve ser inserido.");
            }
            if (name == null) {
                throw new IllegalStateException("o nome do usuário deve ser inserido.");
            }
            if (address == null) {
                throw new IllegalStateException("o endereço do usuário deve ser inserido.");
            }
            if (formation == null) {
                throw new IllegalStateException("a formação do usuário deve ser inserida.");
            }

            return new User(email, name, pictureData, address, formation, abilities, experience);
        }
    }
}
