package com.gogibibo.goibibo.repo;

import com.gogibibo.goibibo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByUsername(String userName);
}
