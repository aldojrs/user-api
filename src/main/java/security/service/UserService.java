package security.service;

import security.dto.UserDTO;
import security.dto.UserResponseDTO;
import security.model.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import security.exception.CustomException;
import security.model.User;
import security.repository.UserRepository;
import security.security.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private ModelMapper modelMapper;

  public UserResponseDTO signup(UserDTO userDTO) {

    User user = modelMapper.map(userDTO, User.class);

    if (!userRepository.existsByEmail(user.getEmail())) {

      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));
      user.setCrated(new Date());
      user.setModified(new Date());
      user.setLastLogin(new Date());
      user.setActive(true);
      user.setToken(jwtTokenProvider.createToken(user.getName(), user.getRoles()));
      userRepository.save(user);

      return modelMapper.map(user, UserResponseDTO.class);
    } else {
      throw new CustomException("Email ya registrado", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public UserResponseDTO login(String name, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));

      User user = userRepository.findByName(name);
      user.setLastLogin(new Date());
      user.setToken(jwtTokenProvider.createToken(user.getName(), user.getRoles()));

      return modelMapper.map(user, UserResponseDTO.class);
    } catch (AuthenticationException e) {
      throw new CustomException("name/password inválido", HttpStatus.NOT_FOUND);
    }
  }

  public UserResponseDTO me(HttpServletRequest req) {
    User user = userRepository.findByName(jwtTokenProvider.getName(jwtTokenProvider.resolveToken(req)));
    return modelMapper.map(user, UserResponseDTO.class);
  }

}
