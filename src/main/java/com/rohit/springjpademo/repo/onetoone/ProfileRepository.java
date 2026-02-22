package com.rohit.springjpademo.repo.onetoone;

import com.rohit.springjpademo.entity.onetoone.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
