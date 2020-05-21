package by.kirill.service.impl;

import by.kirill.controller.handler.exceptions.StatusNotFoundException;
import by.kirill.controller.handler.exceptions.StatusNotUniqueException;
import by.kirill.controller.handler.exceptions.StatusNotUpdatebleException;
import by.kirill.dao.StatusRepository;
import by.kirill.entity.Status;
import by.kirill.service.api.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

//    @Autowired
    private StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) throws StatusNotUpdatebleException {
        Status defStatus = statusRepository.findByName("available").get(0);
        if (id == defStatus.getId()) {
            throw new StatusNotUpdatebleException();
        }
        statusRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return statusRepository.existsById(id);
    }

    @Override
    public Optional<Status> findById(Integer id) {
        return statusRepository.findById(id);
    }

    @Override
    public List<Status> findByName(String name) {
        return statusRepository.findByName(name);
    }

    @Override
    public Status create(Status status) throws StatusNotUniqueException {
        if (status.getId() != null || status.getStatusname().isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (!statusRepository.findByName(status.getStatusname()).isEmpty()) {
            throw new StatusNotUniqueException();
        }
        return statusRepository.save(status);
    }

    @Override
    public Status update(Status status, Integer id)
            throws StatusNotFoundException, StatusNotUpdatebleException, StatusNotUniqueException {
        status.setId(id);
        Optional<Status> statusToUpdateOpt = statusRepository.findById(status.getId());
        Status defStatus = statusRepository.findByName("available").get(0);
        if (!statusRepository.findByName(status.getStatusname()).isEmpty()) {
            throw new StatusNotUniqueException();
        }
        if (status.getId() == defStatus.getId()) {
            throw new StatusNotUpdatebleException();
        }
        if (statusToUpdateOpt.isPresent()) {
            return statusRepository.save(status);
        } else {
            throw new StatusNotFoundException();
        }
    }
}
