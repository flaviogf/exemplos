package br.com.flaviogf;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/executa")
public class Contoller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeTarefa = req.getParameter("tarefa");
        String nomeClasse = format("br.com.flaviogf.tarefa.%s", nomeTarefa);
        try {
            Class<?> clazz = Class.forName(nomeClasse);
            ITarefa tarefa = (ITarefa) clazz.newInstance();
            String view = tarefa.executa(req, resp);
            RequestDispatcher dispatcher = req.getRequestDispatcher(format("WEB-INF/templates/%s", view));
            dispatcher.forward(req, resp);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new ServletException(e);
        }
    }
}
