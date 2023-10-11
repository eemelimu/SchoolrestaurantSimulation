package dao;

import datasource.MariaDbConnection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entity.PalvelupisteData;

public class PalvelupisteDataDao {

    public void persist(PalvelupisteData palvelupisteData) {
        EntityManager em = MariaDbConnection.getInstance();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        em.persist(palvelupisteData);
        entityTransaction.commit();
    }
}
