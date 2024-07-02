package controllers;

import models.Usuario;
import repositories.UsuariosRepoSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuariosRepoSingleton usuariosRepo;

    @Override
    public void init() throws ServletException {
        super.init();
        usuariosRepo = UsuariosRepoSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Usuario usuario = usuariosRepo.findByUsername(username);

        if (usuario != null && usuario.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            if (usuario.getTipo().equals("empleado")) {
                response.sendRedirect("productos?accion=index");
            } else if (usuario.getTipo().equals("cliente")) {
                response.sendRedirect("carrito?accion=cliente");
            }
        } else {
            response.sendRedirect("login?error=invalid_credentials");
        }
    }
}
