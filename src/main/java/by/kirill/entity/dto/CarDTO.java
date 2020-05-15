package by.kirill.entity.dto;

import by.kirill.entity.Car;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class CarDTO {

    private Integer id;
    private String model;
    private Integer statusId;

    public CarDTO() {
    }

    public CarDTO(Integer id, String model, Integer statusId) {
        this.id = id;
        this.model = model;
        this.statusId = statusId;
    }

    public CarDTO(String model, Integer statusId) {
        this.model = model;
        this.statusId = statusId;
    }

    public CarDTO (Car car) {
        this.id = car.getId();
        this.model = car.getModel();
        this.statusId = car.getStatus().getId();
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return Objects.equals(id, carDTO.id) &&
                Objects.equals(model, carDTO.model) &&
                Objects.equals(statusId, carDTO.statusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, statusId);
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", statusId=" + statusId +
                '}';
    }
}
