package com.pierce.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pierce.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findByUsername(String username);
    User getById(Long id);
}
