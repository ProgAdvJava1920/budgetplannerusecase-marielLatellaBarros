package be.pxl.student.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EntityManagerUtil implements ServletContextListener {
    private static EntityManagerFactory emf;

    public static EntityManager createEntityManager() {
        if (emf == null){
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return emf.createEntityManager();
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        emf = Persistence.createEntityManagerFactory("budgetplanner");
    }


    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (emf != null) {
            emf.close();
        }
    }
}
