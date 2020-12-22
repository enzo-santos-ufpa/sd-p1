package com.example.p1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Client {
    private static final Scanner scanner = new Scanner(System.in);

    private Client() {
    }

    private static int fetchOperation() {
        System.out.println("Digite o número da operação a ser realizada.");
        System.out.println();
        System.out.println("1. Adicionar usuário");
        System.out.println("2. Listar usuários por curso");
        System.out.println("3. Listar habilidades de usuários por endereço");
        System.out.println("4. Acrescentar experiência de um usuário");
        System.out.println("5. Exibir experiência de um usuário");
        System.out.println("6. Listar informações de todos os usuários");
        System.out.println("7. Exibir informação de um usuário");
        System.out.println("0. Sair da sessão");
        System.out.println();
        System.out.println("Operação: ");

        return Integer.parseInt(scanner.nextLine().strip());
    }

    private static void fetchAnswer(final String field, final Consumer<String> parser) {
        System.out.print(field);
        parser.accept(scanner.nextLine().strip());
    }

    private static <T> void fetchAnswerWhile(
        final String field,
        final Function<String, T> parser,
        final Predicate<T> condition
    ) {
        System.out.print(field);
        for (; ; ) {
            final T result = parser.apply(scanner.nextLine().strip());
            if (condition.test(result)) {
                return;
            }
        }
    }

    private static void showUserInformation(final User user) {
        System.out.println("Nome: " + user.name);
        System.out.println("E-mail: " + user.email);
        System.out.println("Endereço: " + user.address);
        System.out.println("Formação acadêmica: " + user.formation);
        System.out.println("Possui foto? " + (user.pictureData.length > 0 ? "Sim" : "Não"));
        System.out.println("Habilidades: " + user.getAbilities());
        System.out.println("Experiências: " + user.getExperience());
    }

    private static User fetchUser() {
        final User.Builder builder = new User.Builder();

        fetchAnswer("E-mail: ", builder::setEmail);
        fetchAnswer("Nome: ", builder::setName);
        fetchAnswer("Endereço: ", builder::setAddress);
        fetchAnswer("Formação acadêmica: ", builder::setFormation);
        fetchAnswerWhile(
            "Diretório da foto de perfil [ENTER para sem foto]: ",
            answer -> answer.isEmpty() ? null : new File(answer),
            file -> {
                if (file == null) {
                    return true;
                }
                try {
                    builder.setPictureData(Files.readAllBytes(file.toPath()));
                    return true;

                } catch (IOException e) {
                    System.out.println("Não foi possível ler o arquivo.");
                    return false;
                }
            }
        );
        fetchAnswer("Habilidades [separado por ponto e vírgula, ENTER para nenhuma]: ", answer -> {
            if (!answer.isEmpty()) {
                builder.setAbilities(Arrays.stream(answer.split(";"))
                    .map(String::strip).collect(Collectors.toList()));
            }
        });
        fetchAnswer("Experiências [separado por poonto e vírgula, ENTER para nenhuma]: ", answer -> {
            if (!answer.isEmpty()) {
                builder.setExperience(Arrays.stream(answer.split(";"))
                    .map(String::strip).collect(Collectors.toList()));
            }
        });

        return builder.build();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java com.example.p1.Client host [debug=true,false]");
            return;
        }
        final String hostname = args[0];
        final boolean debug = args.length >= 2 && Boolean.parseBoolean(args[1]);

        final Registry registry;
        try {
            registry = LocateRegistry.getRegistry(hostname);
        } catch (RemoteException e) {
            System.out.println("Erro remoto.");
            e.printStackTrace();
            return;
        }

        final UserCollection collection;
        try {
            final UserCollection obj = (UserCollection) registry.lookup("UserManager");
            collection = debug ? new DebugUserCollection(obj) : obj;

        } catch (RemoteException | NotBoundException e) {
            System.out.println("Erro remoto.");
            e.printStackTrace();
            return;
        }

        System.out.println("========== BANCO DE DADOS ==========");
        for (; ; ) {
            final int operation = fetchOperation();
            System.out.println();
            switch (operation) {
                case 1: {
                    System.out.println("1. Adicionar usuário");
                    final User user = fetchUser();
                    try {
                        collection.add(user);
                    } catch (RemoteException e) {
                        System.out.println("Erro remoto.");
                        e.printStackTrace();
                        return;
                    }

                    System.out.println("Usuário adicionado.");
                    break;
                }
                case 2: {
                    System.out.println("2. Listar usuários por curso");
                    fetchAnswer("Curso: ", answer -> {
                        try {
                            final List<User> users = collection.getUsers().stream()
                                .filter(user -> user.formation.equalsIgnoreCase(answer))
                                .collect(Collectors.toList());

                            System.out.println("Usuários: ");
                            users.forEach(user -> System.out.println(user.email));

                        } catch (RemoteException e) {
                            System.out.println("Erro remoto.");
                            e.printStackTrace();
                        }
                    });
                    break;
                }
                case 3: {
                    System.out.println("3. Listar habilidades de usuários por endereço");
                    fetchAnswer("Endereço: ", answer -> {
                        try {
                            final List<User> users = collection.getUsers().stream()
                                .filter(user -> user.address.equalsIgnoreCase(answer))
                                .collect(Collectors.toList());

                            System.out.println("Habilidades: ");
                            users.forEach(user -> System.out.println(user.getAbilities()));

                        } catch (RemoteException e) {
                            System.out.println("Erro remoto.");
                            e.printStackTrace();
                        }
                    });
                    break;
                }
                case 4: {
                    System.out.println("4. Acrescentar experiência de um usuário");
                    fetchAnswer("E-mail: ", email -> {
                        final Optional<User> optional;
                        try {
                            optional = collection.getUsers().stream()
                                .filter(u -> u.email.equalsIgnoreCase(email))
                                .findFirst();

                        } catch (RemoteException e) {
                            System.out.println("Erro remoto.");
                            e.printStackTrace();
                            return;
                        }

                        if (optional.isPresent()) {
                            fetchAnswer("Experiência: ", job -> optional.get().addExperience(job));
                            System.out.println("Experiência adicionada.");
                        } else {
                            System.out.println("O e-mail informado não se encontra na base de dados.");
                        }
                    });
                    break;
                }
                case 5: {
                    System.out.println("5. Exibir experiência de um usuário");
                    fetchAnswer("E-mail: ", email -> {
                        final Optional<User> optional;
                        try {
                            optional = collection.getUsers().stream()
                                .filter(u -> u.email.equalsIgnoreCase(email))
                                .findFirst();

                        } catch (RemoteException e) {
                            System.out.println("Erro remoto.");
                            e.printStackTrace();
                            return;
                        }

                        if (optional.isPresent()) {
                            System.out.println("Experiências: " + optional.get().getExperience());
                        } else {
                            System.out.println("O e-mail informado não se encontra na base de dados.");
                        }
                    });
                    break;
                }
                case 6: {
                    System.out.println("6. Listar informações de todos os usuários");
                    final List<User> users;
                    try {
                        users = collection.getUsers();

                    } catch (RemoteException e) {
                        System.out.println("Erro remoto.");
                        e.printStackTrace();
                        return;
                    }

                    users.forEach(user -> {
                        System.out.println();
                        showUserInformation(user);
                    });
                    break;
                }
                case 7: {
                    System.out.println("7. Exibir informação de um usuário");
                    fetchAnswer("E-mail: ", email -> {
                        final Optional<User> optional;
                        try {
                            optional = collection.getUsers().stream()
                                .filter(u -> u.email.equalsIgnoreCase(email))
                                .findFirst();

                        } catch (RemoteException e) {
                            System.out.println("Erro remoto.");
                            e.printStackTrace();
                            return;
                        }

                        if (optional.isPresent()) {
                            showUserInformation(optional.get());
                        } else {
                            System.out.println("O e-mail informado não se encontra na base de dados.");
                        }
                    });
                    break;
                }
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
            System.out.println();
        }
    }
}