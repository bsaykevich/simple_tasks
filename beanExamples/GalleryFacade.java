/**
 * Stateless Bean Example - Gallery Facade
 */

package beanExamples;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
//@LocalBean
public class GalleryFacade {

    @EJB
    private GalleryEAO galleryEAO;

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Gallery findById(Long id) throws GalleryNotFoundException{
        Gallery gallery = galleryEAO.find(id);
        if(gallery == null)
            throw new GalleryNotFoundException("Gallery not found");
        return gallery;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Gallery> findAll(){
        return galleryEAO.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void create(String name) throws GalleryAlreadyExistsException {
        if (galleryEAO.findByName(name) != null)
            throw new GalleryAlreadyExistsException("Gallery already exists", name);
        Gallery gallery = new Gallery();
        galleryEAO.persist(gallery);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void remove(Long id) throws GalleryNotFoundException {
        Gallery gallery = findById(id);
        galleryEAO.remove(gallery);
    }
}
