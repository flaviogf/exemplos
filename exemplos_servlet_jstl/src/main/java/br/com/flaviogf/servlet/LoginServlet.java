package br.com.flaviogf.servlet;

import br.com.flaviogf.dao.UsuarioDao;
import br.com.flaviogf.models.Usuario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Usuario> usuarioOpcional = new UsuarioDao().busca(req.getParameter("email"), req.getParameter("senha"));
        if (!usuarioOpcional.isPresent()) return;
        Usuario usuario = usuarioOpcional.get();
        req.getSession().setAttribute("usuarioLogado", usuario);
        resp.sendRedirect("/");
    }
}
