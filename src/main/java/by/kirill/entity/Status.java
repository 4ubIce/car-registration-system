package by.kirill.entity;


import javax.persistence.*;

@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
