package com.project.urlshortner.repository;

import com.project.urlshortner.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findUserById(@NonNull Long id);
    User findUserByPhone(@NonNull String phone);
}
