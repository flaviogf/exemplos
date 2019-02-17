package br.com.flaviogf.servlet;

import br.com.flaviogf.dao.EmpresaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = "/empresa")
public class EmpresaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmpresaDAO dao = new EmpresaDAO();
        Collection<String> empresas = dao.lista();
        req.setAttribute("empresas", empresas);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/templates/empresas.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EmpresaDAO dao = new EmpresaDAO();
        String nome = req.getParameter("nome");
        dao.adiciona(nome);
        resp.sendRedirect("/empresa");
    }
}
