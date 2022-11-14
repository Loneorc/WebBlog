package com.example.project.webblog.Repositories;

import com.example.project.webblog.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsUserByUserName(String userName);
    boolean existsUserByUserNameAndPassword(String userName, String password);
}
