/**
 * Abstract EAO Example (Entity Access Object)
 */

package beanExamples;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;

public abstract class AbstractEAO<T extends Serializable> {

    //IDEA requires constructor to abstract class. WHY???
    public AbstractEAO() {
    }

    protected abstract EntityManager getEntityManager();

    private Class<T> entityClass;

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public AbstractEAO(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    public void persist(T entity){
        getEntityManager().persist(entity);
    }

    public void merge(T entity){
        getEntityManager().merge(entity);
    }

    public void remove(T entity){
        if(entity != null){
            getEntityManager().remove(entity);
        }
    }

    public void remove(Object id){
        T entity = (T) getEntityManager().find(entityClass, id);
        remove(entity);
    }

    public T find(Object id){
        return getEntityManager().find(entityClass, id);
    }

    public void refresh(T entity){
        getEntityManager().refresh(entity);
    }

    /*public TypedQuery<T> namedQuery(String queryName){
        return getEntityManager().createNamedQuery(queryName, entityClass);
    }*/

    public Query namedQuery(String queryName){
        return getEntityManager().createNamedQuery(queryName);
    }

    /*public TypedQuery<T> query(String queryString){
        return getEntityManager().createQuery(queryString, entityClass);
    }*/

    public Query query(String queryString){
        return getEntityManager().createQuery(queryString);
    }

   /* public long count(){
        CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> root = criteriaQuery.from(entityClass);

        criteriaQuery.select(getEntityManager().getCriteriaBuilder().count(root));
        Query query = getEntityManager().createQuery(criteriaQuery);

        return ((Long) query.getSingleResult()).longValue();
    }*/


}
