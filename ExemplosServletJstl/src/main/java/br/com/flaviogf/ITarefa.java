package br.com.flaviogf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ITarefa {

    String executa(HttpServletRequest req, HttpServletResponse resp);
}
