package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAll();
    Optional<Group> findByGroupId(Long groupId);
    Group save(Group group);
    void deleteByGroupId(Long groupId);



}
