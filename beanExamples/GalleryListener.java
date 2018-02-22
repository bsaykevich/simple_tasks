/**
 * Entity Bean Listener Example
 */

package beanExamples;

import java.util.Date;
import javax.persistence.PrePersist;

public class GalleryListener {

    @PrePersist
    public void prePersist(Gallery gallery){
        gallery.setCreatedAt(new Date());
    }
}
