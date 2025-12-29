package br.ufjf.dcc.controller;

import br.ufjf.dcc.model.Persistencia;
import br.ufjf.dcc.model.Usuario;

import java.util.List;

public class LoginController {

    public static Usuario login(String email, String senha) {

        List<Usuario> usuarios = Persistencia.carregarUsuarios();

        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) &&
                    u.getSenha().equals(senha)) {

                return u; // usuário COMPLETO
            }
        }
        return null; // login inválido
    }
}
