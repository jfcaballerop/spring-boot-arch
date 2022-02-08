package com.mrknight.userservice.repository;

import com.mrknight.userservice.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<user, Integer> {

}
