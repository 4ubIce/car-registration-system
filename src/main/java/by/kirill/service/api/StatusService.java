package by.kirill.service.api;

import by.kirill.entity.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService {

    List<Status> findAll();

    void deleteById(Integer theId);

    boolean existsById(Integer id) ;

    Optional<Status> findById(Integer id);

}
