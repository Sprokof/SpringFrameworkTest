package com.twister.app.repositories;

import com.twister.app.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findUserByUsername(String userName);
}
