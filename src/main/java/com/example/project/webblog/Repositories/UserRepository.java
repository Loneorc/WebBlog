package com.example.project.webblog.Repositories;

import com.example.project.webblog.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsUserByUserName(String userName);
    boolean existsUserByUserNameAndPassword(String userName, String password);
    User findUserByUserName(String userName);
    User findById(Optional<Long> userId);
}
