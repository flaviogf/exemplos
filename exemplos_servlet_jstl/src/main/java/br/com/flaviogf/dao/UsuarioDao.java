package br.com.flaviogf.dao;

import br.com.flaviogf.models.Usuario;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UsuarioDao {

    private static List<Usuario> usuarios = Arrays.asList(
            new Usuario("flavio@email.com", "123"),
            new Usuario("fernando@email.com", "123")
    );

    public Optional<Usuario> busca(String email, String senha) {
        return usuarios.stream()
                .filter(usuario -> usuario.getEmail().equals(email) && usuario.getSenha().equals(senha))
                .findFirst();
    }
}
