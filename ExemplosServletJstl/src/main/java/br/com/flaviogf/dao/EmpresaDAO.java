package br.com.flaviogf.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class EmpresaDAO {

    private final static List<String> empresas = new ArrayList<>(
            Arrays.asList("SMN", "TONIN")
    );

    public Collection<String> lista() {
        return empresas;
    }

    public void adiciona(String empresa) {
        empresas.add(empresa);
    }
}
