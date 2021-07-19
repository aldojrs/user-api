package security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import security.model.User;
import security.repository.UserRepository;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    final User user = userRepository.findByName(name);

    if (user == null) {
      throw new UsernameNotFoundException("El usuario '" + name + "' no existe");
    }

    return org.springframework.security.core.userdetails.User
        .withUsername(name)
        .password(user.getPassword())
        .authorities(user.getRoles())
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
  }

}
