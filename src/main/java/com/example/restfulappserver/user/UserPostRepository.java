package com.example.restfulappserver.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Integer> {
}
