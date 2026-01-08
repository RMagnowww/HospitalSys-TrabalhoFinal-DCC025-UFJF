/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Gabriel Angelo Bovareto Batista - 202435039 */
    
package br.ufjf.dcc.controller;

import br.ufjf.dcc.model.*;

import java.io.IOException;
import java.util.List;

public class LoginController {

    public static Usuario autenticar(String email, String senha) {

        try {
            List<Usuario> usuarios = Persistencia.carregarUsuarios();

            for (Usuario u : usuarios) {
                if (u.getEmail().equals(email) &&
                    u.getSenha().equals(senha)) {
                    return u; // LOGIN OK
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // LOGIN INVALIDO
    }
}
