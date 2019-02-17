package br.com.flaviogf.filter;

import br.com.flaviogf.models.Usuario;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.String.format;

@WebFilter(urlPatterns = "/*")
public class AutonomiaFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filtroComSession(servletRequest, servletResponse, filterChain);
    }

    private void filtroComSession(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println(format("filtrando requisicao %s", request.getRequestURL()));
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        if (usuario != null) {
            System.out.println(usuario.getEmail());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void filtroComCookies(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("filtrando requisicao " + request.getRequestURL());
        Cookie[] cookies = request.getCookies();
        Arrays.stream(cookies != null ? cookies : new Cookie[]{})
                .filter(cookie -> cookie.getName().equals("usuario"))
                .findFirst()
                .map(Cookie::getValue)
                .ifPresent(System.out::println);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
