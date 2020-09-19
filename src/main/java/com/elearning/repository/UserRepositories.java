package com.elearning.repository;

import com.elearning.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositories extends JpaRepository<Users, Integer> {
    @Query("SELECT user FROM Users user WHERE user.fullName = :keyword OR user.email = :keyword OR user.phone= :keyword")
    List<Users> search(String keyword);
    Users findByEmail(String email);
}
