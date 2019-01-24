package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryEntityManager implements InterfaceEntityManager<EntityManager, EntityManagerFactory> {

    protected EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;
    private static final String PERSISTENCE_UNIT_NAME = "br.com.codepampa_pocketcontrolPU";


    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }

    @Override
    public void fecharConexao() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Override
    public EntityManager getEntityManager() {
        if(entityManager != null && entityManager.isOpen()) {
            return entityManager;
        }
        return entityManager = getEntityManagerFactory().createEntityManager();
    }
}
