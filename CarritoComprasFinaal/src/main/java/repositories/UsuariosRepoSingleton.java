package repositories;

import models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosRepoSingleton {
    private static UsuariosRepoSingleton instance;
    private List<Usuario> usuarios;

    private UsuariosRepoSingleton() {
        usuarios = new ArrayList<>();
        // Agregar usuarios de prueba
        usuarios.add(new Usuario("cliente1", "password1", "cliente", 500000.00));
        usuarios.add(new Usuario("empleado1", "password1", "empleado"));
    }

    public static UsuariosRepoSingleton getInstance() {
        if (instance == null) {
            instance = new UsuariosRepoSingleton();
        }
        return instance;
    }

    public Usuario findByUsername(String username) {
        return usuarios.stream()
                .filter(usuario -> usuario.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}

