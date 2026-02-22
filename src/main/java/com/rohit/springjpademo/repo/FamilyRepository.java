package com.rohit.springjpademo.repo;

import com.rohit.springjpademo.entity.onetomany.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Long> {

    List<Family> findByUserId(Long userId);
}
