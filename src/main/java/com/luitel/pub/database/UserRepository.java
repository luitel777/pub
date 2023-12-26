package com.luitel.pub.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends CrudRepository<UserPostgres, Integer>{
  User findByUsernameOrEmail(String username, String email);
  User findByEmail(String email);
}
