package beanExamples;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class GalleryNotFoundException extends Exception {

    public GalleryNotFoundException(){    }

    public GalleryNotFoundException(String message){
        super(message);
    }
}
