package by.kirill.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cars")
@NamedQueries({
        @NamedQuery(
                name = "Task.findAllByStatusId",
                query = "select e from Car e where e.status.id = ?1")
})
public class Car implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model")
    private String model;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Car() {
    }

    public Car(Integer id, String model, Status status) {
        this.id = id;
        this.model = model;
        this.status = status;
    }

    public Car(String model, Status status) {
        this.model = model;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public Object clone() {
        Car car = new Car();
        car.id = id;
        car.model = model;
        car.status = status;
        return car;
    }
}
