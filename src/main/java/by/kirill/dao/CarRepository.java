package by.kirill.dao;

import by.kirill.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByStatusId(Integer id);
//
//    @Modifying
//    @Query("update Car c set c.model = ?1, c.status.id = ?2 where c.id = ?3")
//    void update(String model, Integer statusId);
}
