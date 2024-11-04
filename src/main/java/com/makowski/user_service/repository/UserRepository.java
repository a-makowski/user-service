package com.makowski.user_service.repository;

import java.util.Optional;
import com.makowski.user_service.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
