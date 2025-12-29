package br.ufjf.dcc.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static final String ARQUIVO = "usuarios.dat";

    // Salva TODOS os usuários
    public static void salvarUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {

            oos.writeObject(usuarios);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carrega TODOS os usuários
    @SuppressWarnings("unchecked")
    public static List<Usuario> carregarUsuarios() {

        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(ARQUIVO))) {

            return (List<Usuario>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
