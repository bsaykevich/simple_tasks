/**
 * Entity Bean example
 */

package beanExamples;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.Size;


@Entity
@EntityListeners({GalleryListener.class})
@Table(name = "gallery",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
    }
)
@NamedQueries({
        @NamedQuery(name = Gallery.QUERY_FIND_ALL, query = ""/*"SELECT g FROM Gallery g ORDER BY g.name ASC"*/),
        @NamedQuery(name = Gallery.QUERY_FIND_BY_NAME, query = ""/*"SELECT g FROM Gallery g WHERE g.name = :name"*/)
})
public class Gallery implements Serializable{

    private static final long serialVersionUID = 1L;
    public static final String QUERY_FIND_ALL = "Gallery.findAll";
    public static final String QUERY_FIND_BY_NAME = "Gallery.findByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Gallery() {}

    public Gallery(String name){
        this.name = name;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gallery gallery = (Gallery) o;

        return id != null ? id.equals(gallery.id) : gallery.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
