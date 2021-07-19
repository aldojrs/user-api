package security.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 4, max = 255)
  @Column(nullable = false)
  private String name;

  @Email
  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String token;

  @Column(nullable = false)
  private Date crated;

  @Column(nullable = false)
  private Date modified;

  @Column(nullable = false)
  private Date lastLogin;

  @Column(nullable = false)
  private boolean active;

  @ElementCollection(fetch = FetchType.EAGER)
  List<Role> roles;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "user_id")
  List<Phone> phones;

}
