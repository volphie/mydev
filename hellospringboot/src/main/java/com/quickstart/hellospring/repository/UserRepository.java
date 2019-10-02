package com.quickstart.hellospring.repository;

import org.springframework.data.repository.CrudRepository;
import com.quickstart.hellospring.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}