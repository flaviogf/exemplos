package br.com.flaviogf.exemplohibernante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProgramaListar {

    private static EntityManagerFactory emf;

    private static EntityManager entityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("produtos");
        }
        return emf.createEntityManager();
    }

    public static void main(String[] args) {

        EntityManager manager = entityManager();

        List<Produto> produtos = manager.createQuery("select p from Produto p", Produto.class).getResultList();

        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }
}
