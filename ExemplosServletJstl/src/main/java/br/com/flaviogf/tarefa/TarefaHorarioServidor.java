package br.com.flaviogf.tarefa;

import br.com.flaviogf.ITarefa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class TarefaHorarioServidor implements ITarefa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");
        String dataFormatada = formatter.format(LocalDateTime.now());
        req.setAttribute("dataFormatada", dataFormatada);
        req.setAttribute("dataNaoFormatada", Calendar.getInstance());
        return "horario_servidor.jsp";
    }
}
