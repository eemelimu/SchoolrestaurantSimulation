package datasource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class MariaDbConnection {
    public static EntityManagerFactory emf = null;
    public static EntityManager em = null;

    public static EntityManager getInstance(){
        if (em==null) {
            if (emf==null){
                emf = jakarta.persistence.Persistence.createEntityManagerFactory("CompanyMariaDbUnit");
            }
            em = emf.createEntityManager();
        }
        return em;
    }
}