package by.kirill.service.impl;

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

    @Autowired
    StatusRepository statusRepository;

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
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
    public Status findByName(String name) {
        return statusRepository.findByName(name);
    }
}
