package service;

public interface InterfaceEntityManager<EntityManager, EntityManagerFactory> {

    EntityManagerFactory getEntityManagerFactory();
    void fecharConexao();
    EntityManager getEntityManager();

}

