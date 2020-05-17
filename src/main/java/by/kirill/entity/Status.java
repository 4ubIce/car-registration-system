package by.kirill.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Status.findByName",
                query = "select e from Status e where upper(e.statusname) like concat('%',upper(?1),'%')")
})
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "status_id_generator",
            sequenceName = "status_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Integer id;

    @Column(name = "name")
    private String statusname;

    public Status() {
    }

    public Status(String username) {
        this.statusname = username;
    }

    public Status(Integer id, String statusname) {
        this.statusname = statusname;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + statusname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(id, status.id) &&
                Objects.equals(statusname, status.statusname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusname);
    }
}
