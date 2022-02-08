package com.mrknight.userservice.service;

import java.util.List;

import com.mrknight.userservice.entity.user;
import com.mrknight.userservice.repository.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {

  @Autowired
  private UserDAO UserDAO;

  public List<user> getAll() {
    return UserDAO.findAll();
  }

  public user getUserById(int id) {
    return UserDAO.findById(id).orElse(null);
  }

  public user save(user user) {
    return UserDAO.save(user);
  }

}
