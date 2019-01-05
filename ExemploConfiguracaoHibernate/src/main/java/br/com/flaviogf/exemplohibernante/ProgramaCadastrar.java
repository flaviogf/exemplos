package br.com.flaviogf.exemplohibernante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class ProgramaCadastrar {

    private static EntityManagerFactory emf;

    private static EntityManager entityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("produtos");
        }
        return emf.createEntityManager();
    }

    public static void main(String[] args) {

        EntityManager manager = entityManager();

        manager.getTransaction().begin();
        manager.persist(new Produto("Computador", new BigDecimal(100)));
        manager.persist(new Produto("Xbox One", new BigDecimal(250.55)));
        manager.getTransaction().commit();

        manager.close();
    }
}
