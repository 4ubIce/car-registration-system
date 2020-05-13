package by.kirill.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cars")
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
        Car task = new Car();
        task.id = id;
        task.model = model;
        task.status = status;
        return task;
    }
}
