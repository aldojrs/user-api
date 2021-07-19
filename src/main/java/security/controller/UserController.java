package security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import security.dto.UserDTO;
import security.dto.UserResponseDTO;
import security.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/signup")
  public UserResponseDTO signup(@Valid @RequestBody UserDTO user) {
    return userService.signup(user);
  }

  @PostMapping("/login")
  public UserResponseDTO login(String name, String password) {
    return userService.login(name, password);
  }

  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public UserResponseDTO me(HttpServletRequest req) {
    return userService.me(req);
  }

}
