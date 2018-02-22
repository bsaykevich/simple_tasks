package beanExamples;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class GalleryAlreadyExistsException extends Exception{

    private String name;

    GalleryAlreadyExistsException() {}

    GalleryAlreadyExistsException(String message, String name){
        super(message);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
