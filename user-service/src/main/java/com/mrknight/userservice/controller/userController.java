
package com.mrknight.userservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.mrknight.userservice.entity.user;
import com.mrknight.userservice.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * userController
 */
@RestController
@RequestMapping("/api/user")
@Validated
public class userController {
  @Autowired
  userService userService;

  @GetMapping
  public ResponseEntity<List<user>> getAll() {
    List<user> users = userService.getAll();
    if (users.isEmpty())
      return ResponseEntity.noContent().build();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<user> getUser(@PathVariable("id") int id) {
    user user = userService.getUserById(id);
    if (user == null)
      return ResponseEntity.noContent().build();

    return ResponseEntity.ok(user);
  }

  @PostMapping
  public ResponseEntity<user> saveUser(@Valid @RequestBody user user) {
    user newUser = userService.save(user);
    if (newUser == null)
      return ResponseEntity.noContent().build();

    return ResponseEntity.ok(newUser);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    return errors;
  }

}