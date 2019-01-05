package br.com.flaviogf.tarefa;

import br.com.flaviogf.ITarefa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class TarefaExemploForEachJsp implements ITarefa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("cachorros", Arrays.asList("Preto", "Pit", "Rex", "Nina", "Tank", "Frank"));
        return "exemplo_foreach.jsp";
    }
}
