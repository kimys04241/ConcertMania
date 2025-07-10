package com.company.assignment.user.repository;

import com.company.assignment.user.domian.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsBySsnHash(String ssnHash);
    Optional<User> findByUserId(String userId);
}
