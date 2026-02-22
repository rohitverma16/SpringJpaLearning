package com.rohit.springjpademo.repo.onetoone;

import com.rohit.springjpademo.entity.onetoone.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
