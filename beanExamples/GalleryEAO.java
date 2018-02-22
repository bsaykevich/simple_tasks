/**
 * Session Bean Example - Stateless EAO (Entity Access Object)
 */

package beanExamples;

import java.util.List;
//import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
//@LocalBean
public class GalleryEAO extends AbstractEAO<Gallery>{

    @PersistenceContext(unitName = "PersistenceUnit")
    private EntityManager entityManager;

    public GalleryEAO(){
        super(Gallery.class);
    }

    @Override
    protected EntityManager getEntityManager(){
        return entityManager;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Gallery> findAll() {
        return namedQuery(Gallery.QUERY_FIND_ALL).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Gallery findByName(String name){
        return (Gallery) namedQuery(Gallery.QUERY_FIND_BY_NAME) //костыль в этой строке
                .setParameter("name", name)
                .getSingleResult();
    }
}
