package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.Exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DbGroupService {
    private final GroupRepository repository;

    public List<Group> getGroups() {
        return repository.findAll();
    }

    public Optional<Group> getGroup(Long id) throws GroupNotFoundException {
        Optional<Group> result = Optional.ofNullable(repository.findById(id).orElseThrow(GroupNotFoundException::new));
        return result;
    }

    public Group saveGroup(final Group group) {
        return repository.save(group);
    }

    public void deleteGroup(final Long id) {
        repository.deleteById(id);
    }
}
