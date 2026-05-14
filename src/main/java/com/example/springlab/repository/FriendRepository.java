package com.example.springlab.repository;

import com.example.springlab.domain.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
    Friend findByFname(String Fname);

}
