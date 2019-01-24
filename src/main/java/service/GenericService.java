package service;



import model.BaseModel;

import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericService<T extends BaseModel<PK>, PK extends Serializable> extends FactoryEntityManager implements Serializable {

    protected GenericService() {
    }

    private Class<T> getType() {
        return this.getTypeClass();
    }

    private Class<T> getTypeClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T save(T obj) throws PersistenceException {
        try {
            getEntityManager();
            entityManager.getTransaction().begin();
            if (obj.getPK() == null) {
                this.persist(obj);
            } else {
                obj = this.merge(obj);
            }
            flush();
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException();
        } finally {
            fecharConexao();
        }
        return obj;
    }

    private void persist(T obj) {
        entityManager.persist(obj);
    }

    private T merge(T obj) {
        return (T) entityManager.merge(obj);
    }

    private void flush() {
        entityManager.flush();
    }

    public void remove(T obj) throws PersistenceException {
        this.remove(obj.getPK());
    }

    private void remove(PK pk) throws PersistenceException {
        try {
            getEntityManager().getTransaction().begin();
            BaseModel<PK> t = (T) entityManager.find(this.getType(), pk);
            entityManager.remove(t);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException();
        } finally {
            fecharConexao();
        }
    }

    public List<T> findAll() {
        final List resultList = getEntityManager().createQuery("FROM " + getType().getName())
                .getResultList();
        fecharConexao();
        return resultList;
    }

    public T findById(long pk) {
        final T t = getEntityManager().find(this.getType(), pk);
        fecharConexao();
        return t;
    }

}
