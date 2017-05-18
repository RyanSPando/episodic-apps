package com.example.users;

import com.example.users.User;
import com.example.users.UsersRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
  private final UsersRepository usersRepository;

  public UsersController(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
      return this.usersRepository.save(user);
  }

  @GetMapping
  public Iterable<User> findAllUsers() {
    return this.usersRepository.findAll();
  }
}
