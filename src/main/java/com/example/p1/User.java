package com.example.p1;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class User implements Serializable {
    private static final long serialVersionUID = -530948457L;

    final String email;
    final String name;
    final byte[] pictureData;
    final String address;
    final String formation;
    private final List<String> abilities;
    private final List<String> jobs;

    private User(String email, String name, byte[] pictureData, String address, String formation, List<String> abilities, List<String> jobs) {
        this.email = email;
        this.name = name;
        this.pictureData = pictureData;
        this.address = address;
        this.formation = formation;
        this.abilities = abilities;
        this.jobs = jobs;
    }

    public List<String> getAbilities() {
        return Collections.unmodifiableList(abilities);
    }

    public List<String> getJobs() {
        return Collections.unmodifiableList(jobs);
    }

    public void addJob(final String job) {
        jobs.add(job);
    }

    public void addAbility(final String ability) {
        abilities.add(ability);
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

    public static class Builder {
        private String email;
        private String name;
        private String address;
        private String formation;
        private byte[] pictureData = new byte[0];
        private final List<String> abilities = new LinkedList<>();
        private final List<String> jobs = new LinkedList<>();

        public Builder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(final String address) {
            this.address = address;
            return this;
        }

        public Builder setFormation(final String formation) {
            this.formation = formation;
            return this;
        }

        public Builder setPictureData(final byte[] data) {
            this.pictureData = data;
            return this;
        }

        public Builder setAbilities(final List<String> abilities) {
            this.abilities.addAll(abilities);
            return this;
        }

        public Builder setPreviousJobs(final List<String> jobs) {
            this.jobs.addAll(jobs);
            return this;
        }

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

            return new User(email, name, pictureData, address, formation, abilities, jobs);
        }
    }
}