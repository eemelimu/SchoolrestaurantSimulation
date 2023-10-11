package dao;

import datasource.MariaDbConnection;
import entity.PalvelupisteData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entity.Simulointi;
import java.util.List;


public class SimulointiDao {

        public void persist(Simulointi simulointi) {
            EntityManager em = MariaDbConnection.getInstance();
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            em.persist(simulointi);
            entityTransaction.commit();
        }

    public List<Simulointi> getAllSimulointi() {

        EntityManager em = MariaDbConnection.getInstance();
        em.getTransaction().begin();

        List<Simulointi> simuloinnit = em.createQuery("from Simulointi", Simulointi.class)
                .getResultList();
        em.getTransaction().commit();

        return simuloinnit;
    }

    public List<PalvelupisteData> getAllPalvelupisteet(int simulointiId) {
        EntityManager em = MariaDbConnection.getInstance();
        em.getTransaction().begin();
        List<PalvelupisteData> palvelupisteDataList = em.createQuery("FROM PalvelupisteData WHERE simulointiId = :simulointiId", PalvelupisteData.class)
                .setParameter("simulointiId", simulointiId)
                .getResultList();
        em.getTransaction().commit();
        return palvelupisteDataList;
    }
}

